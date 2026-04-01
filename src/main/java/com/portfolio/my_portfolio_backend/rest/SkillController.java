package com.portfolio.my_portfolio_backend.rest;

import com.portfolio.my_portfolio_backend.model.Skill;
import com.portfolio.my_portfolio_backend.service.ISkillService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/skill")
public class SkillController {

    private final ISkillService skillService;

    public SkillController(ISkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping("/all")
    public List<Skill> getAllSkills(){
        return skillService.findAll();
    }

    @GetMapping("/{id}")
    public Skill getById(@PathVariable Long id){
        return skillService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Skill no encontrada con id: " + id
                ));
    }

    @PostMapping
    public Skill save (@RequestBody Skill skill){
        return skillService.save(skill);
    }

    @PutMapping
    public Skill update(@PathVariable Long id, @RequestBody Skill skill){
        skill.setId(id);
        return skillService.save(skill);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        skillService.deleteById(id);
    }
}
