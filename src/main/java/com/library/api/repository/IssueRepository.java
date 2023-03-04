package com.library.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.library.api.dao.Issue;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Integer> {
	@Modifying
	@Query(value = "UPDATE Issue SET status = 'RETURNED' WHERE book_name = :book_name AND member_id = :member_id", nativeQuery=true)
	   public void updateBookReturnByBookNameAndMemberIdNative(@Param("book_name")  String book_name, @Param("member_id")  Integer member_id);
}
