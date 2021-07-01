package com.dia.studentsclasses.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "enroll")
public class Enroll {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stid")
	private Student std;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "clid")
	private Classes cls;

	public Enroll() {}
	

	public Enroll(Student std, Classes cls) {
		this.std = std;
		this.cls = cls;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Student getStd() {
		return std;
	}

	public void setStd(Student std) {
		this.std = std;
	}

	public Classes getCls() {
		return cls;
	}

	public void setCls(Classes cls) {
		this.cls = cls;
	}
	
	

}
