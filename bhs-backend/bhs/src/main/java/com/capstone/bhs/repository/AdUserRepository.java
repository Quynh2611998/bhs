package com.capstone.bhs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capstone.bhs.model.entity.AdUser;

@Repository
public interface AdUserRepository extends JpaRepository<AdUser, Long> {

	Optional<AdUser> findOneById(Long id);

	Optional<AdUser> findOneByUsername(String username);

	Optional<AdUser> findOneByActiveKey(String activeKey);

	Optional<AdUser> findOneByUsernameIgnoreCase(String username);

	Optional<AdUser> findOneByResetKey(String key);

	Optional<AdUser> findOneById(AdUser adUserId);

	@Query(value = "SELECT ad.id FROM AdUser ad WHERE ad.username = :username")
	Object findUserIdByUserName(@Param("username") String username);
}
