package com.lec.spring.repository;

import com.lec.spring.domain.Write;
import org.springframework.stereotype.Repository;

// Repository layer
// DataSource (DB) 등에 대한 직접적인 접근

//@Repository
public interface WriteRepository {

    // 새글 작성 <- Write
    // 앞에  public abstract 항상 붙어있음
    int save(Write write);
}
