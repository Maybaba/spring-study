# spring initial setting
1. build gragle code

```
 plugins {
    id 'java'
    id 'org.springframework.boot' version '2.7.10'
    id 'io.spring.dependency-management' version '1.0.15.RELEASE'
}

group = 'com.spring'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '11'

configurations {
    compileOnly {
        extendsFrom annotationProcessor
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    compileOnly 'org.projectlombok:lombok'
    annotationProcessor 'org.projectlombok:lombok'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

tasks.named('test') {
    useJUnitPlatform()
}
```

2. 단위테스트 - 새로만들기 command + shosh + T
```
   package com.study.springstudy.core.chap03;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class HotelSpringDITest {
@Test
void diTest() {
AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HotelConfig.class);
Hotel hotel = context.getBean(Hotel.class);
hotel.inform();

    }

}
```
3. 단위테스트 - HotelConfig 새로 만들기
```angular2html
package com.study.springstudy.core.chap04;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.study.springstudy.core")
public class HotelConfig {
}

```