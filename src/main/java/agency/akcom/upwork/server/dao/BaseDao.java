package agency.akcom.upwork.server.dao;

import com.google.api.client.util.Lists;
import com.google.appengine.api.datastore.PreparedQuery;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.LoadType;
import com.googlecode.objectify.cmd.Query;

import java.util.*;

import static agency.akcom.upwork.server.dao.objectify.OfyService.ofy;

public abstract class BaseDao<T> {
    private final Class<T> clazz;

    protected BaseDao(final Class<T> clazz) {
        this.clazz = clazz;
    }

    public List<T> listAll() {
        return ofy().load().type(clazz).list();
    }

    public void save(T entity) {
        ofy().save().entity(entity);
    }

    public void save(Iterable<T> entities) {
        ofy().save().entities(entities);
    }

    public void saveNow(Iterable<T> entities) {
        ofy().save().entities(entities).now();
    }

    public Key<T> saveNow(T entity) {
        return ofy().save().entity(entity).now();
    }

    public T saveAndReturn(T entity) {
        // saveNow(entity);
        // return entity;
        return get(saveNow(entity));
    }

    public Key<T> saveAndReturnKey(T entity) {
        return ofy().save().entity(entity).now();
    }

    public Collection<T> saveAndReturn(Iterable<T> entities) {
        return ofy().save().entities(entities).now().values();
    }

    public T get(Key<T> key) {
        return ofy().load().key(key).now();
    }

    public T getByKey(String runPartKeyString) {
        Key<T> key = Key.create(runPartKeyString);
        return get(key);
    }

    public T get(Long id) {
        // TODO probably it could be fixed by parameters of
        // work around for objectify cacheing and new query not having the
        // latest
        // data
        // ofy().clear();

        return ofy().load().type(clazz).id(id).now();
    }

    public T getById(String id) {

        return get(Long.valueOf(id));
    }

    public T getByProperty(String propName, Object propValue) throws PreparedQuery.TooManyResultsException {
        Query<T> q = ofy().load().type(clazz);
        q = q.filter(propName, propValue);
        Iterator<T> fetch = q.limit(2).list().iterator();
        if (!fetch.hasNext()) {
            return null;
        }
        T obj = fetch.next();
        if (fetch.hasNext()) {
            throw new PreparedQuery.TooManyResultsException();
        }
        return obj;
    }

    public Boolean exists(Key<T> key) {
        return get(key) != null;
    }

    public Boolean exists(Long id) {
        return get(id) != null;
    }

    public List<T> getSubset(List<Long> ids) {
        return new ArrayList<T>(ofy().load().type(clazz).ids(ids).values());
    }

    public Map<Long, T> getSubsetMap(List<Long> ids) {
        return new HashMap<Long, T>(ofy().load().type(clazz).ids(ids));
    }

    public void delete(T object) {
        ofy().delete().entity(object);
    }

    public void delete(Long id) {
        ofy().delete().type(clazz).id(id);
    }

    public void delete(List<T> objects) {
        ofy().delete().entities(objects);
    }

    public List<T> get(List<Key<T>> keys) {
        return Lists.newArrayList(ofy().load().keys(keys).values());
    }

    protected LoadType<T> query() {
        return ofy().load().type(clazz);
    }

    public T getByAncestor(Object ancestor) {
        return ofy().load().type(clazz).ancestor(ancestor).chunkAll().first().now();
    }

}
