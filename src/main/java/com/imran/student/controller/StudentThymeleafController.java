package com.imran.student.controller;

import com.imran.student.model.Student;
import com.imran.student.sevice.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentThymeleafController {
    private StudentService studentService;

    public StudentThymeleafController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String viewHomePage(Model model){
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "index";
    }

    @GetMapping("/studentform")
    public String studentForm(Model model){
        Student student = new Student();
         model.addAttribute("student", student);
         return "new_student";
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student") Student student){
        studentService.createStudent(student);
        return "redirect:/";
    }

//    @PostMapping("/update")
//    public String updateStudent(@ModelAttribute("student") Student student){
//        studentService.updateStudentById(student.getStudentId(), student);
//        return "redirect:/";
//    }

    @GetMapping("/updateStudent/{id}")
    public String updateStudent(@PathVariable long id, Model model){
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "update_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable long id){
        studentService.deleteStudentById(id);
        return "redirect:/";
    }
}
