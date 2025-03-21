package bitcamp.myapp.controller;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {
  public String home(HttpServletRequest req, HttpServletResponse resp) {
    return "/home.jsp";
  }
}
