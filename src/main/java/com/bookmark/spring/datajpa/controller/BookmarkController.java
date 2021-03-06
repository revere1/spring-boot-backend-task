package com.bookmark.spring.datajpa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookmark.spring.datajpa.model.Bookmark;
import com.bookmark.spring.datajpa.repository.BookmarkRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class BookmarkController {

	@Autowired
	BookmarkRepository bookmarkRepository;

	@GetMapping("/bookmarks")
	public ResponseEntity<List<Bookmark>> getAllBookmarks(@RequestParam(required = false) String repositoryname) {
		try {
			List<Bookmark> bookmarks = new ArrayList<Bookmark>();

			if (repositoryname == null)
				bookmarkRepository.findAll().forEach(bookmarks::add);
			else
				bookmarkRepository.findByRepositorynameContaining(repositoryname).forEach(bookmarks::add);

			if (bookmarks.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(bookmarks, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/bookmarks/{id}")
	public ResponseEntity<Bookmark> getBookmarkById(@PathVariable("id") long id) {
		Optional<Bookmark> bookmarkData = bookmarkRepository.findById(id);

		if (bookmarkData.isPresent()) {
			return new ResponseEntity<>(bookmarkData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/bookmarks")
	public ResponseEntity<Bookmark> createBookmark(@RequestBody Bookmark bookmark) {
		try {
			Bookmark _bookmark = bookmarkRepository
					.save(new Bookmark(bookmark.getRepositoryname(), bookmark.getRepositorylink(), bookmark.getRepositorydescription()));
			return new ResponseEntity<>(_bookmark, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}

	@PutMapping("/bookmarks/{id}")
	public ResponseEntity<Bookmark> updateBookmark(@PathVariable("id") long id, @RequestBody Bookmark bookmark) {
		Optional<Bookmark> bookmarkData = bookmarkRepository.findById(id);

		if (bookmarkData.isPresent()) {
			Bookmark _bookmark = bookmarkData.get();
			_bookmark.setRepositoryname(bookmark.getRepositoryname());
			_bookmark.setRepositorylink(bookmark.getRepositorylink());
			_bookmark.setRepositorydescription(bookmark.getRepositorydescription());
			return new ResponseEntity<>(bookmarkRepository.save(_bookmark), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/bookmarks/{id}")
	public ResponseEntity<HttpStatus> deleteBookmark(@PathVariable("id") long id) {
		try {
			bookmarkRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	@DeleteMapping("/bookmarks")
	public ResponseEntity<HttpStatus> deleteAllBookmarks() {
		try {
			bookmarkRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}

	}

	@GetMapping("/bookmarks/link")
	public ResponseEntity<List<Bookmark>> findByRepositoryLink() {
		try {
			String repositorylink = null;
			List<Bookmark> bookmarks = bookmarkRepository.findByRepositorylink(repositorylink);

			if (bookmarks.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(bookmarks, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

}
