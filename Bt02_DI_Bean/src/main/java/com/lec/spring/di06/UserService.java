package com.lec.spring.di06;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired    // 클래스의 첫글자 소문자인 bean 객체 이름으로 주입됨
    UserRepository userRepository;

    public UserService() {
        System.out.println(getClass().getName() + "() 생성");
    }
}
