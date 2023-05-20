package com.lec.spring;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@Controller     // Spring에서는 Spring Container에 Controller로 등록됨
public class HomeController {


    // URL -> Handler mapping,
    // url, handler 이름, 뷰 이름  ← 꼭 같은 필요는 없다.
    //      (그러나, 일반적으로는 같거나 동일한 맥락으로 작명한다)

    // HttpServletRequest 객체 (aka. request객체)
    // request 에 담겨있는 정보 (url, parameter, cookie, header, ...)

    //   /common 은 context-path 이하 경로
    @RequestMapping("/common")
    public String common(HttpServletRequest request, Model model) {
        System.out.println("common() 호출 됨");
        String uri = request.getRequestURI();
        String conPath = request.getContextPath();

        // WAS 의 경로
        // http://  domain  /  context-path  /  나머지 경로  /

        model.addAttribute("uri", uri);
        model.addAttribute("conPath", conPath);

        //TODO

        return "comn";      // templates/views/comn.html
    }

    @RequestMapping("/member/search")
    public String searchMember(HttpServletRequest request, Model model){
        System.out.println("searchMember() 호출");
        String uri = request.getRequestURI();
        String conPath = request.getContextPath();
        model.addAttribute("uri", uri);
        model.addAttribute("conPath", conPath);

        return "member/search";
        // http://localhost:8081/ReqMapping/member/search >> 여기에 간다.
    }

    // model.addAttribute() 방법 말고 다른 방법
    // 뷰에 데이터 전달 ModelAndView 객체 사용
    // '뷰' 와 '데이터(Model)' 을 둘다 -> ModelAndView 에 세팅
    @RequestMapping(value="/member/find")
    public ModelAndView findMember(){
        ModelAndView mv = new ModelAndView();

        mv.addObject("mbName", "원피스");  // model 객체에 넣어주었던 것들을 세팅했다.
        mv.addObject("mbDate", LocalDateTime.now());
        mv.setViewName("member/find");      // view도 세팅했다.

        return mv;
    }

    // 확장자 패턴
    @RequestMapping("/member/*.do") // /member/aaa.do , /member/bbb.do ...
                            // 이것도 container에서 자동주입되는구나. 위치를 바꿔도 되는걸 보니~
    // @RequestMapping("/member/**/*.do")   // 2.6.0 부터 ** 동작 안함.     /member/aaa/bbb/cccc/ddd.do
    public String doMember(HttpServletRequest request, Model model) {
        System.out.println("doMember() 호출");
        String uri = request.getRequestURI();
        model.addAttribute("uri", uri);

        return "member/doMember";
    }

}
