package com.lec.spring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.Random;

@Controller     // Spring MVC 에선 Controller 역할을 하는 빈객체로 생성
public class HomeController {

    public HomeController() {
        System.out.println("잘 봐요~");
        System.out.println(getClass().getName()+ "() 생성");
    }

    // Controller 안에는 request를 처리(handle) 하는 메소드들을 정의한다
    // 이러한 메소드들을 'handler' 라 한다.
    // handler 는
    //  - '어떠한 request' 를 받아서,
    //  - '어떠한 처리 (business logic)' 를 하고
    //  - '어떠한 response' 를 할지를 정의

    @RequestMapping("/")     // "/" 라는 url 로 request 가 들어오면 아래 메소드가 처리(handle) 한다
    public String now(Model model){  // Model : 데이터를 실어나르는 객체, 담긴 값은 View 까지 전달
        LocalDateTime t = LocalDateTime.now();

        // ↓ Model 에 데이터 담기 (name - value)쌍으로~
        // name : String 타입 , value : Object
        model.addAttribute("serverTime", t.toString());
        model.addAttribute("name", "홍길동");  // model 객체에 그냥 값을 담았다.
        model.addAttribute("age", 29);

        return "home";      // template [home] 을 찾는다. 이것이 ViewResolver에 의해서 view를 찾아가는것

        // ViewResolver 에 의해서 ~
        // prefix + "home" + suffix =>
        // "templates/" + "home" + ".html"   >>   이게 default
        // 바꾸고 싶다면?  application.properties 에서  ---  spring.thymeleaf.prefix=classpath:templates/views/ 추가하면 됨

        // 템플릿 엔진(Thymeleaf)에게 위 파일과 Model 데이터를 넘기면
        // 템플릿 엔진이 response 할 데이터를 렌더링(데이터 + 템플릿파일을 버무림) 하여 response 됨
    }

    @RequestMapping("/aaa")
    public String home(Model model) {
        int a = new Random().nextInt(10);
        int b = new Random().nextInt(5);
        model.addAttribute("first", a);
        model.addAttribute("second", b);

        return "aaa/my";
        // prefix + "aaa/my" + postfix
        // ViewResolver에 의해
        // => Templates/views/aaa/my.html 을 찾는다
    }

    @RequestMapping("/aaa/bbb")
    public String aaabbb(Model model){  //핸들러 이름은 그닥 중요하진 않다.
        int a = new Random().nextInt(10);
        int b = new Random().nextInt(5);
        model.addAttribute("first", a);
        model.addAttribute("second", b);

        return "aaa/bbb/title";
    }
}
