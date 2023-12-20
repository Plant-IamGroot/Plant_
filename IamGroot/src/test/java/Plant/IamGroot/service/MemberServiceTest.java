package Plant.IamGroot.service;

import Plant.IamGroot.dto.MemberFormDto;
import Plant.IamGroot.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;


@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.yml")
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Member createMember(){
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setEmail("최병국@gmail.com");
        memberFormDto.setName("최병국");
        memberFormDto.setAddress("수원시 권선구 호매실동");
        memberFormDto.setPassword("1234");
        return Member.toSaveEntity(memberFormDto, passwordEncoder);
    }

    @Test
    @DisplayName("회원가입 테스트")
    public void saveMemberTest(){
        Member member = createMember();
        Member saveMember = memberService.saveMember(member);
        Assertions.assertEquals(member.getMemberEmail(), saveMember.getMemberEmail());
        Assertions.assertEquals(member.getMemberName(), saveMember.getMemberName());
        Assertions.assertEquals(member.getMemberAddr(), saveMember.getMemberAddr());
        Assertions.assertEquals(member.getMemberPw(), saveMember.getMemberPw());
        Assertions.assertEquals(member.getRole(), saveMember.getRole());
    }

    @Test
    @DisplayName("중복 회원가입 테스트")
    public void saveDuplicateMemberTest(){
        Member member1 = createMember();
        Member member2 = createMember();
        memberService.saveMember(member1);

        Throwable e = Assertions.assertThrows(IllegalStateException.class, () -> {memberService.saveMember(member2);});

        Assertions.assertEquals("이미 가입된 회원입니다.", e.getMessage());
    }
}