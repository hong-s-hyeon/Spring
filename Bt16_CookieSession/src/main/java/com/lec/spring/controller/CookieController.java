package com.lec.spring.controller;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


// 주의!
// 만약 핸들러에서 HttpServletResponse 객체를 건드렸으면
// 해당 핸들러는 반드시 String 을 리턴하여 (뷰 나 redirect 가 될수 있도록 하자)

@Controller
@RequestMapping("/cookie")
public class CookieController {


    // 전통적인 방법. HttpServletRequest
    @RequestMapping("/list")
    public void list(HttpServletRequest request, Model model) {

        // 클라이언트 안의 쿠키 정보는 request 시에 서버로 전달된다.
        // request.getCookies() 로 쿠키 받아올수 있다.
        Cookie[] cookies = request.getCookies(); // public abstract jakarta.servlet.http.Cookie[] getCookies()  >> Cookie[]

        StringBuilder sb = new StringBuilder();

        if (cookies != null) {  // 쿠기가 하나도 없다면 null 리턴
            for (int i = 0; i < cookies.length; i++) {
                String name = cookies[i].getName(); // 쿠키의 이름
                String value = cookies[i].getValue();   // 쿠키의 값
                sb.append((i + 1) + "] " + name + " : " + value + "<br>");
            }
        } else {
            sb.append("쿠키가없습니다.<br>");
        }

        model.addAttribute("result", sb.toString());

    }


    // 쿠키 생성 절차
    //1. 쿠키(Cookie) 클래스로 생성
    //2. 쿠키속성 설정(setter)
    //3. 쿠키의 전송 (response 객체에 탑재:addCookie())
    @RequestMapping("/create")
    public String create(HttpServletResponse response) {
        String cookieName1 = "num1";
        String cookieValue = "" + (int) (Math.random() * 10);
        Cookie cookie1 = new Cookie(cookieName1, cookieValue); // name-value 쌍으로 Cookie 생성
        cookie1.setMaxAge(60); // 쿠키 파기(expiry) 시간 설정 (생성 시점으로부터 30 초 후)
        response.addCookie(cookie1); // reponse 에 Cookie 추가


        // 쿠키는 얼마든지 생성 가능.
        String cookieName2 = "datetime";
        String cookieValue2 = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss"));   // '-', ':' 등의 문자는 쿠키에 사용 못함
        Cookie cookie2 = new Cookie(cookieName2, cookieValue2);
        cookie2.setMaxAge(30);
        response.addCookie(cookie2);

        // 왜 redirect?
        return "redirect:/cookie/list";
    }


    @RequestMapping("/delete")
    public String delete(HttpServletResponse response) {
        String cookieName = "num1";
        Cookie cookie = new Cookie(cookieName, "xxx");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/cookie/list";
    }

    //------------------------------------------------------------------------
    public static final String ADMIN_ID = "admin";
    public static final String ADMIN_PW = "1234";



    // Spring에서 쿠키를 다룰 때 많이쓰는 방법

    @GetMapping("/login")
                        //Response객체와 비교
                        // 이말은 즉, 이미 한번 로그인 했었다는 것
                        // userid 키값을 받아와서 userid에 담겟다
    public void login(@CookieValue(name="userid", required = false) String userid, Model model) {
        model.addAttribute("userid", userid);
    }

    @PostMapping("/login")
    public String loginOk(String userid, String pw, HttpServletResponse response, Model model) {
        // userid / pw 일치하면 로그인 성공 + 쿠키 생성
        if(ADMIN_ID.equalsIgnoreCase(userid) && ADMIN_PW.equals(pw)){
            Cookie cookie = new Cookie("userid", userid);
            cookie.setMaxAge(30);
            response.addCookie(cookie);

            model.addAttribute("result", true);

        }else{
            Cookie cookie = new Cookie("userid", "안들어왔습니다~");
            cookie.setMaxAge(0);        // 기존에 있었던 쿠키도 죽인다.
            response.addCookie(cookie); // response 객체를 건드리면, 무조건 리턴타입은 String (void 안됨)

        }

        return "cookie/loginOk";
    }

    @PostMapping("logout")
    public String logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("userid", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return "cookie/logout";
    }
}
