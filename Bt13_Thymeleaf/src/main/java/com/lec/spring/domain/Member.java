package com.lec.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor      // 기본생성자
@AllArgsConstructor     // 모든 field를 받는 생성자
public class Member {
    private int no;
    private String id;
    private String pw;
    private String name;
    private LocalDateTime regdate;
    
    public String getNick() {
        return "뜨거운 아아";
    }

    public String getRegDateTime(){
        return this.regdate.format(
                DateTimeFormatter.ofPattern("yyyy년MM월dd일 hh시mm분ss초"));
    }
}
