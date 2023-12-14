package Plant.IamGroot.entity;

import Plant.IamGroot.dto.MemberDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "member_table")
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEM_NO")
    private Long id;

    @Column(name = "MEM_NAME")
    private String memberName;

    @Column(name = "MEM_ID")
    private String memberId;

    @Column(name = "MEM_PW")
    private String memberPw;

    @Column(name = "MEM_TEL")
    private String memberTel;

    @Column(name = "MEM_ADDR")
    private String memberAddr;

    @Column(name = "MEM_ZIPCODE")
    private String memberZipcode;

    public static Member toSaveEntity(MemberDTO memberDTO) {
        Member member = new Member();
        member.setMemberName(memberDTO.getMember_Name());
        member.setMemberId(memberDTO.getMember_Id());
        member.setMemberPw(memberDTO.getMember_Pw());
        member.setMemberTel(memberDTO.getMember_Tel());
        member.setMemberAddr(memberDTO.getMember_Addr());
        member.setMemberZipcode(memberDTO.getMember_Zipcode());
        return member;
    }
}
