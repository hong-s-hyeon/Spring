package com.lec.spring.controller;

import com.lec.spring.domain.Write;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
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

        String[] arrNum = request.getParameterValues("num");        // >> Servlet 방식
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

    @RequestMapping("/member/regOk")  // RequestMapping 은 어떤 request Method도  받을 수 있다.
    public void regOk(HttpServletRequest request, Model model) {

        String methodName = request.getMethod();

        System.out.println("/member/regOk requested " + methodName);

        String name = request.getParameter("name");
        model.addAttribute("name", name);
        System.out.println("name : " + name);
    }

    // 특정 request method(들) 에서만 동작하는 url 매핑
    @RequestMapping(value = "/member/regOk2", method = {RequestMethod.GET, RequestMethod.PUT})
    public String regOk2(HttpServletRequest request, Model model) {

        String methodName = request.getMethod();

        System.out.println("/member/regOk2 requested " + methodName);

        String name = request.getParameter("name");
        model.addAttribute("name", name);
        System.out.println("name : " + name);

        return "member/regOk";
    }

    // 단일 request method 에서만 동작하는 핸들러는
    // @GetMapping(..), @PostMapping(..), @PutMapping(...)  등을 사용하는게 간편하다

    // parameter name 값과 동일한 이름의 변수를 핸들러에 지정해서 받아오기
    @RequestMapping("/member/find1")
//    public void find1(String id, String name, Model model) {      >> 매개변수 순서 달라도 된다~
    public void find1(String name, Model model, String id) {
        // 원래는 이렇게 했엇는데~~~~
//        String name = request.getParameter("id");
//        String name = request.getParameter("name");

        System.out.println("member/find1 : id=" + id + ", name=" + name);
        // model 객체에 담아서 주는 것 말고도 thymeleaf 자체에서도 알아서 해줄수 있다. >> ${param.~~}
//        model.addAttribute("id", id);
//        model.addAttribute("name", name);
    }


    // 숫자 타입은 바로 parsing 하여 받을 수 있다.
    @RequestMapping("/member/find2")
    //                   여기 바뀜
//    public void find2(double id, String name, Model model) { // primitive 타입인데, parameter 에 없는 경우, 혹은 parsing 불가능하면 에러 발생
    public void find2(Double id, String name, Model model) { // wrapper 타입이면, parameter 없으면 null 값으로 받아옴. parsing 불가하면 에러
        //                  ↑          ↑        >> request.get~ 이 아니라 위의 방법대로하는 것을 binding이라고 한다.
        // 위 과정을 binding 한다 라고 한다.
        // parameter "name"-"value"  -->  Java 변수, 객체


        System.out.println("member/find2 : id=" + id + ", name=" + name);
        // 만약에 id 값이 double 형태로 변환이 안된다면? >> 에러를 띄운다
        // id 값이 double로 형변환 가능한 것들로만 해야 한다~
    }


    // 동일한 name 의 parameter(들) --> '배열' 매개변수로 받을수 있다.
    // String[] arrNum = request.getParameterValues("num");        // >> Servlet 방식
    @RequestMapping("/member/find3")
    public void find3(int[] id, String[] name, Model model) {
        System.out.println("member/find3 : id=" + Arrays.toString(id) + ", name=" + Arrays.toString(name));


    }

    // 만약 request parameter 의 name 과 매개변수가 같을수 없는 상황이면
    // @RequestParam 애노테이션 사용
    @RequestMapping("/member/find4")
    public void find4(
            @RequestParam("id") String userid,  // "id" 란 name 의 parameter 값을 userid 매개변수에 binding 해준다
            // @RequestParam 을 쓰면, parameter를 안넘겨주면, 이전처럼 null이 아니라, 에러발생
            @RequestParam("name") String username
    ) {
        System.out.println("member/find4 : id=" + userid + ", name=" + username);
    }
    // 위의 경우 id 값이 없거나 변환 불가능하면 에러 발생한다. (왜냐하면 required=true 이기 때문이다)
    // @RequestParam(value="test", required=false, defaultValue="0") 을 이용하면 가능하긴 하다.✨✨✨✨


    // 또한, @RequestParam 과 Map<name, value> 을 사용하면 된다.
    @RequestMapping("/member/find5")
    public void find5(@RequestParam Map<String, String> map) {
        System.out.println("member/find5 : " + map);
    }

    //--------------------------------------------------------------
    // 커맨드 객체 (Command Object) 사용

    // 게시글 등록 form
    @GetMapping("/board/write")
    public void writeBoard() {
    }

    // 기존의 방식대로라면,
    // 매 parameter 들을 매개변수화 하는 것은 힘들다.
//    @PostMapping("/board/writeOk")
//    public void writeOkBoard(
//            String name,
//            String subject,
//            String content
//    ) {}

    // 커맨드 객체 사용(request에서 넘어온 값들을 한번에 받을 수 있게 된다.)
    // 이 값들을 받아오기 위해선, 커맨드 class에서 프로퍼티와 setXxx()메소드가 존재하여야 한다!
    // 코드 작업량이 매우 줄어든다.

    // 커맨드 객체는 객체타입명으로 Model attribute 추가 된다. (소문자로)
    // Model attribute name 을 바꿀경우 @ModelAttribute 로 지정
    @PostMapping("/board/writeOk")
    public void writeOkBoard(
            @ModelAttribute("DTO") Write w
//            Write k
    ) {
        // write 객체가 넘어 온 parameter 를 binding 하고 있다~
        System.out.println("/board/writeOk(w) : " + w);
//        System.out.println("/board/writeOk(k) : " + k);
    }

    //--------------------------------------
    // @PathVariable 사용
    @RequestMapping("/board/writePath/{name}/{subject}/{k3}")
    public String writePathBoard(
            @PathVariable String name,
            @PathVariable String subject,
            @PathVariable(name = "k3") String content,
            Model model
    ) {
        System.out.println("/board/writePath/ : name = " + name + ", subject = " + subject + ", content = " + content);
        model.addAttribute("name", name);
        model.addAttribute("subject", subject);
        model.addAttribute("content", content);
        return "/board/writepath";
    }

    // ----------------------------------------------------
    // @ResponseBody : View 를 사용하지 않고 직접 response
    @RequestMapping("/board/update")
    @ResponseBody
    public String boardUpdate() {
        StringBuffer buff = new StringBuffer();
        buff.append("<h1>Hello Srping</h1>");
        buff.append("<h3>1.  Request Parameter</h3>");

        return buff.toString();
    }


    @RequestMapping("/board/detail")
    @ResponseBody
    public Write boardDetail() {
        // Object 를 response 하면 JSON 으로 변환되어 response 된다!
        Write write = Write.builder()
                .id(100)
                .name("홍길동")
                .content("안녕하세요")
                .subject("hello")
                .regDate(LocalDateTime.now())
                .build();
        return write;
    }

    //-------------------------
    // redirect
    @RequestMapping("/member/ageInput")
    public void ageInput() {
        System.out.println("ageinput");
    }

    @RequestMapping("/member/ageCheck")
    public String chkAge(
            int age,
            RedirectAttributes redirectAttr     // redirect 되는 request 에 담을 parameter 지정
    ) {
        System.out.println("checkage");

        redirectAttr.addAttribute("age", age);  // "age"라는 parameter 보냄

        if (age < 19) {
            return "redirect:/member/underAge"; // view 가 아니라 redirect 된다.
        } else {
            return "redirect:/member/adult";
        }
    }

    @RequestMapping("/member/underAge")
    public String pageUnderAge(@ModelAttribute("age") int age) {
        System.out.println("underAge");
        return "member/ageUnder";
    }

    @RequestMapping("/member/adult")
    public String pageAdult(int age) {
        System.out.println("ageAdult");
        return "member/ageAdult";
    }



    //----------------------------------------------------
    // forward:
    @RequestMapping("/member/detail")
    public String memberDetail(){
        System.out.println("/member/detail 요청");
        return "forward:/member/notfound";
    }

    @RequestMapping("/member/notfound")
    @ResponseBody       // 데이터(현재는 String) 자체를 리스폰스한다~!
    public String memberNotFound(){
        System.out.println("/member/notfound 요청");
        return "/member/notfound";
    }

} // end controller
