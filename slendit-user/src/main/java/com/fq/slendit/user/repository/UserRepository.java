package com.fq.slendit.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fq.slendit.user.entity.User;
import com.fq.slendit.user.response.VerificationToken;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByEmail(String email);
	
    @Modifying
    @Query("UPDATE User u SET u.password = :newPassword WHERE u.email = :email and u.id = :userId")
    int resetPassword(@Param("newPassword") String newPassword, @Param("email") String email, @Param("userId") int userId);
    
    @Query("SELECT new com.fq.slendit.user.response.VerificationToken(u.verificationCode, u.email, u.updated) FROM User u WHERE u.verificationCode = :token")
    Optional<VerificationToken> findByVerificationCode(@Param("token") String token);

    @Modifying
    @Query("UPDATE User u SET u.verificationCode = NULL, u.isEmailVerified = 'Y' WHERE u.verificationCode = :token")
	void deleteToken(@Param("token") String token);
    
    @Modifying
    @Query("UPDATE User u SET u.email = :newEmail, u.verificationCode = :token, u.isEmailVerified = 'N' WHERE u.email = :currentEmail")
	int updateEmail(@Param("newEmail") String newEmail, @Param("token") String token, @Param("currentEmail") String currentEmail);

	
}
