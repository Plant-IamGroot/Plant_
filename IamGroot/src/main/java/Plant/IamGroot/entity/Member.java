package Plant.IamGroot.entity;

import Plant.IamGroot.constant.Role;
import Plant.IamGroot.dto.MemberFormDto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "member_table")
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private Long id;

    private String memberName;

    @Column(name = "member_email", unique = true)
    private String memberEmail;

    @Column(name = "member_pw")
    private String memberPw;

    @Column(name = "member_addr")
    private String memberAddr;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member toSaveEntity(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setMemberName(memberFormDto.getName());
        member.setMemberEmail(memberFormDto.getEmail());
        String password = passwordEncoder.encode(memberFormDto.getPassword());
        member.setMemberPw(password);
        member.setMemberAddr(memberFormDto.getAddress());
        member.setRole(Role.ADMIN);
        return member;
    }
}
