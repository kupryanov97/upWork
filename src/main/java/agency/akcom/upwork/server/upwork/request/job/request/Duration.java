package agency.akcom.upwork.server.upwork.request.job.request;

import java.io.Serializable;

public enum Duration implements Serializable {
    WEEK{
        public String getValue(){
            return "week";
        }
    },
    MONTH{
        public String getValue(){
            return "month";
        }
    },
    ONGOING{
        public String getValue(){
            return "ongoing";
        }
    };
    public abstract String getValue();
}
