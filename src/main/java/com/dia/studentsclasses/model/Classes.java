package com.dia.studentsclasses.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

@Entity
@Table(name = "classes")
public class Classes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Size(min = 2, max = 40)
	private String name;
	@NotNull
	@Size(min = 2, max = 40)
	private String room;

	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "enroll", 
        joinColumns = @JoinColumn(name = "clid"), 
        inverseJoinColumns = @JoinColumn(name = "stid")
    )
    private List<Student> students;

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Classes() {
	}
	
	public void addStudent(Student std) {
		this.students.add(std);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

}
