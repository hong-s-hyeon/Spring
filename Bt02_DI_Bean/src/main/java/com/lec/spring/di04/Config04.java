package com.lec.spring.di04;

import com.lec.spring.beans.Score;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config04 {
    @Bean
    public Score score1(){
        return new Score(100, 80, 75, "좋아요");
    }
    @Bean(name="Kim")       // "Kim" 이라는 이름의 Score 타입 빈 생성
                            // 이렇게 하는 순간, 기본적으로 주어지던 해당 bean객체의 이름(score2)은 사라진다
    public Score score2(){
        return new Score(24, 42, 64, "나빠요");
    }

}
