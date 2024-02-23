package com.javastudy.member.dto;

import com.javastudy.member.entity.MemberEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter
//@Setter
@NoArgsConstructor
@AllArgsConstructor
//@ToString
@Data
public class MemberDTO {
	private Long id;
	private String email;
	private String password;
	private String name;

	public static MemberDTO toMemberDTO(MemberEntity memberEntity) {
		MemberDTO memberDTO = new MemberDTO();

		memberDTO.setId(memberEntity.getId());
		memberDTO.setEmail(memberEntity.getEmail());
		memberDTO.setPassword(memberEntity.getPassword());
		memberDTO.setName(memberEntity.getName());

		return memberDTO;
	}
}
