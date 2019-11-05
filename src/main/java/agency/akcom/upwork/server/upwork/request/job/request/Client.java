package agency.akcom.upwork.server.upwork.request.job.request;

import org.json.JSONException;
import org.json.JSONObject;

public class Client {
    private String feedback;
    private String country;
    private String past_hires;
    private String payment_verification_status;
    private String jobs_posted;
    private String reviews_count;

    public Client(JSONObject jsonObject) throws JSONException {
        feedback = jsonObject.get("feedback").toString();
        country = jsonObject.get("country").toString();
        past_hires = jsonObject.get("past_hires").toString();
        payment_verification_status = jsonObject.get("payment_verification_status").toString();
        jobs_posted = jsonObject.get("jobs_posted").toString();
        reviews_count = jsonObject.get("reviews_count").toString();
    }

    public String getFeedback() {
        return feedback;
    }

    public String getCountry() {
        return country;
    }

    public String getPast_hires() {
        return past_hires;
    }

    public String getPayment_verification_status() {
        return payment_verification_status;
    }

    public String getJobs_posted() {
        return jobs_posted;
    }

    public String getReviews_count() {
        return reviews_count;
    }
}
