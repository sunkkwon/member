package com.javastudy.member.entity;

import com.javastudy.member.dto.MemberDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "member")
public class MemberEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
	private Long id;

	@Column(unique = true, length = 50)
	private String email;

	@Column(length = 100)
	private String password;

	@Column(length = 30)
	private String name;

	public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
		MemberEntity memberEntity = new MemberEntity();

		memberEntity.setEmail(memberDTO.getEmail());
		memberEntity.setPassword(memberDTO.getPassword());
		memberEntity.setName(memberDTO.getName());

		return memberEntity;
	}

	public static MemberEntity toUpdateMemberEntity(MemberDTO memberDTO) {
		MemberEntity memberEntity = new MemberEntity();

		memberEntity.setId(memberDTO.getId());
		memberEntity.setEmail(memberDTO.getEmail());
		memberEntity.setPassword(memberDTO.getPassword());
		memberEntity.setName(memberDTO.getName());

		return memberEntity;
	}
}
