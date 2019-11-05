package agency.akcom.upwork.server.dao.objectify;

import agency.akcom.upwork.server.dao.BaseDao;
import agency.akcom.upwork.server.upwork.request.job.request.JobQuery;

public class JobQueryDao extends BaseDao<JobQuery> {

    JobQueryDao () {
        super(JobQuery.class);
    }

    protected JobQueryDao(Class<JobQuery> clazz) {
        super(clazz);
    }
}
