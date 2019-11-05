package agency.akcom.upwork.domain;


import java.util.HashMap;
import java.util.List;


import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;

import agency.akcom.upwork.server.upwork.request.job.request.JobQuery;
import agency.akcom.upwork.shared.domain.DatastoreObject;
import com.googlecode.objectify.annotation.Mapify;
import com.googlecode.objectify.mapper.Mapper;

@Entity
public class AppUser extends DatastoreObject {

    private static final long serialVersionUID = 1L;

    @Index
    private String login;

    @Index
    private String email;

    @Index
    private String googleId;

    @Index
    private String oauth_token;//For UpWork

    @Index
    private String secret;//For UpWork

    @Index
    private String token;//For UpWork

    @Index
    @Mapify(ForHashMap.class)
    private HashMap<Integer, String> hashMap;

    @Index
    private List<JobQuery> job_query;

    private boolean isAdmin = true;

    private String pictureURL;

    public AppUser() {

    }


    public void setOauth_token(String oauth_token) {
        this.oauth_token = oauth_token;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }


    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPictureURL() {
        return pictureURL != null ? pictureURL : "/images/no-pic.gif";
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public List<JobQuery> getJob_query() {
        return job_query;
    }

    public void setJob_query(List<JobQuery> job_query) {
        this.job_query = job_query;
    }

    public String getOauth_token() {
        return oauth_token;
    }


    public HashMap<Integer, String> getHashMap() {
        return hashMap;
    }

    public void setHashMap(HashMap<Integer, String> hashMap) {
        this.hashMap = hashMap;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}

@Entity
class ForHashMap implements Mapper<Integer,String> {  //https://github.com/objectify/objectify/wiki/Entities

    @Override
    public Integer getKey(String value) {
        return value.hashCode();
    }

}
