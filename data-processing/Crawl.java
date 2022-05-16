import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

public class Crawl {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException, InterruptedException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("./data-processing/url.txt")));
        String html;
        int id=0;
        BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter("./data-processing/topic.txt"));
        BufferedWriter bufferedWriter1=new BufferedWriter(new FileWriter("./data-processing/project.txt"));
        BufferedWriter bufferedWriter2=new BufferedWriter(new FileWriter("./data-processing/time.txt"));
        while ((html = bufferedReader.readLine()) != null) {
            if (!html.contains("https:")) {
                continue;
            }
            id++;
            int lastOne = html.lastIndexOf("/");
            int lastTwo = html.lastIndexOf("/", lastOne - 1);
            int lastThree = html.lastIndexOf("/", lastTwo - 1);

            String name;
            if (lastOne == html.length() - 1) {
                name = html.substring(lastThree + 1, lastTwo - 1);
            } else {
                name = html.substring(lastTwo + 1);
            }
            System.out.println(name);
            URL url = new URL("https://api.github.com/repos/"+name);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
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
            Thread.sleep(2000);
            JSONObject jsonObject = new JSONObject(new String(content));

            bufferedWriter1.write(name+" "+html+" "+jsonObject.get("stargazers_count")+" "+jsonObject.get("open_issues_count")+" "
                            +jsonObject.get("subscribers_count")+" "+jsonObject.get("forks_count")+"\r\n");
            bufferedWriter2.write(id+" "+jsonObject.get("created_at")+" "+jsonObject.get("updated_at")+"\r\n");
            JSONArray array = jsonObject.getJSONArray("topics");
            for (int i = 0; i < array.length(); i++) {
                bufferedWriter.write(id+" "+array.get(i)+"\r\n");
            }
            bufferedWriter.flush();
            bufferedWriter1.flush();
            bufferedWriter2.flush();
        }
        bufferedWriter.close();
        bufferedWriter1.close();
        bufferedWriter2.close();
    }
}
