package com.library.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.library.api.dao.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
	
	@Query(value = "SELECT * FROM Member m where m.email = :email", nativeQuery=true)
	   public Member findMemberByEmailNative(@Param("email")  String email);
	
	@Query(value = "SELECT * FROM Member m where m.phone = :phone", nativeQuery=true)
	   public Member findMemberByPhoneNative(@Param("phone")  String phone);
	
	@Query(value = "SELECT * FROM Member m where m.name = :name", nativeQuery=true)
	   public Member findMemberByNameNative(@Param("name")  String name);
	
	@Query(value = "SELECT * FROM Member m where m.id = :id", nativeQuery=true)
	   public List<Member> findMemberByIdNative(@Param("id")  Integer id);
}
