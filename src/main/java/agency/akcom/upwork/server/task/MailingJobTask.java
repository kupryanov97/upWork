package agency.akcom.upwork.server.task;

import agency.akcom.upwork.domain.AppUser;
import agency.akcom.upwork.domain.UpworkUserSettings;
import agency.akcom.upwork.server.dao.UpworkUserSettingsDao;
import agency.akcom.upwork.server.upwork.UpworkAuthClient;
import agency.akcom.upwork.server.upwork.request.job.Job;
import agency.akcom.upwork.server.upwork.request.job.JobClient;
import agency.akcom.upwork.server.upwork.request.job.request.JobQuery;
import com.Upwork.api.OAuthClient;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
public class MailingJobTask extends AbstractTask {

	private static final TimeZone UPWORK_TZ = TimeZone.getTimeZone("Etc/GMT+0");
	private static final TimeZone MOSCOW_TZ = TimeZone.getTimeZone("Europe/Moscow");
	private static final SimpleDateFormat MOSCOW_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	private static final String HTML_BODY = readTemplate("/message/body_template.html");
	private static final String HTML_BLOCK = readTemplate("/message/block_template.html");
	{
		MOSCOW_DATE_FORMAT.setTimeZone(MOSCOW_TZ);
	}
	public static Date yesterday;

	UpworkUserSettingsDao upworkUserSettingsDao;

	@Override
	public void run() {
		upworkUserSettingsDao = new UpworkUserSettingsDao();
		yesterday = getYesterday(); // set yesterday
		// Get user list
		List<UpworkUserSettings> userSettings = upworkUserSettingsDao.listAll();

		// Every user
		for (int i = 0; i < userSettings.size(); i++) {
			// Get user credentials
			OAuthClient client = getOAuthClient(userSettings.get(i));
			// Get request of job
			List<Job> jobs = null;
			try {
				jobs = getListJobs(userSettings.get(i).getQueriesList(), client);
				if(jobs == null){
					continue;
				}
			} catch (JSONException e) {
				log.error(e.getStackTrace().toString());
				continue;
			}
			// Send mail
			try {
				String message = createMessage(jobs);
				log.error("START SENDING MESS");
				sendMessage(message, userSettings.get(i));
			} catch (Exception e) {
				String exceptionStr = "";
				for(StackTraceElement str : e.getStackTrace()){
					exceptionStr += str + "\n";
				}
				log.error(e.getMessage());
				log.error(exceptionStr);
				continue;
			}
		}
		log.error("MAILING SUCCESS");
	}

	public static String createMessage(List<Job> jobs) throws IOException {
		String htmlBodyTemp = HTML_BODY;
		String htmlBlockTemp = HTML_BLOCK;

		String body = "";
		for (Job job : jobs) {
			String block = htmlBlockTemp;

			block = block.replace("$Title", job.getTitle());
			block = block.replace("$Price", job.getBudget() + " / " + job.getJob_type() + " / " + job.getWorkload());
			block = block.replace("$Description", job.getSnippet());
			block = block.replace("$link", job.getUrl());

			body += block + "\n";
		}
		htmlBodyTemp = htmlBodyTemp.replace("$block_template", body);
		return htmlBodyTemp;
	}

	private void sendMessage(String htmlBody, UpworkUserSettings userSettings) throws MessagingException {
		String to = userSettings.getUserDtoRef().get().getEmail();
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		Message msg = null;
		try {
			msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("noreply@uw-assist.appspotmail.com", "Upwork mailing"));
			msg.addRecipient(Message.RecipientType.TO,
					new InternetAddress(to, "Mr. User"));
			msg.setSubject("Upwork's jobs");
			msg.setContent(htmlBody, "text/html");

		} catch (AddressException e) {
			// ...
		} catch (MessagingException e) {
			// ...
		} catch (UnsupportedEncodingException e) {
			// ...
		}
		Transport.send(msg);
	}

	private OAuthClient getOAuthClient(UpworkUserSettings userDtoRef) {
		AppUser appUser = userDtoRef.getUserDtoRef().get();
		UpworkAuthClient upworkClient = new UpworkAuthClient();
		upworkClient.setTokenWithSecret(appUser.getToken(), appUser.getSecret());
		return upworkClient.getOAuthClient();
	}

	public static List<Job> getListJobs(List<JobQuery> jobQueries, OAuthClient client) throws JSONException {
		List<Job> jobsList = new ArrayList();
		// every user's query
		for (int i = 0; i < jobQueries.size(); i++) {
			JobQuery jobQuery = jobQueries.get(i);
			JobClient jobClient = new JobClient(client, jobQuery);
			jobsList.addAll(jobClient.getJobListBeforeDate(yesterday));
		}
		return jobsList;
	}

	public List<UpworkUserSettings> getUpworkSettingsList() {
		UpworkUserSettingsDao upworkUserSettingsDao = new UpworkUserSettingsDao();
		return upworkUserSettingsDao.getAllEntities();
	}

	public static Date getYesterday() {
		Date now = new Date();
		GregorianCalendar gCalendar = new GregorianCalendar(MOSCOW_TZ);
		gCalendar.setTime(now);
		gCalendar.add(Calendar.DAY_OF_YEAR, -1); // yesterday
		gCalendar.add(Calendar.HOUR_OF_DAY, -1); // -1 hour
		return gCalendar.getTime();
	}

	public static String readTemplate(String path){
		InputStream is = MailingJobTask.class.getResourceAsStream(path);
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String template = "";
		String str = null;
		while(true) {
			try {
				if (!((str = reader.readLine()) != null)) break;
			} catch (IOException e) {
				e.printStackTrace();
			}
			template += str;
		}
		return template;
	}

	@Override
	protected String getUniqueKey() {
		return "";
	}
}
