package com.javastudy.member.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.javastudy.member.dto.MemberDTO;
import com.javastudy.member.entity.MemberEntity;
import com.javastudy.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	public void save(MemberDTO memberDTO) {

		// repository 의 save method 호출(entity 객체를 넘겨 줘야함)
		MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
//		MemberEntity memberEntity = new MemberEntity();
//		memberEntity.setEmail(memberDTO.getEmail());
//		memberEntity.setPassword(memberDTO.getPassword());
//		memberEntity.setName(memberDTO.getName());

		memberRepository.save(memberEntity);

	}

	public MemberDTO login(MemberDTO memberDTO) {
		Optional<MemberEntity> byMemberEmail = memberRepository.findByEmail(memberDTO.getEmail());

		if (byMemberEmail.isPresent()) {
			MemberEntity memberEntity = byMemberEmail.get();
			if (memberEntity.getPassword().equals(memberDTO.getPassword())) {
				// entity 를 dto 로 변환하여 return
				MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
				return dto;
			}
		} else {
			return null;
		}
		return memberDTO;
	}

	public List<MemberDTO> findAll() {
		List<MemberEntity> memberEntityList = memberRepository.findAll();

		// entity 를 dto 로 변환
		List<MemberDTO> memberDTOList = new ArrayList<>();
		for (MemberEntity memberEntity : memberEntityList) {
			memberDTOList.add(MemberDTO.toMemberDTO(memberEntity));
		}
		return memberDTOList;
	}

	public MemberDTO findById(Long id) {
		Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
		if (optionalMemberEntity.isPresent()) {
//			MemberEntity memberEntity = optionalMemberEntity.get();
//			MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
//			return memberDTO;
			// 위와 동일하다
			return MemberDTO.toMemberDTO(optionalMemberEntity.get());
		} else {
			return null;
		}
	}

	public MemberDTO updateForm(String myEmail) {
		Optional<MemberEntity> optionalMemberEntity = memberRepository.findByEmail(myEmail);
		if (optionalMemberEntity.isPresent()) {
			return MemberDTO.toMemberDTO(optionalMemberEntity.get());
		} else {
			return null;
		}
	}

	public void update(MemberDTO memberDTO) {
		memberRepository.save(MemberEntity.toUpdateMemberEntity(memberDTO));

	}

	public void deleteById(Long id) {
		memberRepository.deleteById(id);

	}

	public String emailCheck(String email) {
		Optional<MemberEntity> byMemberEmail = memberRepository.findByEmail(email);
		if (byMemberEmail.isPresent()) {
			return null;
		} else {
			return "ok";
		}
	}
}
