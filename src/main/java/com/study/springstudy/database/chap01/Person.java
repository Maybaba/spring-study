package com.study.springstudy.database.chap01;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
@NoArgsConstructor
//@RArgsConstructor
@AllArgsConstructor
public class Person {

    private int id;

    private String personName;

    private int personAge;

    public Person(ResultSet rs) throws SQLException  {
        this.id = rs.getInt("id");
        this.id = rs.getInt("name");
        this.id = rs.getInt("age");
    }

}
