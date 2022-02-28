package cg.service.impl;

import cg.model.Student;
import cg.repository.IStudentRepository;
import cg.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements IStudentService {
    @Autowired
    private IStudentRepository iStudentRepository;

    @Override
    public Page<Student> findAllStudent(Pageable pageable) {
        return iStudentRepository.findAll(pageable);
    }

    @Override
    public Student saveStudent(Student student) {
        return iStudentRepository.save(student);
    }

    @Override
    public void deleteStudent(int id) {
        iStudentRepository.deleteById(id);
    }

    @Override
    public Student findById(int id) {
        if (iStudentRepository.findById(id).isPresent()) {
            return iStudentRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public Page<Student> getAllStudentsByName(String name, Pageable pageable) {
        return iStudentRepository.findStudentByNameContaining(name, pageable);
    }

    @Override
    public Page<Student> findStudentByNumber(String number, Pageable pageable) {
        return iStudentRepository.findStudentByNumberContaining(number, pageable);
    }
}
