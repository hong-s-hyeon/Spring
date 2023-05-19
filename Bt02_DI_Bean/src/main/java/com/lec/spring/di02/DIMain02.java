package com.lec.spring.di02;

import com.lec.spring.beans.MessageBean;
import com.lec.spring.di01.DIMain01;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DIMain02 implements CommandLineRunner {


    public MessageBean msg;


    public DIMain02(){
        System.out.println(getClass().getName() + "() 생성");
    }

    @Autowired      // 자동주입 (setter injection)
    public void setMsg(MessageBean msg) {
        System.out.println("setMsg() 호출");
        this.msg = msg;
    }

    @Autowired      // 자동주입 (field injection)
    ApplicationContext ctx;     // Spring container(스프링 컨테이너), 컨텍스트, IoC 컨테이너, Bean Factory 등 지칭하는 용어 다양


    public static void main(String[] args) {
        System.out.println("main 시작");

        // 컨테이너 생성
        // 컨테이너가 생성될 때, bean이 생성되고
        SpringApplication.run(DIMain02.class, args);



        System.out.println("main 종료");
    }// main

    @Override
    public void run(String... args) throws Exception {
        // System.out.println("언제 실행되나?");

         msg.sayHello();

        // 컨테이너 안의 생성된 bean 정보를 꺼내올 수 있다.
        System.out.println("생성된 빈의 갯수: " + ctx.getBeanDefinitionCount());

//        for(String name : ctx.getBeanDefinitionNames()){
//            System.out.println(name + " : " + ctx.getBean(name));
//        }


    } //method run() end


}// class
