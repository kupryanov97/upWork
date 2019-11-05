package agency.akcom.upwork.server.upwork.request.job;

import agency.akcom.upwork.server.upwork.request.job.request.Client;
import org.json.JSONException;
import org.json.JSONObject;

public class Job {
    private String snippet;
    private String category2;
    private String job_type;
    private String date_created;
    private String workload;
    private String title;
    private String url;
    private String skills; //array
    private String duration;
    private String subcategory2;
    private String job_status;
    private String id;
    private String budget;
    Client client;

    public Job (JSONObject jsonObject) throws JSONException {
        snippet = jsonObject.get("snippet").toString();
        category2 = jsonObject.get("category2").toString();
        job_type = jsonObject.get("job_type").toString();
        date_created = jsonObject.get("date_created").toString();
        workload = jsonObject.get("workload").toString();
        title = jsonObject.get("title").toString();
        url = jsonObject.get("url").toString();
        skills = jsonObject.get("skills").toString();
        duration = jsonObject.get("duration").toString();
        subcategory2 = jsonObject.get("subcategory2").toString();
        job_status = jsonObject.get("job_status").toString();
        client = new Client((JSONObject) jsonObject.get("client"));
        id = jsonObject.get("id").toString();
        budget = jsonObject.get("budget").toString();
    }

    public String getSnippet() {
        return snippet;
    }

    public String getCategory2() {
        return category2;
    }

    public String getJob_type() {
        return job_type;
    }

    public String getDate_created() {
        return date_created;
    }

    public String getWorkload() {
        return workload;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getSkills() {
        return skills;
    }

    public String getDuration() {
        return duration;
    }

    public String getSubcategory2() {
        return subcategory2;
    }

    public String getJob_status() {
        return job_status;
    }

    public String getId() {
        return id;
    }

    public String getBudget() {
        return budget;
    }

    public Client getClient() {
        return client;
    }
}
