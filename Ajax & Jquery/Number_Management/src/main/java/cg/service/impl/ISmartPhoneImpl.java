package cg.service.impl;

import cg.model.SmartPhone;
import cg.repository.ISmartPhoneRepository;
import cg.service.ISmartPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ISmartPhoneImpl implements ISmartPhoneService {
    @Autowired
    private ISmartPhoneRepository iSmartPhoneRepository;

    @Override
    public Iterable<SmartPhone> findAll() {
        return iSmartPhoneRepository.findAll();
    }

    @Override
    public Optional<SmartPhone> findById(Long id) {
        return iSmartPhoneRepository.findById(id);
    }

    @Override
    public SmartPhone save(SmartPhone smartPhone) {
        return iSmartPhoneRepository.save(smartPhone);
    }

    @Override
    public void remove(Long id) {
        iSmartPhoneRepository.deleteById(id);
    }
}
