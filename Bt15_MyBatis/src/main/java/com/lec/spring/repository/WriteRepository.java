package com.lec.spring.repository;

import com.lec.spring.domain.Write;

import java.util.List;

// Repository layer
// DataSource (DB) 등에 대한 직접적인 접근


public interface WriteRepository {

    // 새글 작성 <- Write
    // 앞에  public abstract 항상 붙어있음
    int save(Write write);


    // 특정 id 글 내용 읽기
    Write findById(long id);


    // 특정 id 글 조회수 +1 증가
    int incViewCnt(long id);

    //전체글 목록 : 최신순
    List<Write> findAll();

    // 특정 id 글 수정(제목, 내용)
    int update(Write write);

    // 특정 id 글 삭제
    int delete(Write write);
}
