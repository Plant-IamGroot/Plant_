package Plant.IamGroot.repository;

import Plant.IamGroot.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // 회원 아이디로 회원정보 조회
    Optional<Member> findByMemberId(String member_id);
}
