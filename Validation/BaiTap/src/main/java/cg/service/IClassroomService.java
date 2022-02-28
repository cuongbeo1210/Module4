package cg.service;

import cg.model.Classroom;

import java.util.Optional;

public interface IClassroomService {
    Iterable<Classroom> findAllClassrooms();

    Classroom saveClassroom(Classroom classroom);

    void deleteClassroom(int id);

    Optional<Classroom> findById(int id);
}
