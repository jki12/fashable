package Hackerton.fashable.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 초기페이지를 구성하는 프론트(ex: html) 파일을 읽어들이는 클래스
@Controller
public class HomeController {
    //기본 요청 메서드
    @GetMapping("/")
    public String index(){      //초기페이지 변수명에 따라 'index' 부분 변경 가능
        return "index";  //초기페이지 프론트(ex: html) 파일로 찾아감, 'index' 부분 변경 가능
    }
}
