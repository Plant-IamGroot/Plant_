package Plant.IamGroot.service;

import Plant.IamGroot.dto.MemberDTO;
import Plant.IamGroot.entity.Member;
import Plant.IamGroot.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public void save(MemberDTO memberDTO) {
        Member member = Member.toSaveEntity(memberDTO);
        memberRepository.save(member);
    }

    public MemberDTO login(MemberDTO memberDTO) {
        Optional<Member> mem_Id = memberRepository.findByMemberId(memberDTO.getMember_Pw());  // Optional => null 방지
        if (mem_Id.isPresent()){
            // 조회 결과 있을 시
            Member member = mem_Id.get();
            if (member.getMemberPw().equals(memberDTO.getMember_Pw())) {
                // PW 일치 시
                MemberDTO dto = MemberDTO.toSaveDTO(member);
                return dto;
            } else {
                // 불일치 시
                return null;
            }
        } else {
            // 없을 시
            return null;
        }
    }
}
