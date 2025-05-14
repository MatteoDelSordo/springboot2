package it.siinfo.springboot2.repository;

import it.siinfo.springboot2.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {


    Optional<RefreshToken> findByToken(String string);

    Optional<RefreshToken> findByUser_Id (Long id);

    Optional<RefreshToken> findByUser_EMail (String eMail);


}
