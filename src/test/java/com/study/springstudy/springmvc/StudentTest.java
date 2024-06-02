package com.study.springstudy.springmvc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    @Test
    void sTest() {
        Student s = new Student();
        s.setName("냥");
        s.setAge(20);
        s.setGrade(1);

        System.out.println("\n s = " + s);
    }

}