import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Sort {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("./data-processing/project.txt"));
        String line;
        Map<String, Integer> map = new HashMap<>();
        while ((line = bufferedReader.readLine()) != null) {
            String[] s = line.split(" ");
            String name = s[0];
            int star = Integer.parseInt(s[2]);
            map.put(name, star);

        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> o2.getValue() - o1.getValue());
        list.forEach(entry -> {
            System.out.println("key:" + entry.getKey() + ",value:" + entry.getValue());

        });
    }
}
