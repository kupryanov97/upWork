package agency.akcom.upwork.client.application.admin;

import agency.akcom.upwork.shared.dto.UserDto;
import com.gwtplatform.mvp.client.UiHandlers;

interface AdminUiHandlers extends UiHandlers {
    void onUserDeleteUpdate(UserDto userDto);
    void GetTable();
    void onIsAdminUpdate(UserDto userDto, Boolean value);

}
