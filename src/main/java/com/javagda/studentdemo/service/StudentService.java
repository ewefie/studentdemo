package com.javagda.studentdemo.service;

import com.javagda.studentdemo.model.Student;
import com.javagda.studentdemo.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public void save(final Student student) {
        studentRepository.save(student);
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> find(final Long id) {
        return studentRepository.findById(id);
    }

    public void delete(final Long id) {
        studentRepository.deleteById(id);
    }
}
