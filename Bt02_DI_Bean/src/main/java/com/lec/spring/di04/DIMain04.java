package com.lec.spring.di04;

import com.lec.spring.beans.MessageBean;
import com.lec.spring.beans.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

// 동일 타입 빈이 컨테이너 안에 여러개 생성된 경우

/*
 * Bean  에 이름을 지정하는 방법
 * 1) 이름을 명시하지 않는 경우
 * @Component: 소문자로 시작하는 클래스이름이 자동으로 사용됨.
 * @Bean: 소문자로 시작하는 메서드이름이 자동으로 사용됨.
 *
 * 2) 이름을 명시하는 경우
 * @Component: @Component("이름") 으로 이름 명시
 * @Bean: @Bean(name="이름") 으로 이름 명시
 */


@SpringBootApplication
public class DIMain04 implements CommandLineRunner {

    @Autowired
    @Qualifier("score1")  // default 이름과 객체이름이 동일 하므로 생략 가능
    Score score1; // 컨테이너 안에서 "score1" 이라는 name 의 Score 타입 bean 을 자동 주입

    @Autowired
    @Qualifier("Kim") // 컨테이너 안에서 "Kim" 이라는 name 의 Score 타입 bean 을 자동 주입
    Score scoreB;

    MessageBean msg1;
    MessageBean msg2;

    @Autowired
    @Qualifier("messageKor")   // @Component로 bean객체를 만들면 ~ 앞글자 소문자인 클래스 이름으로 bean객체의 name이 만들어진다.
    public void setMsg1(MessageBean msg1) {
        System.out.println("messageKor setter 실행");
        this.msg1 = msg1;
    }

    @Autowired
    @Qualifier("ENG")
    public void setMsg2(MessageBean msg2) {
        this.msg2 = msg2;
    }

    @Autowired
    ApplicationContext ctx;  // 생성된 스프링 컨테이너

    public DIMain04() {
        System.out.println("DIMain04() 생성");
    }

    public static void main(String[] args) {
        System.out.println("Main 시작");
        SpringApplication.run(DIMain04.class, args);

        System.out.println("Main 종료");
    } // end main


    @Override
    public void run(String... args) throws Exception {
        System.out.println(score1);
        System.out.println(scoreB);
        System.out.println();
        System.out.println(ctx.getBean("score1"));
        System.out.println(ctx.getBean("Kim"));
//        System.out.println(ctx.getBean("score2"));

        msg1.sayHello();
        msg2.sayHello();
    }
}
