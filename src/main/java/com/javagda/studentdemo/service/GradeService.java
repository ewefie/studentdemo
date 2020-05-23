package com.javagda.studentdemo.service;

import com.javagda.studentdemo.model.Grade;
import com.javagda.studentdemo.model.Student;
import com.javagda.studentdemo.repository.GradeRepository;
import com.javagda.studentdemo.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GradeService {
    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;

    @Transactional
    public void saveOrUpdate(final Grade grade, final Long studentId) {
                final Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {

            if (grade.getId()!= null){
                grade.setDateAdded(gradeRepository.findById(grade.getId()).get().getDateAdded());
            }
            grade.setStudent(optionalStudent.get());
            gradeRepository.save(grade);

        }
    }

//    czy to tak powinno być?
//    public void update (final  Grade grade, final Long studentId){
//            final Optional<Grade> optionalGrade = gradeRepository.findById(grade.getId());
//            if(optionalGrade.isPresent()){
//                Grade gradeToSave = optionalGrade.get();
//               gradeToSave.setSubject(grade.getSubject());
//               gradeToSave.setValue(grade.getValue());
//                gradeRepository.save(gradeToSave);
//            }
//    }

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
