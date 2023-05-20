package com.lec.spring.di03;

import com.lec.spring.beans.Score;
import com.lec.spring.beans.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DIMain03 implements CommandLineRunner {

    @Autowired // 자동주입, 필드타입과 같은 bean이 컨테이너 안에 있으면 자동주입 받는다.
    Score scoreA;

    @Autowired
    Student stuA;

    @Autowired
    ApplicationContext ctx;  // 생성된 스프링 컨테이너

    public DIMain03() {
        System.out.println("DIMain03() 생성");
    }

    public static void main(String[] args) {
        System.out.println("Main 시작");
        SpringApplication.run(DIMain03.class, args);

        System.out.println("Main 종료");
    } // end main

    @Override
    public void run(String... args) throws Exception {
        System.out.println(scoreA);
        System.out.println(stuA);

        // 아니, Student 객체 만들면서 score1을 호출하면서 new로 새로운 객체를 생성했는데,
        // 왜 두개가 같지?
        // @Bean으로 만들어진 Bean객체는 알아서 자동주입된다.
        System.out.println("scoreA == stuA ?? >> " + (scoreA == stuA.getScore()));


        // 컨테이너에 생성된 bean 의 '이름' 으로
        // bean 객체를 받아올수 있다.
        System.out.println(ctx.getBean("score1"));
        System.out.println(ctx.getBean("stu1"));

    }
}
