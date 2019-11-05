package agency.akcom.upwork.server.upwork.request;

import agency.akcom.upwork.server.upwork.UpworkAuthClient;
import agency.akcom.upwork.server.upwork.request.job.Job;
import agency.akcom.upwork.server.upwork.request.job.JobClient;
import agency.akcom.upwork.server.upwork.request.job.request.JobQuery;
import com.Upwork.api.Routers.Freelancers.Search;
import com.Upwork.api.Routers.Organization.Users;
import org.json.JSONException;

import java.util.List;

public class test {
    public static void main(String[] args) throws JSONException {
        UpworkAuthClient upworkClient = new UpworkAuthClient();
        upworkClient.setTokenWithSecret("TOKEN ", "SECRET");

        JobQuery jobQuery = new JobQuery();
        jobQuery.setQuery("google cloud");

        JobClient jobClient = new JobClient(upworkClient.getOAuthClient(), jobQuery);

        List<Job> jobs = jobClient.getJob();
        while (jobClient.hasNextPage()){
            jobs.addAll(jobClient.getNextPage());
        }

        boolean next = jobClient.hasNextPage();
        boolean next2 = jobClient.hasNextPage();
        System.out.println("end");



    }

}
