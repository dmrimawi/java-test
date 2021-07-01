package com.dia.studentsclasses.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dia.studentsclasses.model.Classes;
import com.dia.studentsclasses.model.Student;
import com.dia.studentsclasses.services.SchoolService;
import com.dia.studentsclasses.validator.StudentValidator;

@Controller
public class SchoolController {

	private final SchoolService schoolService;
	private final StudentValidator stdValid;

	public SchoolController(SchoolService schoolService, StudentValidator stdValid) {
		this.schoolService = schoolService;
		this.stdValid = stdValid;
	}

	@RequestMapping("")
	public String home(@ModelAttribute("student") Student std, @ModelAttribute("cls") Classes cls) {
		return "index.jsp";
	}

	@RequestMapping(value = "/new/student", method = RequestMethod.POST)
	public String newStudent(@Valid @ModelAttribute("student") Student std, BindingResult result, Model model) {
		stdValid.validate(std, result);
		if (result.hasErrors()) {
			model.addAttribute("cls", new Classes());
			return "index.jsp";
		}
		schoolService.createStudent(std);
		return "redirect:/";
	}

	@RequestMapping(value = "/new/class", method = RequestMethod.POST)
	public String newStudent(@Valid @ModelAttribute("cls") Classes cls, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("student", new Student());
			return "index.jsp";
		}
		schoolService.createClass(cls);
		return "redirect:/";
	}

	private Model addModelAttr(Long id, Model model, Student std) {
		model.addAttribute("std", std);
		model.addAttribute("myclasses", std.getClasses());
		model.addAttribute("otherclasses", schoolService.getOtherClasses(id));
		return model;
	}
	
	@RequestMapping("/student/{id}")
	public String showStudent(@PathVariable("id") Long id, Model model) {
		Student std = schoolService.getStudent(id);
		if (std != null) {
			model = addModelAttr(id, model, std);
			return "student.jsp";
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/enroll/{id}", method = RequestMethod.POST)
	public String newStudent(@PathVariable("id") Long id, @RequestParam("cls_type") Long clsId, Model model) {
		Student std = schoolService.getStudent(id);
		if (std != null) {
			std = schoolService.enrollStudent(std, clsId);
			model = addModelAttr(id, model, std);
		}
		return "redirect:/student/" + id;
	}

	
	
	@RequestMapping("/drop/{stdid}/{clsid}")
	public String dropStd(@PathVariable("stdid") Long stdid, @PathVariable("clsid") Long clsid,  Model model) {
		Student std = schoolService.getStudent(stdid);
		Classes cls = schoolService.getClasses(clsid);
		if (std != null && cls != null) {
			model = addModelAttr(stdid, model, std);
			for(Classes clas: std.getClasses()) {
				if (clas.getId() == cls.getId()) {
					std.getClasses().remove(clas);
					schoolService.updateStudent(std);
					break;
				}
			}
		}
		return "redirect:/student/" + stdid;
	}
}
