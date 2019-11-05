package agency.akcom.upwork.server.upwork.request.job.request;

import java.io.Serializable;

public enum Job_type implements Serializable {
    HOURLY{
        public String getValue(){
            return "hourly";
        }
    },
    FIXED{
        public String getValue(){
            return "fixed";
        }
    };
    public abstract String getValue();
}
