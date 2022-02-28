package cg.repository;

import cg.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepository extends PagingAndSortingRepository<Student, Integer> {
    Page<Student> findStudentByNameContaining(String name , Pageable pageable);
    Page<Student> findStudentByNumberContaining(String number , Pageable pageable);
}
