package agency.akcom.upwork.server.upwork.request.job.request;

import com.googlecode.objectify.annotation.Ignore;

import java.io.Serializable;
import java.util.HashMap;

public class JobQuery extends AbstractQuery implements Serializable {
    @Ignore
    public int curientOffset;
    @Ignore
    public int currientCount;

    public JobQuery() {
        queryParam = new HashMap<>();
        setDefaultPagination();
    }

    public JobQuery(HashMap<String, String> queryParams) {
        this.queryParam = queryParams;
        setPagination();
    }

    public void setQueryParams(HashMap<String, String> queryParams) {
        this.queryParam = queryParams;
        setPagination();
    }

    public void setHashMap(HashMap<String, String> queryParams) {
        this.queryParam = queryParams;
    }

    public void setQuery(String query) {
        queryParam.put("q", query);
    }

    public String getQuery() {
        return queryParam.get("q");
    }

    public void setTitle(String title) {
        queryParam.put("title", title);
    }

    public void setSkills(String skills) {
        queryParam.put("skills", skills);
    }

    public void setCategory2(String category2) {
        queryParam.put("category2", category2);
    }

    public void setSubcategory2(String subcategory2) {
        queryParam.put("subcategory2", subcategory2);
    }

    public void setJob_type(String job_type) {
        queryParam.put("job_type", job_type);
    }

    public void setDuration(String duration) {
        queryParam.put("duration", duration);
    }

    public void setWorkload(String workload) {
        queryParam.put("workload", workload);
    }

    public void setClient_feedback(String client_feedback) {
        queryParam.put("client_feedback", client_feedback);
    }

    public void setClient_hires(String client_hires) {
        queryParam.put("client_hires", client_hires);
    }

    public void setBudget(String budget) {
        queryParam.put("budget", budget);
    }

    public void setJob_status(String job_status) {
        queryParam.put("job_status", job_status);
    }

    public void setDays_posted(int days_posted) {
        queryParam.put("days_posted", days_posted + "");
    }

    private void setPaging(int offset, int count) {
        queryParam.put("paging", offset + ";" + count);
    }

    //default 0;10
    private void setDefaultPagination() {
        curientOffset = 0;
        currientCount = 10;
        setPaging(curientOffset, currientCount);
    }

    private void setPagination() {
        String[] pagination = queryParam.get("paging").split(";");
        curientOffset = Integer.parseInt(pagination[0]);
        currientCount = Integer.parseInt(pagination[1]);
    }

    public void setNextPage() {
        curientOffset += 10;
        setPaging(curientOffset, currientCount);
    }

    public void setPreviousPage() {
        curientOffset -= 10;
        setPaging(curientOffset, currientCount);
    }
}