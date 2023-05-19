package com.lec.spring.di04;

import com.lec.spring.beans.MessageBean;
import org.springframework.stereotype.Component;

@Component("ENG") // // 이름이 "ENG" 인 MessageBean 타입 bean 생성 >> 기존의 messageEng 라는 이름의 Bean객체 이름은 사라짐
public class MessageEng implements MessageBean {

    String msgEng = "Good Morning";

    public MessageEng() {
        System.out.println("MessageEng() 생성");
    }

    @Override
    public void sayHello() {
        System.out.println(msgEng);
    }
}
