package Plant.IamGroot.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class MemberFormDto {

    @NotBlank(message = "* 이름은 필수 입력 값입니다.")
    private String name; // 회원이름

    @NotEmpty(message = "* 이메일은 필수 입력 칸입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;  // 회원 아이디

    @NotEmpty(message = "* 회원 비밀번호는 필수 입력 칸입니다")
    @Length(min=5, max = 15, message = "비밀번호는 5자이상, 15자 이하로 입력해주세요.")
    private String password;  // 회원 비밀번호

    @NotEmpty(message = "* 회원 주소는 필수 입력 칸입니다")
    private String address;  // 회원 주소

}
