package cg.service.impl;

import cg.model.Human;
import cg.repository.IHumanRepository;
import cg.service.IHumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class IHumanServiceImpl implements IHumanService {
    @Autowired
    private IHumanRepository iHumanRepository;

    @Override
    public Page<Human> findAllHuman(Pageable pageable) {
        return iHumanRepository.findAll(pageable);
    }

    @Override
    public Human saveHuman(Human human) {
        return iHumanRepository.save(human);
    }

    @Override
    public void deleteHuman(int id) {
        iHumanRepository.deleteById(id);
    }

    @Override
    public Human findById(int id) {
        if (iHumanRepository.findById(id).isPresent()) {
            return iHumanRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public Page<Human> getAllHumanByName(String name, Pageable pageable) {
        return iHumanRepository.findHumanByNameContaining(name, pageable);
    }
}
