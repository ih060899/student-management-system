package com.imran.student.sevice;

import com.imran.student.entity.StudentEntity;
import com.imran.student.model.Student;
import com.imran.student.repository.StudentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{
    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        List<StudentEntity> studentEntities = studentRepository.findAll();
        List<Student> students = studentEntities.stream().map(entity ->
                new Student(
                        entity.getStudentId(),
                        entity.getFirstName(),
                        entity.getLastName(),
                        entity.getEmailId(),
                        entity.getDepartment())
                ).collect(Collectors.toList());

        return students;
    }

    @Override
    public Student getStudentById(long id) {
        StudentEntity studentEntity = studentRepository.findById(id).get();
        Student student = new Student();
        BeanUtils.copyProperties(studentEntity, student);
        return student;
    }

    @Override
    public Student createStudent(Student student) {
        StudentEntity studentEntity = new StudentEntity();
        BeanUtils.copyProperties(student, studentEntity);
        studentRepository.save(studentEntity);
        return student;
    }

    @Override
    public Student updateStudentById(long id, Student student) {
        StudentEntity studentEntity = studentRepository.findById(id).get();
        if(student.getFirstName() != null && !student.getFirstName().equals(""))
            studentEntity.setFirstName(student.getFirstName());
        if(student.getLastName() != null && !student.getLastName().equals(""))
            studentEntity.setLastName(student.getLastName());
        if(student.getEmailId() != null && !student.getEmailId().equals(""))
            studentEntity.setEmailId(student.getEmailId());
        if(student.getFirstName() != null && !student.getDepartment().equals(""))
            studentEntity.setDepartment(student.getDepartment());
        studentRepository.save(studentEntity);

        return student;
    }

    @Override
    public boolean deleteStudentById(long id) {
        StudentEntity studentEntity = studentRepository.findById(id).get();
        studentRepository.delete(studentEntity);
        return true;
    }
}
