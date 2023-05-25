package com.lec.spring.controller;

import com.lec.spring.domain.Write;
import com.lec.spring.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


// Controller layer
//      request 처리 ~ response
@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired      // 필드 인잭션
                    // constructor injection을 사용하는게 좋음
    private BoardService boardService;

    public BoardController(){
        System.out.println("BoardController() 생성");
    }

    @GetMapping("/write")
    public void write() {

    }

    @PostMapping("/write")
                        // 여기서는 커맨드 객체로 작용
    public String writeOk(Write write, Model model) {
        model.addAttribute("result", boardService.write(write));
        model.addAttribute("dto", write); // auto-generated key

        return "board/writeOk";
    }

    @GetMapping("/detail")
    public void detail(long id, Model model) {
        model.addAttribute("list", boardService.detail(id));
    }

    @GetMapping("/list")
    public void list() {

    }

    @GetMapping("/update")
    public void update() {

    }

    @PostMapping("/update")
    public void updateOk() {

    }

    @PostMapping("/delete")
    public void deleteOk() {

    }

}
