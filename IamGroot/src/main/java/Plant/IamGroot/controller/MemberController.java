package Plant.IamGroot.controller;

import Plant.IamGroot.dto.MemberDTO;
import Plant.IamGroot.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/save")
    public String saveForm(){
        return "members/save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute MemberDTO memberDTO){
        memberService.save(memberDTO);
        return "members/home";
    }

    @GetMapping("/login")
    public String loginForm(){
        return "members/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session){
       MemberDTO loginRersult =  memberService.login(memberDTO);
       if(loginRersult != null){
           session.setAttribute("loginId", loginRersult.getMember_Id());
           return "redirect:/";
       } else {
           return "members/login";
       }
    }
}
