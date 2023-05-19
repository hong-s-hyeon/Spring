package com.lec.spring.di06;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    public UserRepository() {
        System.out.println("UserRepository() 생성");
    }

}
