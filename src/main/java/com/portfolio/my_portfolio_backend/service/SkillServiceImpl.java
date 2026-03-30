package com.portfolio.my_portfolio_backend.service;

import com.portfolio.my_portfolio_backend.model.Skill;
import com.portfolio.my_portfolio_backend.repository.ISkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements ISkillService{
    private final ISkillRepository skillRepository;

    @Override
    public List<Skill> findAll() {
        return skillRepository.findAll();
    }

    @Override
    public Optional<Skill> findById(Long id) {
        return skillRepository.findById(id);
    }

    @Override
    public Skill save(Skill skill) {

        if (skill.getLevelPercentage() < 0 || skill.getLevelPercentage() > 100){
            throw new IllegalArgumentException("El porcentaje es incorrecto debe estar entre 0 y 100");
        } else {

        }

        return skillRepository.save(skill);
    }

    @Override
    public void deleteById(Long id) {
        skillRepository.deleteById(id);
    }

    @Override
    public List<Skill> findSkillsByPersonalInfoId(Long personalInfoId) {
        return skillRepository.findByPersonalInfoId(personalInfoId);
    }
}
