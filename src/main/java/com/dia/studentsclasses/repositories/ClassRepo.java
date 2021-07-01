package com.dia.studentsclasses.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dia.studentsclasses.model.Classes;
import com.dia.studentsclasses.model.Student;

@Repository
public interface ClassRepo extends CrudRepository<Classes, Long> {
	// @Query("SELECT c FROM Classes c WHERE c.id NOT IN (SELECT c.id FROM Classes c, Student s, Enroll e WHERE c.id=e.clid and s.id=e.stid and s.id = ?1)")
	// List<Classes> getOtherClasses(Long stdid);
	List<Classes> findByStudentsNotContains(Student s);
		
}
