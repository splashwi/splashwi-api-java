import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import xyz.example.SplashwiAPI;

public class Example {
    public boolean exampleExternalAuth() {
        try {
            SplashwiAPI splashwiAPI = new SplashwiAPI();

            HashMap<String, String> auth = new HashMap<String, String>();
            HashMap<String, String> data = new HashMap<String, String>();
            HashMap<String, HashMap<String, String>> postdata = new HashMap<String, HashMap<String, String>>();

            auth.put("API_KEY", "");
            auth.put("API_USERNAME", "");
            auth.put("API_PASSWORD", "");

            data.put("USERNAME", mEmail);
            data.put("PASSWORD", mPassword);

            postdata.put("auth", auth);
            postdata.put("data", data);

            try {
                Map<String, String> ret = splashwiAPI.execute("https://blaiq.biz/", "externalauth", postdata);
                if(ret.get("STATUS").equals("SUCCESS")) {
                    return true;
                } else if(ret.get("STATUS").equals("FAIL")) {
                    return false;
                } else {
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}