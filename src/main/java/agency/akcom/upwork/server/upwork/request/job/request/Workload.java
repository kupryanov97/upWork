package agency.akcom.upwork.server.upwork.request.job.request;

import java.io.Serializable;

public enum Workload implements Serializable {
    AS_NEEDED{
        public String getValue(){
            return "as_needed";
        }
    },
    PART_TIME{
        public String getValue(){
            return "part_time";
        }
    },
    FULL_TIME{
        public String getValue(){
            return "full_time";
        }
    };
    public abstract String getValue();
}
