package com.lec.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// Model 객체(domain, DTO: Data Transfer Object)

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Write {
    private long id;
    private String user;
    private String subject;
    private String content;
    private LocalDateTime regDate;
    private long viewCnt;

    // 웹개발시...
    // 가능한, 다음 3가지는 이름을 일치시켜주는게 좋습니다.
    // 클래스 필드명 = DB 필드명 = form의 name명

}
