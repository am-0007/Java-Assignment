package internsathi.javaAssignment.repository;

import internsathi.javaAssignment.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.Optional;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.username = ?1")
    Optional<User> findByUsername(String username);

    @Query("select u from User u where u.username = ?1 and u.email = ?2")
    Optional<User> findByUsernameAndEmail(String username, String email);

    @Modifying
    @Query("update User u set u.password=?2 where u.username =?1")
    void updateUserPassword(String username, String password);
}
