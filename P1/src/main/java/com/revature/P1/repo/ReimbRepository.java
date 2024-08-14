package com.revature.P1.repo;

import com.revature.P1.model.Reimb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import java.util.Optional;

@Repository
public interface ReimbRepository extends JpaRepository<Reimb, Integer> {
    List<Reimb> findByStatus(String status);
    List<Reimb> findByUserid(int userid);
    List<Reimb> findByUseridAndStatus(int userid, String status);
}


