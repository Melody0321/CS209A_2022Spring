import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

public class Topic {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
       BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("./data-processing/test.txt")));
        String html;
        Driver driver = new org.postgresql.Driver();
        Properties prop = new Properties();
        prop.setProperty("user", "checker");
        prop.setProperty("password", "123456");
        Connection connection= driver.connect("jdbc:postgresql://localhost:5432/java2", prop);
        assert connection != null;
        Statement statement=connection.createStatement();
        int id=0;
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
            JSONObject jsonObject = new JSONObject(new String(content));

            JSONArray array = jsonObject.getJSONArray("topics");
            for (int i = 0; i < array.length(); i++) {
                statement.executeUpdate("insert into project_topic(id,topic) values (" +
                        id + ",'" + array.get(i) + "');");
            }
        }



    }
}
