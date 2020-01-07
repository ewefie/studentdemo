package com.javagda.studentdemo.repository;

import com.javagda.studentdemo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

//    List<Student> findAllByLastNamOrLastName(final String lastName);

//    @Query(value = "Select*from... where ?1", nativeQuery = true)
//    List<Student> mojeQuery(final String lastName);

}
