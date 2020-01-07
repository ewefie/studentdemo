package com.javagda.studentdemo.controller;

import com.javagda.studentdemo.model.Grade;
import com.javagda.studentdemo.model.GradeSubject;
import com.javagda.studentdemo.service.GradeService;
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
@RequestMapping("/grade/")
public class GradeController {
    private final GradeService gradeService;

    @GetMapping("/add/{studentId}")
    public String getGradeForm(final Model model, final Grade grade, @PathVariable(name = "studentId") final Long studentId) {
        model.addAttribute("grade", grade);
        model.addAttribute("studentId", studentId);
        model.addAttribute("subjects", GradeSubject.values());
        return "grade-form";
    }

    @GetMapping("/edit/{id}")
    public String editForm(final Model model, @PathVariable(name = "id") Long id) {
        Optional<Grade> optionalGrade = gradeService.find(id);
        if (optionalGrade.isPresent()) {
            model.addAttribute("grade", optionalGrade.get());
            model.addAttribute("subjects", GradeSubject.values());
            model.addAttribute("studentId", optionalGrade.get().getStudent().getId());
            return "grade-form";
        }
        return "redirect:/grade/list";
    }

    @PostMapping("/add")
    public String submitGradeForm(final Grade grade, final Long studentId) {
        gradeService.save(grade, studentId);
        return "redirect:/grade/list";
    }

    @GetMapping("/list")
    public String listGrades(final Model model) {
        List<Grade> grades = gradeService.findAll();
        model.addAttribute("grades", grades);
        return "/grade-list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") Long gradeId) {
        gradeService.delete(gradeId);
        return "redirect:/grade/list";
    }

}
