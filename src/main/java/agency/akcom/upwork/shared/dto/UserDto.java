package agency.akcom.upwork.shared.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserDto extends Dto {
    private static final long serialVersionUID = 1L;

    private boolean isLoggedIn;

    private String login;

    private String email;

    private String googleId;

    private boolean isAdmin;

    private String pictureURL;
    private String time;
    private String timeModified;

    private String oauth_token;//For UpWork
    private String oauth_oauth_verifier;//For UpWork


    private HashMap<Integer,String> hashMap;

    public UserDto() {
        // not logged in user default values

        isLoggedIn = false;
        isAdmin = false;

    }

    public void setAttributes(boolean isLoggedIn, Long id, String login, boolean isAdmin, String pictureURL, String time ,String timeModified, String googleId , String email) {
        setId(id);
        this.isLoggedIn = isLoggedIn;
        this.login = login;
        this.isAdmin = isAdmin;
        this.pictureURL = pictureURL;
        this.time = time;
        this.googleId = googleId;
        this.email = email;
        this.timeModified = timeModified;
    }

    public void setTokenUpWork(String oauth_token,String oauth_oauth_verifier){
        this.oauth_token = oauth_token;
        this.oauth_oauth_verifier = oauth_oauth_verifier;
    }

    public String getTime() {
        return time;
    }

    public String getTimeModified(){
        return timeModified;
    }

    public String getLogin() {
        return login;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public String getEmail() {
        return email;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public String getGoogleId(){
        return googleId;
    }

    public void copyFrom(UserDto userToCopy) {
        isLoggedIn = userToCopy.isLoggedIn;
        setId(userToCopy.getId());
        login = userToCopy.login;
        pictureURL = userToCopy.pictureURL;
        isAdmin = userToCopy.isAdmin;
        time = userToCopy.time;
        googleId = userToCopy.googleId;
        email = userToCopy.email;
        timeModified = userToCopy.timeModified;
    }

    public HashMap<Integer, String> getHashMap() {
        return hashMap;
    }

    public void setHashMap(HashMap<Integer, String> hashMap) {
        this.hashMap = hashMap;
    }
}
