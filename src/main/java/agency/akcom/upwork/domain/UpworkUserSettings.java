package agency.akcom.upwork.domain;

import agency.akcom.upwork.server.upwork.request.job.request.JobQuery;
import agency.akcom.upwork.shared.domain.DatastoreObject;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Getter
@Setter
@Entity
public class UpworkUserSettings extends DatastoreObject {
    private static final long serialVersionUID = 1L;

    @Index
    @Parent
    Ref<AppUser> userDtoRef;

    List<String> emailsList;

    List<JobQuery> queriesList;

    public UpworkUserSettings() {
    }
}
