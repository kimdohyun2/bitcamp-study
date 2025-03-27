package bitcamp.myapp.member;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {

  private MemberService memberService;

  public AuthController(MemberService memberService) {
    this.memberService = memberService;
  }

  @GetMapping("login-form")
  public void form(
          @CookieValue(value="email", required=false) String email,
          Model model) {
    model.addAttribute("email", email);
  }
}
