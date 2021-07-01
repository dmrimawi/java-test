package com.dia.studentsclasses.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.dia.studentsclasses.model.Classes;
import com.dia.studentsclasses.model.Enroll;
import com.dia.studentsclasses.model.Student;
import com.dia.studentsclasses.repositories.ClassRepo;
import com.dia.studentsclasses.repositories.EnrollRepo;
import com.dia.studentsclasses.repositories.StudentRepo;

@Service
public class SchoolService {
	private final StudentRepo stdRepo;
	private final ClassRepo clsRepo;
	private final EnrollRepo enrollRepo;

	public SchoolService(StudentRepo stdRepo, ClassRepo clsRepo, EnrollRepo enrollRepo) {
		this.stdRepo = stdRepo;
		this.clsRepo = clsRepo;
		this.enrollRepo = enrollRepo;
	}

	public Student createStudent(Student std) {
		String hashed = BCrypt.hashpw(std.getPassword(), BCrypt.gensalt());
        std.setPassword(hashed);
		return stdRepo.save(std);

	}

	public Student updateStudent(Student std) {
		return stdRepo.save(std);
	}

	public Classes createClass(Classes cls) {
		return clsRepo.save(cls);

	}

	public Enroll enrollAStudent(Student std, Classes cls) {
		Enroll en = new Enroll(std, cls);
		return enrollRepo.save(en);
	}

	public Student getStudent(Long id) {
		Optional<Student> std = stdRepo.findById(id);
		if (std.isPresent()) {
			return std.get();
		}
		return null;
	}
	
	public Classes getClasses(Long id) {
		Optional<Classes> cls = clsRepo.findById(id);
		if (cls.isPresent()) {
			return cls.get();
		}
		return null;
	}

	public List<Classes> getOtherClasses(Long id) {
		Optional<Student> std = stdRepo.findById(id);
		if (std.isPresent()) {
			return clsRepo.findByStudentsNotContains(std.get());
			// return clsRepo.getOtherClasses(id);
		}
		return null;
	}

	public Student enrollStudent(Student std, Long clsId) {
		Optional<Classes> cls = clsRepo.findById(clsId);
		if (cls.isPresent()) {
			this.enrollAStudent(std, cls.get());
		}
		return std;
	}

	public Student registerStudent(Student std) {
        String hashed = BCrypt.hashpw(std.getPassword(), BCrypt.gensalt());
        std.setPassword(hashed);
        return stdRepo.save(std);
    }
    
    public Student findByEmail(String email) {
        return stdRepo.findByEmail(email);
    }
    
    public boolean authenticateUser(String email, String password) {
        // first find the user by email
    	Student std = stdRepo.findByEmail(email);
        // if we can't find it by email, return false
        if(std == null) {
            return false;
        } else {
            // if the passwords match, return true, else, return false
            if(BCrypt.checkpw(password, std.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }
    
}
