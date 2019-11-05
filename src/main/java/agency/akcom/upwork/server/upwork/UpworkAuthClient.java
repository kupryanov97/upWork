package agency.akcom.upwork.server.upwork;

import com.Upwork.api.Config;
import com.Upwork.api.OAuthClient;

import java.util.HashMap;
import java.util.Properties;
public class UpworkAuthClient {

    public static final String CONSUMERKEY = "f0342e643de215d60b20777e70a141cc";
    public static final String CONSUMERSECRET = "f92d51246f9e2da9";
    public static final String OAYTРCALLBACK = "https://uw-assist.appspot.com/main2";

    OAuthClient client;

    public UpworkAuthClient() {
        Properties keys = new Properties();
        keys.setProperty("consumerKey", CONSUMERKEY);
        keys.setProperty("consumerSecret", CONSUMERSECRET);
        Config config = new Config(keys);
        client = new OAuthClient(config);
    }

    public void setTokenWithSecret (String token, String secret){
        client.setTokenWithSecret(token, secret);
    }

    public HashMap<String,String> setTokenWithSecret2 (String token, String secret){
        return client.setTokenWithSecret(token, secret);
    }

    public void setOAuthClient (OAuthClient oAuthClient) {
        this.client = oAuthClient;
    }

    public OAuthClient getOAuthClient() {
        return client;
    }

    public String getAuthorizationUrl() {
        return this.client.getAuthorizationUrl(OAYTРCALLBACK);
    }
}
