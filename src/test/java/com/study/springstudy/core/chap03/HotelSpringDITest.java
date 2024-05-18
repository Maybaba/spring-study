package com.study.springstudy.core.chap03;

import com.study.springstudy.core.chap04.HotelConfig;
import com.study.springstudy.core.chap04.Hotel;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class HotelSpringDITest {
    @Test
    void diTest() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HotelConfig.class);
        Hotel hotel = context.getBean(Hotel.class);
//        hotel.inform();

    }

}