package agency.akcom.upwork.shared.action;

import agency.akcom.upwork.shared.dto.UserDto;
import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.Out;
import com.gwtplatform.dispatch.rpc.shared.UnsecuredActionImpl;

import java.util.List;

@GenDispatch(isSecure = false, serviceName = UnsecuredActionImpl.DEFAULT_SERVICE_NAME)
public class GetUsers {

    @Out(1)
    List<UserDto> users;

}
