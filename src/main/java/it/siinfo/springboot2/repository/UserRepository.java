package it.siinfo.springboot2.repository;

import it.siinfo.springboot2.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    @Query(value = "SELECT u FROM Users u WHERE u.name = :name")
    List<Users> findByName(@Param("name") String name);
    List<Users> findAllByOrderByNameAsc();
}
