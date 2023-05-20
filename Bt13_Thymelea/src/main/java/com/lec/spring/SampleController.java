package com.lec.spring;

import com.lec.spring.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.*;

@Controller
public class SampleController {

    // 데이터 준비
    String [] arr1 = new String[]{"AAA", "BBB", "CCC", "DDD", "EEE"};
    Member [] arr2;
    List<Integer> list1 = new ArrayList<>();
    List<Member> list2 = new ArrayList<>();

    Map<Integer, String> map1 = new HashMap<>();
    Map<String, Member> map2 = new HashMap<>();


    public SampleController(){
        System.out.println("SampleController() 생성");

        Member member;
        // 데이터 초기화
        for(int i = 0; i < 5; i++) {
            list1.add(i * 1000);
            member = new Member(100 + i, "u0" + i, "p0" + i, arr1[i],
                    LocalDateTime.now());
            list2.add(member);
            map1.put(i, arr1[i]);
            map2.put(arr1[i], member);
        }
        arr2 = list2.toArray(new Member[5]);

        // ghkrdls
        System.out.println("arr1: " + Arrays.toString(arr1));
        System.out.println("arr2: " + Arrays.toString(arr2));

        System.out.println("list1: " + list1);
        System.out.println("list2: " + list2);

        System.out.println("map1: " + map1);
        System.out.println("map2: " + map2);

    } // end 생성자


    @RequestMapping("/sample1")
    public void sameple1(Model model) {
        System.out.println("sample1() 호출");
        model.addAttribute("greeting", "Hello Thymeleaf");
    }


    @RequestMapping("/sample2")
    public void sameple2(Model model) {
        System.out.println("sample2() 호출");
        // 우리는 아직까지 MVC 모델에서 V (View) C(Controller) 만 확인 했다.
        // Model은?
        // 대학학사관리
        // entity : 학생 교수 과목 학과 ... <<<-- 이런것들이 Model

        // baseRoot > domain.Member 생성 후~

        Member member = new Member(123, "u00", "p00", "홍길동", LocalDateTime.now());

        model.addAttribute("m", member);

        System.out.println(model.getAttribute("m"));
        System.out.println(model.getAttribute("aaa")); // 없는 attribute는 꺼내려하면 null 리턴

        System.out.println(model.addAttribute("arr1", arr1));
        System.out.println(model.addAttribute("arr2", arr2));
        System.out.println(model.addAttribute("list1", list1));
        System.out.println(model.addAttribute("list2", list2));
        System.out.println(model.addAttribute("map1", map1));
        System.out.println(model.addAttribute("map2", map2));

    }






}