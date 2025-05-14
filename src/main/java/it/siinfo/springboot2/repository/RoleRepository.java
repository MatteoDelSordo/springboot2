package it.siinfo.springboot2.repository;

import it.siinfo.springboot2.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}