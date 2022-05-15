import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Information {
    public static void main(String[] args) throws IOException {
        String html = "https://api.github.com/repos/jmapper-framework/jmapper-core";
        URL url = new URL(html);
        HttpURLConnection conn=(HttpURLConnection) url.openConnection();
        conn.connect();
        InputStreamReader reader;
        BufferedReader in;
        reader = new InputStreamReader(conn.getInputStream());
        in = new BufferedReader(reader);
        String line;
        StringBuilder content = new StringBuilder();
        while ((line = in.readLine()) != null) {
            content.append(line);
        }
//        System.out.println(content);
        JSONObject jsonObject=new JSONObject(new String(content));
        System.out.println(jsonObject.get("stargazers_count"));//star
        System.out.println(jsonObject.get("forks_count"));//fork
        System.out.println(jsonObject.get("open_issues_count"));//issues
        System.out.println(jsonObject.get("subscribers_count"));//watch
    }
}
