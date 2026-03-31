package com.portfolio.my_portfolio_backend.repository;

import com.portfolio.my_portfolio_backend.model.Experience;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ExperienceRepositoryImpl implements IExperienceRepository{

    private final RowMapper<Experience> experienceRowMapper

    @Override
    public List<Experience> findAll() {
        return List.of();
    }

    @Override
    public Optional<Experience> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Experience save(Experience experience) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Experience> findExperienceByPersonalInfoId(Long personalInfoId) {
        return List.of();
    }
}
