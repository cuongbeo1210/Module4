package cg.service;

import cg.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IStudentService {
    Page<Student> findAllStudent(Pageable pageable);

    Student saveStudent(Student student);

    void deleteStudent(int id);

    Student findById(int id);

    Page<Student> getAllStudentsByName(String name, Pageable pageable);

    Page<Student> findStudentByNumber(String number, Pageable pageable);
}
