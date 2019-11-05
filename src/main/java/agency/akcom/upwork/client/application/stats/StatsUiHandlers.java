package agency.akcom.upwork.client.application.stats;


import agency.akcom.upwork.shared.dto.UserDto;
import com.gwtplatform.mvp.client.UiHandlers;

public interface StatsUiHandlers extends UiHandlers {
    void onUserDeleteUpdate(UserDto userDto);
    void GetTable();
    void onIsAdminUpdate(UserDto userDto, Boolean value);

}
