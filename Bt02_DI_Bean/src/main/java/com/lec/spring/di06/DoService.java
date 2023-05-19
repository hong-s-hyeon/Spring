package com.lec.spring.di06;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface DoService {
}

@Service
class RegisterService implements DoService{
    DAO dao;

    // 기본생성자는 없다.


    // 아래 DAO 는 자동주입 받게 된다
    public RegisterService(DAO dao) {
        System.out.printf("RegisterService(%s)생성\n", dao);
        this.dao = dao;
    }

    @Override
    public String toString() {
        return String.format("[RegisterService : %s]", dao);
    }
}

@Service
class ReadService implements DoService {
    DAO dao;

    // 기본생성자가 없다.

    // 생성자에 @Autowired 자동주입
    // 매개변수 '타입' 과 일치하는 bean 객체가 주입된다.
    @Autowired
    public ReadService(DAO dao) {
        System.out.printf("ReadService(%s)생성\n", dao);
        this.dao = dao;
    }

    @Override
    public String toString() {
        return String.format("[ReadService : %s]", dao);
    }
}
