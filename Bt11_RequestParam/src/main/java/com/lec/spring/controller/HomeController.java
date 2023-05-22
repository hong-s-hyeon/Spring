package com.lec.spring.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {

    @RequestMapping("/member/delete")   // 어떤 방식(method)의 request 도 매핑함 (GET, POST, ...)
    public void delMember(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");   // "id"란 name 의 parameter 값 리턴(String)
        System.out.println("id : " + id);

        String age = request.getParameter("age");
        System.out.println("age : " + age);

        // ?id=hello&age=33&num=10&num=20&num=30 의 url이 넘어온다면?

        String num = request.getParameter("num"); // 10만 넘어왔다.. 난 10 20 30 을 넘겨줫는데,
        System.out.println("num : " + num);

        String[] arrNum = request.getParameterValues("num");
        System.out.println("arrNum : " + Arrays.toString(arrNum));
        // url :  http://localhost:8081/member/delete?id=hello
        // getParameter(param age) <-- 없는 parameter 의 경우, null을 리턴한다!

//        model.addAttribute("id", id);
//        model.addAttribute("age", age);

        Map<String, String> map = new HashMap<>();
        map.put("mdId", id);
        map.put("mdAge", age);
        map.put("num", num);
        model.addAllAttributes(map);
    }

    @RequestMapping("/member/regist")
    public void registMember() {
    }

    @RequestMapping("/member/regOk")
    public void regOk(HttpServletRequest request, Model model) {

        String methodName = request.getMethod();

        System.out.println("/member/regOk requested " + methodName);

        String name = request.getParameter("name");
        model.addAttribute("name", name);
        System.out.println("name : " + name);
    }
}
