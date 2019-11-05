package agency.akcom.upwork.server;

import agency.akcom.upwork.server.task.MailingJobTask;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class CronServlet extends HttpServlet {

    private static final String TASK_PARAMETER = "task";
    private static final String FORCE_PARAMETER = "force";

    private static final String MAILING_JOBS = "mailing_jobs";

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        log.info("Starting cron execution...");

        String taskType = req.getParameter(TASK_PARAMETER);
        boolean force = "true".equals(req.getParameter(FORCE_PARAMETER));

        if (MAILING_JOBS.equals(taskType)) {
            log.info("Running cron job: " + taskType);
            MailingJobTask task = new MailingJobTask();
            task.enqueue();
            // TODO add more task dispatching here
        } else {
            log.warn(taskType != null ? "No implementation defined for cron task " + taskType
                    : "Parameter <" + TASK_PARAMETER + "> missing in cron url");
        }
    }
}
