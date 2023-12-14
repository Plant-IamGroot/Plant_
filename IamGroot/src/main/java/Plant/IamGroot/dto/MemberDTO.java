package Plant.IamGroot.dto;

import Plant.IamGroot.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDTO {

    private Long id;  // 기본키값

    @NotEmpty(message = "* 회원이름은 필수 입력 칸입니다.")
    private String member_Name; // 회원이름

    @NotEmpty(message = "* 회원 아이디는 필수 입력 칸입니다")
    private String member_Id;  // 회원 아이디

    @NotEmpty(message = "* 회원 비밀번호는 필수 입력 칸입니다")
    private String member_Pw;  // 회원 비밀번호

    @NotEmpty(message = "* 회원 전화번호는 필수 입력 칸입니다")
    private String member_Tel;  // 회원 전화번호

    @NotEmpty(message = "* 회원 주소는 필수 입력 칸입니다")
    private String member_Addr;  // 회원 주소

    @NotEmpty(message = "* 회원 우편번호는 필수 입력 칸입니다")
    private String member_Zipcode;  // 회원 우편번호

    public static MemberDTO toSaveDTO(Member member) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMember_Name(member.getMemberName());
        memberDTO.setMember_Id(member.getMemberId());
        memberDTO.setMember_Pw(member.getMemberPw());
        memberDTO.setMember_Tel(member.getMemberTel());
        memberDTO.setMember_Addr(member.getMemberAddr());
        memberDTO.setMember_Zipcode(member.getMemberZipcode());
        return memberDTO;
    }
}
