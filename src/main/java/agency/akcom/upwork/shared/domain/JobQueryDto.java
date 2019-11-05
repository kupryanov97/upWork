package agency.akcom.upwork.shared.domain;

import agency.akcom.upwork.shared.dto.Dto;
import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;
import java.util.HashMap;

public class JobQueryDto implements IsSerializable {
    public HashMap<String, String> queryParam;

    public JobQueryDto(){
        queryParam = new  HashMap<String, String>();
    }

    public void setQueryParam(HashMap<String, String> queryParam){
        this.queryParam = queryParam;
    }

    public HashMap<String, String> getQueryParam() {
        return queryParam;
    }

    public void setQuery(String query) {
        queryParam.put("q", query);
    }

    public String getQuery() {
        return queryParam.get("q");
    }

    public String getClienthires(){
        return queryParam.get("client_hires");
    }

}
