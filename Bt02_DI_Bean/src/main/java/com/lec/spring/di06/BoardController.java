package com.lec.spring.di06;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class BoardController {
    DoService service;

    // DoService 주입받아 생성
    // 조건1: 생성자 1개만 있는 것 만족, / 조건2: bean 객체의 네임과 비슷하거나 동일한 것으로 ~
    public BoardController(DoService registerService) {
        System.out.println("BoardController(" + registerService + ") 생성");
        this.service = registerService;
    }
}
