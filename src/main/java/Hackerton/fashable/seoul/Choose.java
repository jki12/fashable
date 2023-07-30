package Hackerton.fashable.seoul;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Scanner;

public class Choose {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. 경제");
        System.out.println("2. 복지");
        System.out.println("3. 문화");
        System.out.print("크롤링할 사이트를 선택하세요 (1, 2, 3 중 하나 입력): ");
        int choice = scanner.nextInt();

        String url = "";
        switch (choice) {
            case 1:
                url = "https://news.seoul.go.kr/economy/news-all";
                break;
            case 2:
                url = "https://news.seoul.go.kr/welfare/news-all";
                break;
            case 3:
                url = "https://news.seoul.go.kr/culture/news-all";
                break;
            default:
                System.out.println("잘못된 입력입니다. 프로그램을 종료합니다.");
                return;
        }

        try {
            // 해당 URL의 HTML 문서를 가져옵니다.
            Document document = Jsoup.connect(url).get();

            // 게시글 제목이 포함된 HTML 요소를 선택합니다.
            Elements titleElements = document.select(".news-list-title > a");

            System.out.println("----- " + url + " -----");
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