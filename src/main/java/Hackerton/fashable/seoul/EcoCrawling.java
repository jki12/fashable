package Hackerton.fashable.seoul;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class EcoCrawling {
    public static void main(String[] args) {
        String url = "https://news.seoul.go.kr/economy/news-all";
        Document doc = null;
        System.out.println("URL= " + url);

        try {
            // 해당 URL의 HTML 문서를 가져옵니다.
            Document document = Jsoup.connect(url).timeout(10000).get();

            // 게시글 제목이 포함된 HTML 요소를 선택합니다.
            Elements titleElements = document.select(".news-list-title > a");

            // 게시글 제목을 출력합니다.
            for (Element element : titleElements) {
                String title = element.text();
                System.out.println(title);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}