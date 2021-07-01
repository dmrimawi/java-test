package com.dia.studentsclasses.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.dia.studentsclasses.model.Student;

@Component
public class StudentValidator  implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Student.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Student std =  (Student) target;
		if(!std.getPassword().equals(std.getPasswordConfirmation())) {
			errors.rejectValue("passwordConfirmation", "Match");
		}
		if(std.getAge() < 18) {
			errors.rejectValue("age", "Ibrahim");
		}
		
	}

}
