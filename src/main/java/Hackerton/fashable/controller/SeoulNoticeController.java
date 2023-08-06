package Hackerton.fashable.controller;

import Hackerton.fashable.Part;
import Hackerton.fashable.dto.notice.NoticeDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.util.ArrayList;

@RestController
public class SeoulNoticeController {
    public static ArrayList<NoticeDto> parse(String url) {
        return parse(".title > a", url, "https://www.seoul.go.kr/main/index.jsp");
    }

    public static ArrayList<NoticeDto> parse(String cssQuery, String url, String baseUrl) {
        baseUrl = (baseUrl == null ? "" : baseUrl);

        assert (baseUrl.charAt(baseUrl.length() - 1) != '/');

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

    // TODO - 현재 String part를 사용하는 부분을 enum값으로 변경하고 switch문으로 변경, 명칭, 예외, 오류 발생시 행동은 팀원과 회의 후 변경 예정입니다.
    @GetMapping("notices/{part}")
    public Object getNotices(@PathVariable("part") String part) {
        String url;

        if (part.equals("eco")) url = "https://news.seoul.go.kr/economy/news-all"; // 경제
        else if (part.equals("cul")) url = "https://news.seoul.go.kr/culture/news-all"; // 문화
        else if (part.equals("wel")) url = "https://news.seoul.go.kr/welfare/news-all"; // 복지
        else url = "https://news.seoul.go.kr/economy/archives/category/jobplan-news_c1/news_job-news-n1"; // 일자리

        assert (url != null || !url.equals(""));

        return parse(url);
    }
}
