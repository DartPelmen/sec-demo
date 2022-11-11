package edu.ivankuznetosov.secdemo.repository;

import edu.ivankuznetosov.secdemo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface RoleRepository extends JpaRepository<Role,String> {
}
