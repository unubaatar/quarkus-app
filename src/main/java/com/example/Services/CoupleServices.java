package com.example.Services;

import com.example.Entities.Couple;
import com.example.Repositories.CoupleRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class CoupleServices {
    @Inject CoupleRepository CoupleRepository;

    public List<Couple> getAll() {
        return CoupleRepository.listAll();
    }

    public Optional<Couple> getByName(String name) {
        return CoupleRepository.findByName(name);
    }

    public Optional<Couple> getById(UUID id) {
        return CoupleRepository.findById(id);
    }

    @Transactional
    public Couple create(Couple couple) {
        CoupleRepository.persist(couple);
        return couple;
    }

    @Transactional
    public  Optional<Couple>  update( UUID id ,  Couple updatedCouple) {
        Optional<Couple> foundCouple = CoupleRepository.findById(id);
        if(foundCouple.isEmpty()) {
            return Optional.empty();
        }
        Couple existingCouple = foundCouple.get();
        existingCouple.setCoupleName(updatedCouple.getCoupleName());
        existingCouple.setState(updatedCouple.getState());
        return Optional.of(existingCouple);
    }

    public long delete(UUID id) {
        return CoupleRepository.delete("id" , id);
    }

}
