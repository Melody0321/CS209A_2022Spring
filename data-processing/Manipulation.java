import java.io.*;

public class Manipulation {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("./data-processing/topic_count.csv"));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("./data-processing/topic.json"));
        String line;
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("{");
        while ((line = bufferedReader.readLine()) != null) {
            String[] s = line.split(",");
            String mString = String.format("[\"%s\": \"%s\",", "name", s[0]);
            String nString = String.format("\"%s\": \"%s\"]", "value", s[1]);
            sBuilder.append(mString).append(nString);
            sBuilder.append(", ");
        }
        sBuilder.deleteCharAt(sBuilder.length() - 2);
        sBuilder.append("}");
        bufferedWriter.write(sBuilder.toString());
        bufferedWriter.flush();
        bufferedWriter.close();


    }

}
