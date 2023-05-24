package com.lec.spring;

import com.lec.spring.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        System.out.println(model.addAttribute("map2.get(6)", map1.get(6)));
        System.out.println("안녕");

    }

    @RequestMapping("/sample3")
    public void sample3(Model model) {
        model.addAttribute("list", list2);
    }

    @RequestMapping(value = "/sample4", method = RequestMethod.GET)   // GET 방식으로 /sample4 에 대해 동작하는 handler
    public void sample4(Model model) {
        list2.get(3).setId(null);
        model.addAttribute("list", list2);

        // th:if 에서 어케 판정될까?
        model.addAttribute("test1", "aaa");
        model.addAttribute("test2", "");
        model.addAttribute("test3", null);
        model.addAttribute("test4", false);
        model.addAttribute("test5", 0);
    }

    @GetMapping("/sample5")  // GET 방식으로 /sample4 에 대해 동작하는 handler
                            //  @RequestMapping(value = "/sample5", method = RequestMethod.GET) 와 동일
    public void sample5(Model model) {
        String result = "SUCCESS";
        model.addAttribute("result", result);

    }

    @GetMapping("/sample6")
    public void sample6(Model model) {
        model.addAttribute("list", list2);
        String result = "SUCCESS";
        model.addAttribute("result", result);
    }

    @GetMapping("/sample7")
    public void sample7(Model model) {
        model.addAttribute("now1", LocalDateTime.now());    //java.time.LocalDateTime
        model.addAttribute("now2", new Date());     // java.util.Date
        model.addAttribute("price", 123456789);
        model.addAttribute("title", "This is sample");
        model.addAttribute("options", Arrays.asList("AAA", "BBB", "CCC", "DDD"));   // List<>
    }

    @GetMapping("/sample8")
    public void sample8(Model model) {
        model.addAttribute("value1", "John");
        model.addAttribute("url1", "sample1");

    }
}
