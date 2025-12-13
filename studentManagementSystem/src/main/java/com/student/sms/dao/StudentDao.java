package com.student.sms.dao;

import java.util.List;
import com.student.sms.model.Student;

public interface StudentDao {
    void saveStudent(Student student);

    void updateStudent(Student student);

    Student getStudentById(int id);

    List<Student> getAllStudents();

    void deleteStudent(int id);
}
