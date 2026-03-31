package com.portfolio.my_portfolio_backend.service;

import com.portfolio.my_portfolio_backend.model.Education;
import com.portfolio.my_portfolio_backend.repository.IEducationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EducationServiceImpl  implements IEducationService{

    private final IEducationRepository iEducationRepository;

    public EducationServiceImpl(IEducationRepository iEducationRepository) {
        this.iEducationRepository = iEducationRepository;
    }

    @Override
    public List<Education> findAll() {
        return iEducationRepository.findAll();
    }

    @Override
    public Optional<Education> findById(Long id) {
        return iEducationRepository.findById(id);
    }

    @Override
    public Education save(Education education) {
        // Validación 1: Asegurar que la fecha de inicio no sea nula, como exige la DB
        if (education.getStartDate() == null){
            throw new IllegalArgumentException("La fecha de inicio de la educación no puede estar vacía.");
        }

        if (education.getEndDate() != null && education.getStartDate().isAfter(education.getEndDate())){
            throw new IllegalArgumentException("La fecha de inicio de la educación no puede ser posterior a la fecha de fin");
        }

        return iEducationRepository.save(education);
    }

    @Override
    public void deleteById(Long id) {
    iEducationRepository.deleteById(id);
    }

    @Override
    public List<Education> findEducationByPersonalInfoId(Long personalInfoId) {
        return iEducationRepository.findEducationByPersonalInfoId(personalInfoId);
    }
}
