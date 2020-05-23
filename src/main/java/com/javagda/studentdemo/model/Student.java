package com.javagda.studentdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data // getter setter, tostring hashcode equals
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // identyfikator jest generowany przez bazę
    private Long id;
    private String firstName;
    private String lastName;
    private String indexNumber;
    private int age;
    private boolean suspended;


    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<Grade> grades;

}
