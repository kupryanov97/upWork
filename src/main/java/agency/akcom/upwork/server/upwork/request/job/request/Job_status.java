package agency.akcom.upwork.server.upwork.request.job.request;

import java.io.Serializable;

public enum Job_status implements Serializable {
    OPEN{
        String getValue(){
            return "open";
        }
    },
    COMPLETED{
        String getValue(){
            return "completed";
        }
    },
    CANCELLED{
        String getValue(){
            return "cancelled";
        }
    };
    abstract String getValue();
}
