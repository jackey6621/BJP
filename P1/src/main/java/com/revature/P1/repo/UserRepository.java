package com.revature.P1.repo;
import com.revature.P1.model.Reimb;
import com.revature.P1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByUsernameAndPassword(String username, String password);
}