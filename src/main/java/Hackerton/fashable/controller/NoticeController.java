package Hackerton.fashable.controller;

import Hackerton.fashable.dto.notice.NoticeDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class NoticeController {
    private static final String[] PATHS = {
            // 순서는 경제, 문화, 복지로 보장 되어야 함
            "ecnmnotice", "culture0101", "welnotice", "economy/news-all", "culture/news-all", "welfare/news-all"
    };

    public static ArrayList<NoticeDto> parse(String cssQuery, String url, String baseUrl) {
        assert (baseUrl != null);

        ArrayList<NoticeDto> res = new ArrayList<>();
        var conn = Jsoup.connect(url);

        try {
            Document doc = conn.get();

            if (doc.connection().response().statusCode() == 200) {
                Elements notices = doc.select(cssQuery);

                for (var notice : notices) res.add(new NoticeDto(notice.text(), baseUrl + notice.attr("href")));

                assert (notices.size() <= 30);
            }

            return res;
        } catch (Exception e) {
            e.printStackTrace(); // logging.

            return res;
        }
    }

    private int toIndex(String part) {
        switch (part) {
            case "eco":
                return 1;
            case "cul":
                return 2;
            case "wel":
                return 3;
            default:
                assert (false);

                return -1;
        }
    }

    @GetMapping("notices/{region}/{part}")
    public Object getNotices(@PathVariable("region") String region, @PathVariable("part") String part) {
        String url = "";

        switch (region) {
            case "Busan":
                url += "https://www.busan.go.kr/depart/" + PATHS[toIndex(part) - 1];

                return parse(".title > a", url, "https://www.busan.go.kr");
            case "Seoul":
                // intentional fall-through
            default:
                url += "https://news.seoul.go.kr/" + PATHS[(toIndex(part) + PATHS.length / 2) -1];

                return parse(".tit > a", url, "");
        }
    }
}
