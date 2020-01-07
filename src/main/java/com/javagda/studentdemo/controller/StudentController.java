package com.javagda.studentdemo.controller;

import com.javagda.studentdemo.model.Student;
import com.javagda.studentdemo.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/student/")
public class StudentController {

    private final StudentService studentService;

    //    @RequestMapping(path = "/", method = RequestMethod.GET) //równoważne
    @GetMapping("/")
    public String getIndexPage() {
//        byc może jako parametr trzeba dodac model
        return "index";
    }

    @GetMapping("/add")
    public String getStudentForm(final Model model, final Student student) {
        model.addAttribute("student", student);
        return "student-form";
    }

    @PostMapping("/add")
    public String submitStudentForm(final Student student) {
        studentService.save(student);
        return "redirect:/student/list";
    }

    @GetMapping("/list")
    public String listStudents(final Model model) {
        final List<Student> studentList = studentService.getAll();
        model.addAttribute("students", studentList);
        return "student-list";
    }

    @GetMapping("/edit/{studentId}")
    public String editForm(final Model model, @PathVariable(name = "studentId") Long id) {
        final Optional<Student> studentOptional = studentService.find(id);
        if (studentOptional.isPresent()) {
            model.addAttribute("student", studentOptional.get());
            return "student-form";
        }
        return "redirect:/student/list";
    }

    @GetMapping("/delete/{studentId}")
    public String delete(@PathVariable(name = "studentId") final Long id) {
        studentService.delete(id);
        return "redirect:/student/list";
    }

    @GetMapping("/grades/{studentId}")
    public String studentGrades(final Model model, @PathVariable(name = "studentId") Long id) {
        final Optional<Student> optionalStudent = studentService.find(id);
        if (optionalStudent.isPresent()) {
            model.addAttribute("grades", optionalStudent.get().getGrades());
            return "grade-list";
        }
        return "redirect:/student/list";

    }

}
