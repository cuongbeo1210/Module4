package cg.service;

import cg.model.Human;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IHumanService {
    Page<Human> findAllHuman(Pageable pageable);

    Human saveHuman(Human human);

    void deleteHuman(int id);

    Human findById(int id);

    Page<Human> getAllHumanByName(String name, Pageable pageable);
}
