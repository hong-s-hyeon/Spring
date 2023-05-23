package com.lec.spring.beans;

import lombok.Data;
@Data
public class Score {
    int kor;
    int eng;
    int math;
    String comment;
    public Score(){
        super();
        System.out.println("Score() 생성");
    }
    // ↑ 기본생성자가 없을때 어떤 일들이 발생하는지 보자
    public Score(int kor, int eng, int math, String comment) {
        super();
        System.out.printf("Score(%d, %d, %d, %s) 생성\n", kor, eng, math, comment);
        this.kor = kor;
        this.eng = eng;
        this.math = math;
        this.comment = comment;
    }
}
