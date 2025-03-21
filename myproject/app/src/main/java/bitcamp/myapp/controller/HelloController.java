package bitcamp.myapp.controller;


import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HelloController {
  public String hello(HttpServletRequest req, HttpServletResponse resp) {
    return "/hello.jsp";
  }

  public String hello2(HttpServletRequest req, HttpServletResponse resp) {
    return "/hello2.jsp";
  }
}
