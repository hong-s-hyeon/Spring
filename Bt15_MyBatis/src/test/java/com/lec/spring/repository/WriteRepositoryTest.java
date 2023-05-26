package com.lec.spring.repository;

import com.lec.spring.domain.Write;
import com.lec.spring.service.BoardService;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WriteRepositoryTest {

    @Autowired
    private SqlSession sqlSession;

    @Test
    void test1() {
        WriteRepository writeRepository = sqlSession.getMapper(WriteRepository.class);
        writeRepository.incViewCnt(1L);
        System.out.println(writeRepository.findById(1L));
        System.out.println("테스트");
    }

    @Test
    void test2() {
        WriteRepository writeRepository = sqlSession.getMapper(WriteRepository.class);
        System.out.println(writeRepository.findAll());
        System.out.println("테스트");
    }

    @Test
    void test3() {
        WriteRepository writeRepository = sqlSession.getMapper(WriteRepository.class);
        System.out.println(writeRepository.findById(2L));
        Write write = Write.builder()
                .id(2L)
                .subject("qwe")
                .content("qwe")
                .build();

        System.out.println(writeRepository.update(write));
        System.out.println(writeRepository.findById(write.getId()));
        System.out.println("테스트3");
    }

}