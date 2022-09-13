package code.user;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
    User findById(int userId);
    User findByEmailAndPassword(String email, String password);
    User findByEmail(String email);
    void deleteById(int userId);
}
