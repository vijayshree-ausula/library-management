package com.library.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.library.test.dao.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
	@Query(value = "SELECT * FROM Genre g where g.genre = :genre", nativeQuery=true)
	   public List<Genre> findBooksByGenreNative(@Param("genre")  String genre);

}
