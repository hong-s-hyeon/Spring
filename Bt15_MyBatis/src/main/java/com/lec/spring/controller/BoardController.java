package com.lec.spring.controller;

import com.lec.spring.domain.Write;
import com.lec.spring.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


// Controller layer
//      request 처리 ~ response
@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    public BoardController(){
        System.out.println("BoardController() 생성");
    }

    @GetMapping("/write")
    public void write() {

    }

    @PostMapping("/write")
    public void writeOk(Write write) {
        boardService.write(write);
    }

    @GetMapping("/detail")
    public void detail() {

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
