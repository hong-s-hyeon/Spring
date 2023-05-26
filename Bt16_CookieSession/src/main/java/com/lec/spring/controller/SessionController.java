package com.lec.spring.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;

@Controller
@RequestMapping("/session")
public class SessionController {


    @RequestMapping("/list")
    public void list(HttpSession session, Model model) {

        // 세션에 있는 '모든' attr names 들 뽑아내기
        Enumeration<String> enumeration = session.getAttributeNames();

//        StringBuilder sb = new StringBuilder();
        StringBuffer buff = new StringBuffer();
        int i = 0;
        while (enumeration.hasMoreElements()) {
            String sessionName = enumeration.nextElement();
            // session.getAttribute('name')  <-- 특정 세션 attr value 추출. 리턴타입 Object. 해당 name 이 없으면 null 리턴
            String sessionValue = session.getAttribute(sessionName).toString();
            buff.append((i + 1) + "] " + sessionName + " : " + sessionValue + "<br>");
            i++;
        }

        if (i == 0) {
            buff.append("세션 안에 attribute 가 없습니다<br>");
        }

        model.addAttribute("result", buff.toString());

    }


    @RequestMapping("/create")
    public String create(HttpSession session) {
        String sessionName, sessionValue;

        sessionName = "num1";
        sessionValue = "" + (int) (Math.random() * 1000);

        // 세션 attr : name-value 생성
        // setAttribute(String name, Object value) 두번째 매개변수는 Object 타입이다
        // Tomcat 의 session 기본값이 아마도 30분? 일 것이다.
        session.setAttribute(sessionName, sessionValue);

        sessionName = "datetime";
        sessionValue = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        session.setAttribute(sessionName, sessionValue);

        return "redirect:/session/list";
    }

    @RequestMapping("/delete")
    public String delete(HttpSession session) {
        // attribute 삭제
        session.removeAttribute("num1");

        return "redirect:/session/list";
    }


    // -----------------------------------------------------
    public static final String ADMIN_ID = "admin";
    public static final String ADMIN_PW = "1234";

    @GetMapping("/login")
    public void login(HttpSession session, Model model) {

        if(session.getAttribute("userid") != null) {
            String userid = session.getAttribute("userid").toString();
            System.out.println("userid : "+ userid);
            model.addAttribute("userid", userid);
        }
    }

    @PostMapping("/login")
    public String loginOk(Model model, String userid, String pw, HttpSession session) {
        if(ADMIN_ID.equals(userid) && ADMIN_PW.equals(pw)){
            session.setAttribute("userid", userid);
            model.addAttribute("result", true);
        } else {
            model.addAttribute("result", false);
            session.removeAttribute("userid");
        }


        return "/session/loginOk";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("userid");
        return "/session/logout";
    }


} // end controller
