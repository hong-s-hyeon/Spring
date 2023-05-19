package com.lec.spring.di06;

public interface DAO {
}

// DAO 구현 클래스 >> Data Access Object
class DAOImpl implements DAO{
    String uid;

    public DAOImpl() {
        System.out.println("DAOImpl() 생성");
    }

    public DAOImpl(String uid) {
        System.out.printf("DAOImpl(%s) 생성\n", uid);
        this.uid = uid;
    }

    @Override
    public String toString() {
        return String.format("[DAOImpl: %s]", this.uid);
    }
}
