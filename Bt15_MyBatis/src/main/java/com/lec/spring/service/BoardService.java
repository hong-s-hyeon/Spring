package com.lec.spring.service;

import com.lec.spring.controller.BoardController;
import com.lec.spring.domain.Write;
import com.lec.spring.repository.WriteRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


// Service layer
//  - Tranaction 담당
@Service
public class BoardService {


    private WriteRepository writeRepository;
    @Autowired
    public BoardService(SqlSession sqlSession){ // MyBatis 가 생성한 SqlSession 빈(bean) 객체 주입
        writeRepository = sqlSession.getMapper(WriteRepository.class);
        System.out.println("BoardService() 생성");
    }

    public int write(Write write) {
        return writeRepository.save(write);
    }
}
