package Plant.IamGroot.controller;

import Plant.IamGroot.dto.MemberFormDto;
import Plant.IamGroot.entity.Member;
import Plant.IamGroot.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/new")
    public String memberForm(Model model){
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "members/memberForm";
    }

//    @PostMapping("/new")
//    public String MemberForm(MemberDTO memberDTO){
//        Member member = Member.toSaveEntity(memberDTO);
//        memberService.saveMember(member);
//
//        return "redirect:/";
//    }  밑에 회원가입 로직으로 대체

    @PostMapping("/new")
    public String newMember(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "members/memberForm";
        }
        try {
            Member member = Member.toSaveEntity(memberFormDto, passwordEncoder);
            memberService.saveMember(member);
        }catch(IllegalStateException e) {
           model.addAttribute("errorMessage", e.getMessage());
           return "members/memberForm";
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginMember(){
        return "members/memberLoginForm";
    }

    @GetMapping("/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
        return "members/memberLoginForm";
    }
}
