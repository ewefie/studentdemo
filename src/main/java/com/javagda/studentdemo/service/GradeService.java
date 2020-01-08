package com.javagda.studentdemo.service;

import com.javagda.studentdemo.model.Grade;
import com.javagda.studentdemo.model.Student;
import com.javagda.studentdemo.repository.GradeRepository;
import com.javagda.studentdemo.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GradeService {
    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;

    public void saveORrUpdate(final Grade grade, final Long studentId) {
        final Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {
            grade.setStudent(optionalStudent.get());
            gradeRepository.save(grade);
        }
    }

    public Optional<Grade> find(final Long id) {
        return gradeRepository.findById(id);
    }

    public void delete(final Long id) {
        gradeRepository.deleteById(id);
    }

    public List<Grade> findAll() {
        return gradeRepository.findAll();
    }

}
