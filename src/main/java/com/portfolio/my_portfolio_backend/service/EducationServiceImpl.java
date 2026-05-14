package com.portfolio.my_portfolio_backend.service;

import com.portfolio.my_portfolio_backend.exception.ValidationException;
import com.portfolio.my_portfolio_backend.model.Education;
import com.portfolio.my_portfolio_backend.repository.IEducationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;


import java.util.List;
import java.util.Optional;

@Service
public class EducationServiceImpl  implements IEducationService{

    private final IEducationRepository iEducationRepository;
    private final Validator validator;

    public EducationServiceImpl(IEducationRepository iEducationRepository, Validator validator) {
        this.iEducationRepository = iEducationRepository;
        this.validator = validator;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Education> findAll() {
        return iEducationRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Education> findById(Long id) {
        return iEducationRepository.findById(id);
    }

    @Override
    @Transactional
    public Education save(Education education) {
        BindingResult result = new BeanPropertyBindingResult(education, "education");
        validator.validate(education, result);
        if (result.hasErrors()){
            throw new ValidationException(result);
        }

        return iEducationRepository.save(education);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
    iEducationRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Education> findEducationByPersonalInfoId(Long personalInfoId) {
        return iEducationRepository.findEducationByPersonalInfoId(personalInfoId);
    }
}
