package com.study.springstudy.springmvc.chap01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;

@Controller
@RequestMapping("/spring/chap01/*") //controllerMap.put (~~~) 경로를 설정한 객체를 생성하는 역할을 한다.
public class BasicController {
    //디테일 요청
    //URL : /spring/chap01/hello 가 포함되면 아래를 실행시키기로 함
    @RequestMapping("/hello") // 이 요청을 보내면, 아래와 같은 응답이 나온다 ! 왜이렇게 되는지는 v1 ~ v5 참고하시구리.
    public String hello() {
        System.out.println("hello 요청이 들어옴!");
        //포워딩 할 경로
        return "hello"; // 이렇게 열리는 이유는 application.properties에 경로를 지정해놓았기 때문임
        //WEB-INF/views/hello.jsp -> View 클래스에서 suffix 설정함. Spring frameework에 같은 이름의 파일이 있으니 확인 가능
    }

    //============================ 요청 파라미터 읽기 Query String ==========================//
    //URL에 붙어있거나 form 태그에서 전송된 데이터
    @RequestMapping("/person")
    public String person(HttpServlet request) {
        System.out.println("/person 요청됨!!!!!!!!!!!1");

        String name = request.getInitParameter("name");
        String age = request.getInitParameter("age");

        System.out.println("name = " + name);
        System.out.println("age = " + age);
        return "";
    }

    //2. @RequestParam 사용하기
    // /spring/chap01/major?stu=shin&major=physics&grade=4
    @RequestMapping("/major")
    public String major(
//            @RequestParam String stu String st,
            @RequestParam ("stu") String st,
            String major, //왜 생략 가능?
            @RequestParam int grade) {

        System.out.println("st = " + st);
        System.out.println("major = " + major);
        System.out.println("grade = " + grade);
        return "";
    }

    //3. 커매늗 객체 (RequestDTO) 사용하기
    //ex)/spring/chap01/order?orderNum=22&goods=구두&........ param이 너무 많을 때 동일 디렉에 클래스를 하나 만든다
    @RequestMapping("/order")
    public String order(OrderDto order) {
        System.out.println("* orderNum = " + order.getOrderNum());
        System.out.println("* goods name = " + order.getGoods());
        System.out.println(" * amount= " + order.getAmount());
        System.out.println("* price = " + order.getPrice());

        return "";
    }
}
