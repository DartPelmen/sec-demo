package edu.ivankuznetosov.secdemo.repository;

import edu.ivankuznetosov.secdemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Transactional
    @Query("SELECT t FROM accounts t LEFT JOIN FETCH t.roles where t.username = ?1")

    User findByUsername(String username);
}
