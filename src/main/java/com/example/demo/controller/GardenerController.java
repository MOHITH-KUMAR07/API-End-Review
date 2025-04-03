package com.example.demo.controller;

import com.example.demo.entity.Gardener;
import com.example.demo.service.GardenerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/gardeners")
public class GardenerController {

    private final GardenerService gardenerService;

    public GardenerController(GardenerService gardenerService) {
        this.gardenerService = gardenerService;
    }

    @PostMapping("/post")
    public Gardener createGardener(@RequestBody Gardener gardener) {
        return gardenerService.saveGardener(gardener);
    }

    @GetMapping
    public ResponseEntity<List<Gardener>> getAllGardeners() {
        return ResponseEntity.ok(gardenerService.getAllGardenersList());
    }

    @GetMapping("/{id}")
    public Optional<Gardener> getGardenerById(@PathVariable Long id) {
        return gardenerService.getGardenerById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateGardener(@PathVariable Long id, @RequestBody Gardener gardener) {
        try {
            Gardener updatedGardener = gardenerService.updateGardener(id, gardener);
            return ResponseEntity.ok(updatedGardener);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/page")
    public Page<Gardener> getAllGardeners(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return gardenerService.getAllGardeners(pageable);
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<Gardener>> getGardenersWithSorting(
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") 
                   ? Sort.by(sortBy).descending() 
                   : Sort.by(sortBy).ascending();
        return ResponseEntity.ok(gardenerService.getGardenerWithSorting(sort));
    }

    @DeleteMapping("/{id}")
    public String deleteGardener(@PathVariable Long id) {
        gardenerService.deleteGardener(id);
        return "Gardener deleted successfully!";
    }

    @GetMapping("/search")
    public List<Gardener> findByName(@RequestParam String name) {
        return gardenerService.findByName(name);
    }

    @DeleteMapping("/name")
    public String deleteByName(@RequestParam String name) {
        gardenerService.deleteByName(name);
        return "Deleted successfully!";
    }

    @GetMapping("/contact")
    public List<Gardener> findByContact(@RequestParam String contact) {
        return gardenerService.findByContact(contact);
    }

    @GetMapping("/experience")
    public List<Gardener> findByExperience(@RequestParam String experience) {
        return gardenerService.findByExperience(experience);
    }
}