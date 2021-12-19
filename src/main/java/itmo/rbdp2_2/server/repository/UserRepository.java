package itmo.rbdp2_2.server.repository;

import itmo.rbdp2_2.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long id);
    User findUserByUsernameAndPassword(String username, String password);
}
