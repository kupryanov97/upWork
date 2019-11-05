package agency.akcom.upwork.shared.action;

import agency.akcom.upwork.shared.domain.JobQueryDto;
import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.In;
import com.gwtplatform.dispatch.rpc.shared.UnsecuredActionImpl;

@GenDispatch(isSecure = false, serviceName = UnsecuredActionImpl.DEFAULT_SERVICE_NAME)
public class SaveJobQuery{

    @In(1)
    JobQueryDto job;

    @In(2)
    Long userId;


}
