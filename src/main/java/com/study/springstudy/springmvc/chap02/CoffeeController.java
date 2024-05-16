package com.study.springstudy.springmvc.chap02;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;

@Controller
@RequestMapping("/coffee/*") //공통 url, coffee로 시작하는 모든 디렉
public class CoffeeController{

    /**
     * @request-uri /coffee/order
     * @return  /coffee-form.jsp
     */
    //GET 요청만 받을거야.
//    @RequestMapping(value = "/order", method = RequestMethod.GET)
    @GetMapping("/order")
    public String order() {
        return "mvc/coffee-form";
    }

    //POST 요청만 받을거야.
//    @RequestMapping(value = "/result", method = RequestMethod.POST) //post 이외의 메서드는 에러창을 띄운다.
    @PostMapping("/result")
    public String result(
            @RequestParam String menu,
            @RequestParam int price,
            Model model) {

        //1. 주문 데이터 읽어오기 (menu, price)
        model.addAttribute("m", menu);
        model.addAttribute("p", price);

        //2. jsp 에 보내서 렌더링
        return "mvc/coffee-result";
    }
}
