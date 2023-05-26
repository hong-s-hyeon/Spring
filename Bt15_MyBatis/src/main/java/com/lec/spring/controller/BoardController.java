package com.lec.spring.controller;

import com.lec.spring.domain.Write;
import com.lec.spring.domain.WriteValidator;
import com.lec.spring.service.BoardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;


// Controller layer
//      request 처리 ~ response
@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired      // 필드 인잭션
    // constructor injection을 사용하는게 좋음
    private BoardService boardService;

    public BoardController() {
        System.out.println("BoardController() 생성");
    }

    @GetMapping("/write")
    public void write() {

    }

    @PostMapping("/write")
    // 여기서는 커맨드 객체로 작용
    public String writeOk(
            @Valid Write write,
            BindingResult result,  // <- Validator 가 유효성 검사를 한 결과가 담긴 객체. BindingResult : Errors 의 자식() >> validate()에서
                                    //                                                      담아둔, Errors를 가지고옴
            Model model,             // 매개변수 선언시, BindingResult 보다 Model 을 뒤에 두어야 한다
            RedirectAttributes redirectAttr     // redirect: 시 넘겨줄 값들
    ) {

        // validation 에러가 있었다면 redirect 한다.
        if (result.hasErrors()) {

            // redirect 시, 기존에 입력했던 값들은 보이게 하기
            redirectAttr.addFlashAttribute("user", write.getUser());
            redirectAttr.addFlashAttribute("subject", write.getSubject());
            redirectAttr.addFlashAttribute("content", write.getContent());


            List<FieldError> errList = result.getFieldErrors();
            List<String> errCode = new ArrayList<>();
            for (FieldError err : errList) {

                // redirect는 처음 request가 포스트면 그대로 간다.
                // addFlashAttribute()  <- post 방식으로 redirect 발생될때 쓴다~
//                redirectAttr.addFlashAttribute("error", err.getCode());
                errCode.add(err.getCode());
            }
            redirectAttr.addFlashAttribute("errors", errCode);

            return "redirect:/board/write";
        }


        model.addAttribute("result", boardService.write(write));
        model.addAttribute("dto", write); // auto-generated key

        return "board/writeOk";
    }

    @GetMapping("/detail")
    public void detail(long id, Model model) {
        model.addAttribute("list", boardService.detail(id));
    }

    @GetMapping("/list")
    public void list(Model model) {
        model.addAttribute("list", boardService.list());
    }

    @GetMapping("/update")
    public void update(long id, Model model) {
        model.addAttribute("list", boardService.selectById(id));
    }


    // 1. 커멘드 객체 (dto) 를 통해서, request에서의 정보를 BoardController로 받아옴
    // 2. 받아온 정보를 boardService의 메소드를 실행(update)         : BoardController <- 주입 -- BoardService
    // 3. boardService에서는 Transaction을 처리함                  : BoardService <- 주입 by SqlSession -- WriteBoardRepository
    //                                                                                        │      mapping     │
    //                                                                                        └──────────────────┘
    //                                                                                             xml : sql문
    // 4. writeBoardRepository는 Mybatis를 이용하여 mapper/**/*.xml의 mapper 들과 mapping을 함
    //              WriteBoardRepository는 Interface로서, mapping 될 메소드를 만들기만함 (Override는 그럼 내부적으로 mapping 된 sql문들과 이뤄짐?)
    // 5. boardService에서 WriteBoardRepository의 update메소드를 정의함.
    //                      그리고 mapping 된 sql문을 수행(dto객체에서 뽑아씀<getter> & dto객체에 저장<setter>)하고 결과값(dto)을 리턴
    // 6. 리턴된 결과값은 BoardService > BoardController로 전달
    // 7. Model 객체에 담아 ViewResoler를 통해 특정 View로 전달되고, template 엔진에 의해 SSR 되어 response
    @PostMapping("/update")
    public String updateOk(Write write, Model model) {
        model.addAttribute("result", boardService.update(write));
        // boardService.update(write) >> 1이 전달
        model.addAttribute("dto", write);
        // write >> sql문에 의해서 변경된 dto 객체가 전달됨
        return "/board/updateOk";
    }

    @PostMapping("/delete")
    public String deleteOk(long id, Model model) {
        model.addAttribute("result", boardService.deleteById(id));

        return "/board/deleteOk";
    }


    // 이 컨트롤러 클래스의 handler 에서 폼 데이터를 바인딩 할때, 검증하는 Validator 객체 지정
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        System.out.println("initBinder() 호출");
        binder.setValidator(new WriteValidator());
    }
}
