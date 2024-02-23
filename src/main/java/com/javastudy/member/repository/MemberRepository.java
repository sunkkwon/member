package com.javastudy.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javastudy.member.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

	// 이메일로 조회
	Optional<MemberEntity> findByEmail(String email);
}
