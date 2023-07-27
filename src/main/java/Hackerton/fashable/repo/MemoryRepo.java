package Hackerton.fashable.repo;

import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.HashMap;

@Repository
public class MemoryRepo {
    private static final String EMPTY = "";
    private static final String url = "./src/main/java/Hackerton/fashable/repo/district.csv";
    private HashMap<String, String> map = new HashMap<>();

    public void init() throws IOException {
        if (map.size() != 0) return;

        var br = new BufferedReader(new FileReader(new File(url)));

        br.readLine(); // ignore csv header.

        String s;
        while ((s = br.readLine()) != null) {
            var list = s.split(", ");

            map.put(list[0], list[1]);
        }
    }

    public String findById(final String key) {
        if (!map.containsKey(key)) return EMPTY; // none.

        return map.get(key);
    }
}
