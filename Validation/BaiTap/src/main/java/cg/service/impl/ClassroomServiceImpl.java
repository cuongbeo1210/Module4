package cg.service.impl;

import cg.model.Classroom;
import cg.repository.IClassroomRepository;
import cg.service.IClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClassroomServiceImpl implements IClassroomService {
    @Autowired
    private IClassroomRepository iClassroomRepository;

    @Override
    public Iterable<Classroom> findAllClassrooms() {
        return iClassroomRepository.findAll();
    }

    @Override
    public Classroom saveClassroom(Classroom classroom) {
        return null;
    }

    @Override
    public void deleteClassroom(int id) {

    }

    @Override
    public Optional<Classroom> findById(int id) {
        return Optional.empty();
    }
}
