package com.portfolio.my_portfolio_backend.repository;

import com.portfolio.my_portfolio_backend.model.Education;

import java.util.List;
import java.util.Optional;

public interface IEducationRepository {
    List<Education> findAll();
    Optional<Education> findById(Long id);
    Education save (Education education);
    void deleteById(Long id);
    List<Education> findEducationByPersonalInfoId(Long personalInfoId);

}
