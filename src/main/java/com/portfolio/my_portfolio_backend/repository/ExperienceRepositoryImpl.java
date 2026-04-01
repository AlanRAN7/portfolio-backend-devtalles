package com.portfolio.my_portfolio_backend.repository;

import com.portfolio.my_portfolio_backend.model.Experience;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ExperienceRepositoryImpl implements IExperienceRepository{

    private final RowMapper<Experience> experienceRowMapper = (result, numRow) -> {
        Experience experience = new Experience();
        experience.setId(result.getLong("id"));
        experience.setJobTitle(result.getString("job_title"));
        experience.setCompanyName(result.getString("company_name"));
        experience.setStartDate(result.getObject("start_date", LocalDate.class));
        experience.setEndDate(result.getObject("end_date", LocalDate.class));
        experience.setDescription(result.getString("description"));
        experience.setPersonalInfoId(result.getLong("personal_info_id"));
        return experience;
    };

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Experience> findAll() {
        String sql = "SELECT * FROM experiences";
        return jdbcTemplate.query(sql, experienceRowMapper);
    }

    @Override
    public Optional<Experience> findById(Long id) {

        String sql = "SELECT * FROM experiences WHERE id = ?";
        try {
            return Optional.of(jdbcTemplate.queryForObject(sql, experienceRowMapper, id));
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public Experience save(Experience experience) {
     if (experience.getId() == null){
         String sql = "INSERT INTO experiences(job_title, company_name, start_date, end_date, description, personal_info_id)" +
                 "VALUES(?. ?, ?, ?, ?, ?)";
         KeyHolder keyHolder = new GeneratedKeyHolder();
         jdbcTemplate.update(con -> {
             PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
             ps.setString(1, experience.getJobTitle());
             ps.setString(2, experience.getCompanyName());
             ps.setObject(3, experience.getStartDate());
             ps.setObject(4, experience.getEndDate());
             ps.setString(5,experience.getDescription());
             ps.setLong(6, experience.getPersonalInfoId());
             return ps;
         }, keyHolder);

         experience.setId(Objects.requireNonNull(keyHolder.getKey().longValue()));
     } else {
         String sql = "UPDATE experiences SET job_title = ?, company_name = ?, start_date = ?, end_date = ?, description = ?, personal_info_id = ? WHERE id = ?";
         jdbcTemplate.update(sql,
                 experience.getJobTitle(),
                 experience.getCompanyName(),
                 experience.getStartDate(),
                 experience.getEndDate(),
                 experience.getDescription(),
                 experience.getPersonalInfoId(),
                 experience.getId());
     }
        return experience;
    }

    @Override
    public void deleteById(Long id) {
         String sql = "DELETE FROM experiences WHERE id = ?";
         jdbcTemplate.update(sql, id);

    }

    @Override
    public List<Experience> findExperienceByPersonalInfoId(Long personalInfoId) {
        String sql = "SELECT id, job_title, company_name, start_date, end_date, description, personal_info_id FROM experiences WHERE personal_info_id = ?";
        return jdbcTemplate.query(sql, experienceRowMapper, personalInfoId);
    }
}
