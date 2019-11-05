package agency.akcom.upwork.server.upwork.request.job;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.Upwork.api.OAuthClient;
import com.Upwork.api.Routers.Jobs.Search;

import agency.akcom.upwork.server.upwork.request.job.request.JobQuery;
@Slf4j
public class JobClient {
	private JobQuery jobQuery;
	private Search jobs;
	private JSONObject pagination;
	private List<Job> jobList;
	private static final TimeZone UPWORK_TZ = TimeZone.getTimeZone("Etc/GMT+0");
	private static final SimpleDateFormat UPWORK_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	{
		UPWORK_DATE_FORMAT.setTimeZone(UPWORK_TZ);
	}

	public JobClient(OAuthClient oAuthClient, JobQuery jobQuery) {
		jobs = new Search(oAuthClient);
		this.jobQuery = jobQuery;
	}

	public List<Job> getJob() throws JSONException {
		JSONObject job = jobs.find(jobQuery.getQueryParam());
		try{
			pagination = (JSONObject) job.get("paging");
		} catch (Exception e){
			log.error("PAGINATION NOT FOUND (ERROR) " + job.toString());
		}

		jobList = parseResponse(job);
		return jobList;
	}

	public List<Job> getJobListBeforeDate(Date date) throws JSONException {
		boolean jobCreatedBefore = false;
		List<Job> jobs = this.getJob();
		boolean first = false;
		do {
			if (first) {
				jobs.addAll(getNextPage());
			}
			for (int i = jobs.size()-1; i > 0; i--) {
				Date job_created = null;
				try {
					job_created = UPWORK_DATE_FORMAT.parse(jobs.get(i).getDate_created());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if (job_created.before(date)) {
					jobs.remove(i);
					jobCreatedBefore = true;
				} else {
					break;
				}
			}
			if (jobCreatedBefore) {
				return jobs;
			}
			first = true;
		} while (this.hasNextPage());
		return jobs;
	}

	public List<Job> getNextPage() throws JSONException {
		jobQuery.setNextPage();
		JSONObject nextPage = jobs.find(jobQuery.getQueryParam());
		pagination = (JSONObject) nextPage.get("paging");
		jobList = parseResponse(nextPage);
		return jobList;
	}

	public boolean hasNextPage() throws JSONException {
		int total = (int) pagination.get("total");
		int offset = (int) pagination.get("offset");
		if ((total - offset) > 10) {
			return true;
		}
		return false;
	}

	public List<Job> getPreviousPage() throws JSONException {
		jobQuery.setPreviousPage();
		JSONObject previousPage = jobs.find(jobQuery.getQueryParam());
		pagination = (JSONObject) previousPage.get("paging");
		jobList = parseResponse(previousPage);
		return jobList;
	}

	public boolean hasPreviousPage() throws JSONException {
		int offset = (int) pagination.get("offset");
		if (offset > 0) {
			return true;
		}
		return false;
	}

	private static List<Job> parseResponse(JSONObject jsonObject) throws JSONException {
		List<Job> jobList = new ArrayList<>(10);
		JSONArray jobsArray = (JSONArray) jsonObject.get("jobs");
		for (int i = 0; i < jobsArray.length(); i++) {
			JSONObject jsonJob = (JSONObject) jobsArray.get(i);
			Job job = new Job(jsonJob);
			jobList.add(job);
		}
		return jobList;
	}
}
