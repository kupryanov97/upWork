package agency.akcom.upwork.server.upwork.request.job.request;

import java.io.Serializable;
import java.util.HashMap;

public abstract class AbstractQuery implements Serializable {
    protected HashMap<String, String> queryParam;

    public HashMap<String, String> getQueryParam() {
        return queryParam;
    }

    public void setQueryParam(HashMap<String, String> queryParam) {
        this.queryParam = queryParam;
    }

}
