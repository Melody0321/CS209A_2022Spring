import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JSCrawler {
    public static void main(String[] args) throws IOException, InterruptedException {
        String s = "https://github.com/topics/java?l=java&o=desc&s=stars&page=";
        int index = 1;
        FileWriter writer = new FileWriter("url.txt");
        while (index<=34){
            String temp = s + index;
            URL url = new URL(temp);
            try{
                HttpURLConnection conn=(HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder content=new StringBuilder();
                String line=bufferedReader.readLine();
                while (line!=null){
                    content.append(line);
                    line=bufferedReader.readLine();

                }
                bufferedReader.close();
                Document document = Jsoup.parse(content.toString());
                Elements elements = document.getElementsByTag("article");
                for (Element e: elements){
                    Elements h3 = e.getElementsByTag("h3");
                    if (h3.size()==0){
                        continue;
                    }
                    for (Element h: h3){
                        Elements a = h.getElementsByTag("a");
                        if (a.size()!=2){
                            continue;
                        }
                        Element tar = a.get(1);
                        writer.write(tar.text()+"\n"+"https://github.com"+tar.attr("href")+"\n");
                        writer.flush();
                    }
                }
                System.out.println("PAGE:"+index);
                index++;
                conn.disconnect();
            } catch (Exception e) {
                Thread.sleep(30000);
                System.out.println(temp);
            }

        }
        writer.close();
    }
}
