package biz.blaiq.blaiqmedialtd;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.HashMap;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class SplashwiAPI {
    private String uri = "https://blaiq.biz/api/";

    public Map execute(String myUri, String method, HashMap<String, HashMap<String, String>> data) throws IOException {
        HashMap<String, String> auth = data.get("auth");
        HashMap<String, String> postdata = data.get("data");

        this.uri = myUri + "api/";

        URL url = new URL(this.uri + method);
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
        httpCon.setDoOutput(true);
        httpCon.setDoInput(true);
        httpCon.setInstanceFollowRedirects(true);
        httpCon.setRequestMethod("POST");
        httpCon.setRequestProperty("Accept", "*/*");
        httpCon.setRequestProperty("Connection", "Keep-Alive");
        httpCon.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U;  Windows NT 5.0; en-US; rv:1.7.12)     Gecko/20050915 Firefox/1.0.7");

        httpCon.setRequestProperty("auth[API_KEY]", auth.get("API_KEY"));
        httpCon.setRequestProperty("auth[API_USERNAME]", auth.get("API_USERNAME"));
        httpCon.setRequestProperty("auth[API_PASSWORD]", auth.get("API_PASSWORD"));

        for(Map.Entry<String, String> entry : postdata.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            httpCon.setRequestProperty("data[" + key + "]", value);
        }
                        
        OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());

        String ret = out.toString();
        Map<String, String> result = this.jsonToMap(ret);

        return result;
    }

    public Map<String, String> jsonToMap(String json) {
        Map<String, String> map = new HashMap<String, String>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            //convert JSON string to Map
            map = mapper.readValue(json, new TypeReference<HashMap<String, String>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
