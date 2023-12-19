package Plant.IamGroot.controller;

import Plant.IamGroot.dto.MemberFormDto;
import Plant.IamGroot.entity.Member;
import Plant.IamGroot.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
//    private final PasswordEncoder passwordEncoder;

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
//    }

    @PostMapping("/new")
    public String newMember(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "members/memberForm";
        }
        try {
            Member member = Member.toSaveEntity(memberFormDto);
            memberService.saveMember(member);
        }catch(IllegalStateException e) {
           model.addAttribute("errorMessage", e.getMessage());
           return "members/memberForm";
        }
        return "redirect:/";
    }
}
