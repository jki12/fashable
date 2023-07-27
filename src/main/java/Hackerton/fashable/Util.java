package Hackerton.fashable;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Util {
    private static final String FILE_URL = "./rawData.csv";

    public static void parseAndCreateFile() throws Exception {
        var br = new BufferedReader(new FileReader(new File(FILE_URL)));
        Set<String> set = new HashSet<>();

        br.readLine(); // Skip csv header row.

        String s;
        while ((s = br.readLine()) != null) {
            var list = s.split(" ");

            for (int i = 0; i < list.length; ++i) {
                String temp = list[i];

                if (temp.indexOf("도") != -1 || temp.indexOf("시") != -1 || temp.indexOf("군") != -1 || temp.indexOf("구") != -1) set.add(temp);
            }
        }

        var sb = new StringBuilder();
        for (var e : set) sb.append(e + System.lineSeparator());

        var bw = new BufferedWriter(new FileWriter(new File("./parsed.txt")));

        bw.write(sb.toString());
        bw.flush();

        System.out.printf("done! - %d", set.size());
    }
}
