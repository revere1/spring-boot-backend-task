package com.bookmark.spring.datajpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookmark.spring.datajpa.model.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
	List<Bookmark> findByRepositorylink(String repositorylink);
	List<Bookmark> findByRepositorynameContaining(String repositoryname);
}
