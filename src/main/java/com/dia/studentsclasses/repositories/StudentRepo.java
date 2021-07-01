package com.dia.studentsclasses.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dia.studentsclasses.model.Student;

@Repository
public interface StudentRepo extends CrudRepository<Student, Long>{
	Student findByEmail(String email);
}
