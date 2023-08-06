package Hackerton.fashable.repo;

import Hackerton.fashable.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface UserRepo extends CrudRepository<User, String> {
    //이메일로 회원 정보 조회 (select * from member_table where member_email=?)
    Optional<User> findByEmail(String email);

    static boolean existsByEmail(String email) {
        return false;
    }

    static boolean existsByNickname(String nickName) {
        return false;
    }
}


// https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html
// CrudRepository에 어떤 함수들이 구현되어 있는지 확인할 수 있습니다!
