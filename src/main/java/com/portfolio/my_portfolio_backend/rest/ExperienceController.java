package com.portfolio.my_portfolio_backend.rest;

import com.portfolio.my_portfolio_backend.model.Experience;
import com.portfolio.my_portfolio_backend.service.IExperienceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/experience")
public class ExperienceController {
    private final IExperienceService experienceService;


    public ExperienceController(IExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @GetMapping("/all")
    public List<Experience> getAllExperience(){
        return experienceService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Experience> getExperienceById(@PathVariable("id") Long id){
        return experienceService.findById(id);
    }

    @PostMapping
    public Experience save(@RequestBody Experience experience){
        return experienceService.save(experience);
    }

    @PutMapping("/{id}")
    public Experience update(@PathVariable Long id, @RequestBody Experience experience) {
        experience.setId(id);
        return experienceService.save(experience);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        experienceService.deleteById(id);
    }
}
