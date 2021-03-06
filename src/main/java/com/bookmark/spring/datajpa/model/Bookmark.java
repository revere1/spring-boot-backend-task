package com.bookmark.spring.datajpa.model;

import javax.persistence.*;

@Entity
@Table(name = "bookmarks")
public class Bookmark {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "repositoryname")
	private String repositoryname;

	@Column(name = "repositorylink")
	private String repositorylink;
	
	@Column(name = "repositorydescription")
	private String repositorydescription;

	

	public Bookmark() {

	}

	public Bookmark(String repositoryname, String repositorylink, String repositorydescription) {
		this.repositoryname = repositoryname;
		this.repositorylink = repositorylink;
		this.repositorydescription = repositorydescription;
	}

	public long getId() {
		return id;
	}
	public String getRepositoryname() {
		return repositoryname;
	}

	public void setRepositoryname(String repositoryname) {
		this.repositoryname = repositoryname;
	}

	public String getRepositorylink() {
		return repositorylink;
	}

	public void setRepositorylink(String repositorylink) {
		this.repositorylink = repositorylink;
	}

	public String getRepositorydescription() {
		return repositorydescription;
	}

	public void setRepositorydescription(String repositorydescription) {
		this.repositorydescription = repositorydescription;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Bookmark [id=" + id + ", repositoryname=" + repositoryname + ", repositorylink=" + repositorylink + ", repositorydescription=" + repositorydescription + "]";
	}

}
