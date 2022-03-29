package com.imran.student.sevice;

import com.imran.student.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();

    Student getStudentById(long id);

    Student createStudent(Student student);

    Student updateStudentById(long id, Student student);

    boolean deleteStudentById(long id);
}
