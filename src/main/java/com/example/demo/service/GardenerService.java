package com.example.demo.service;

import com.example.demo.entity.Gardener;
import com.example.demo.repository.GardenerRepository;

import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GardenerService {

    private final GardenerRepository gardenerRepository;

    public GardenerService(GardenerRepository gardenerRepository) {
        this.gardenerRepository = gardenerRepository;
    }

    public Gardener saveGardener(Gardener gardener) {
        return gardenerRepository.save(gardener);
    }

    public Optional<Gardener> getGardenerById(Long id) {
        return gardenerRepository.findById(id);
    }

    public Page<Gardener> getAllGardeners(Pageable pageable) {
        return gardenerRepository.findAll(pageable);
    }

    public void deleteGardener(Long id) {
        gardenerRepository.deleteById(id);
    }

    public List<Gardener> getGardenerWithSorting(Sort sort) {
        return gardenerRepository.findAll(sort);
    }

    public List<Gardener> findByName(String name) {
        return gardenerRepository.findByName(name);
    }

    public List<Gardener> getAllGardenersList() {
        return gardenerRepository.findAll();
    }

    @Transactional
    public int deleteByName(String name) {
        return gardenerRepository.deleteByName(name);
    }

    public List<Gardener> findByContact(String contact) {
        return gardenerRepository.findByContact(contact);
    }

    public List<Gardener> findByExperience(String experience) {
        return gardenerRepository.findByExperience(experience);
    }

    public Gardener updateGardener(Long id, Gardener gardenerDetails) {
        Gardener existingGardener = gardenerRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Gardener not found with id: " + id));
        existingGardener.setName(gardenerDetails.getName());
        existingGardener.setExperience(gardenerDetails.getExperience());
        existingGardener.setContact(gardenerDetails.getContact());
        return gardenerRepository.save(existingGardener);
    }
}