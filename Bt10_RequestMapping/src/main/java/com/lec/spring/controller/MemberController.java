package com.lec.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")      // 지금부터 아래의 모든 handlermapping 은 앞에 /member 가 붙는다
public class MemberController {

    @RequestMapping("/save")
    public String saveMember() {
        return "member/save";
    }

    // handler의 리턴타입이 void
    // mapping 되는 url에 해당하는 뷰 파일이 리턴
    // 일반적으로 많이 사용하는 버전!!!✨✨✨✨
    @RequestMapping("/load")  // /member + /load => /member/load
    public void loadMember() {
        // return "/member/load";     를 입력한 것과 동일
    }


    // url mapping 이 중복되면 에러! <- 서버가 가동할 때 에러
    // 에러 문구
    // >>
            //    Ambiguous mapping. Cannot map 'homeController' method
            //    com.lec.spring.HomeController#searchMember(HttpServletRequest, Model)
            //    to { [/member/search]}: There is already 'memberController' bean method
            //    com.lec.spring.controller.MemberController#searchMember() mapped.
//    @RequestMapping("/search")      // /memeber/search로 맵핑
//    public void searchMember() {
//        //
//    }

}
