package com.lec.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller     // Spring MVC 에선 Controller 역할을 하는 빈객체로 생성
public class HomeController {

    public HomeController() {
        System.out.println("잘 바밥요");
        System.out.println(getClass().getName()+ "() 생성");
    }

    // Controller 안에는 request를 처리(handle) 하는 메소드들을 정의한다
    // 이러한 메소드들을 'handler' 라 한다.
    // handler 는
    //  - '어떠한 request' 를 받아서,
    //  - '어떠한 처리 (business logic)' 를 하고
    //  - '어떠한 response' 를 할지를 정의

    @RequestMapping("/")     // "/" 라는 url 로 request 가 들어오면 아래 메소드가 처리(handle) 한다
    public String now(){
        return "home";
    }
}
