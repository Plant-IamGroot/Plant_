package Plant.IamGroot.entity;

import Plant.IamGroot.constant.Role;
import Plant.IamGroot.dto.MemberFormDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @Enumerated
    private Role role;

    public static Member toSaveEntity(MemberFormDto memberFormDto) {
        Member member = new Member();
        member.setMemberName(memberFormDto.getName());
        member.setMemberEmail(memberFormDto.getEmail());
//        String password = passwordEncoder.encode(memberDTO.getMember_Pw());
        member.setMemberPw(memberFormDto.getPassword());
        member.setMemberAddr(memberFormDto.getAddress());
        member.setRole(Role.USER);
        return member;
    }
}
