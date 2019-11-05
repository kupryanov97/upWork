package agency.akcom.upwork.client.application.Search;

import agency.akcom.upwork.shared.domain.JobQueryDto;
import com.gwtplatform.mvp.client.UiHandlers;

import java.util.HashMap;

interface SearchUiHandlers extends UiHandlers {
    void setSetting(HashMap<String, String> hashMap);
    void getSetting();
    void searchWork();
}

