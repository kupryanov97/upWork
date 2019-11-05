package agency.akcom.upwork.server.dao.objectify;

import com.google.appengine.api.datastore.ReadPolicy.Consistency;
import com.google.inject.Inject;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

public class OfyService {
    @Inject
    public static void setObjectifyFactory(OfyFactory factory) {
        ObjectifyService.setFactory(factory);
    }

    public static Objectify ofy() {
        return ObjectifyService.ofy().consistency(Consistency.STRONG);
    }

    public static OfyFactory factory() {
        return (OfyFactory) ObjectifyService.factory();
    }
}
