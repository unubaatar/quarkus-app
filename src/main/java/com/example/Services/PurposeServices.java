package com.example.Services;

import com.example.Entities.Purpose;
import com.example.Repositories.PurposeRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class PurposeServices {
    @Inject
    PurposeRepository PurposeRepository;

    public List<Purpose> getAll() {
        return PurposeRepository.listAll();
    }

    public Optional<Purpose> getById(UUID id) {
        return  PurposeRepository.findById(id);
    }

    @Transactional
    public Purpose create(Purpose purpose) {
        PurposeRepository.persist(purpose);
        return purpose;
    }

    @Transactional
    public Optional<Purpose> update(UUID id,  Purpose purpose) {
        Optional<Purpose> foundPurpose = PurposeRepository.findById(id);
        if(foundPurpose.isEmpty()) {
            return Optional.empty();
        }
        Purpose existingPurpose = foundPurpose.get();
        if (purpose.getTitle() != null) {
            existingPurpose.setTitle(purpose.getTitle());
        }
        if (purpose.getDescription() != null) {
            existingPurpose.setDescription(purpose.getDescription());
        }
        if (purpose.getState() != null) {
            existingPurpose.setState(purpose.getState());
        }
        return Optional.of(existingPurpose);
    }

    @Transactional
    public Optional<Purpose> delete(UUID id) {
        Optional<Purpose> foundPurpose = PurposeRepository.findById(id);
        if (foundPurpose.isEmpty()) {
            return Optional.empty();
        }

        Purpose purposeToDelete = foundPurpose.get();
        PurposeRepository.delete("id", id);

        return Optional.of(purposeToDelete);
    }
}
