package com.lec.spring.service;

import com.lec.spring.domain.Write;
import com.lec.spring.repository.WriteRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


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


    // 특정 id의 글 조회
    // 트랜잭션 처리
    // 1. 조회수 증가 (update)
    // 2. 글 읽어오기 (select)
    @Transactional      // >> commit rollback을 간단하게 처리...와우
    public List<Write> detail(long id) {
        writeRepository.incViewCnt(id);
        Write write = writeRepository.findById(id);

        List<Write> list = new ArrayList<>();

        if (write != null) {
            list.add(write);
        }

        return list;
    }
}
