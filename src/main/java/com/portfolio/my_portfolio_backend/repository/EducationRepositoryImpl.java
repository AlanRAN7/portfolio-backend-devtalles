package com.portfolio.my_portfolio_backend.repository;

import com.portfolio.my_portfolio_backend.model.Education;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EducationRepositoryImpl implements IEducationRepository{

    private final RowMapper<Education> educationRowMapper = (result, numRow) -> {
        Education education = new Education();
        education.setId(result.getLong("id"));
        education.setDegree(result.getString("degree"));
        education.setDescription(result.getString("description"));
        education.setInstitution(result.getString("institution"));
        education.setStartDate(result.getDate("start_date").toLocalDate());
        education.setEndDate(result.getDate("end_date").toLocalDate());
        education.setPersonalInfoId(result.getLong("personal_info_id"));
        return education;
    };
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Education> findAll() {
        String sql = "SELECT id, degree, institution, start_date, end_date, description personal_info_id"
                + "FROM education";
        return jdbcTemplate.query(sql, educationRowMapper);
    }

    @Override
    public Optional<Education> findById(Long id) {
        String sql = "SELECT id, degree, institution, start_date, end_date, description, personal_info_id"
                + "FROM education WHERE id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, educationRowMapper, id));
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public Education save(Education education) {
        if (education.getId() == null){
            String sql = "INSERT INTO education(degree, institution, start_date, end_date, description, personal_info_id)" +
                    "VALUES(?, ?, ?, ?, ?, ?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
                ps.setString(1, education.getDegree());
                ps.setString(2, education.getInstitution());
                ps.setDate(3, Date.valueOf(education.getStartDate()));
                ps.setDate(4, Date.valueOf(education.getEndDate()));
                ps.setString(5, education.getDescription());
                ps.setLong(6, education.getPersonalInfoId());
                return ps;
            }, keyHolder);

            education.setId(Objects.requireNonNull(keyHolder.getKey().longValue()));
        } else {
            String sql = "UPDATE education SET degree = ?, institution = ?, start_date = ?, end_date = ?, description = ?, personal_info_id = ? WHERE id = ?";
            jdbcTemplate.update(sql, education.getDegree(), education.getInstitution(), education.getStartDate(), education.getEndDate(), education.getDescription(), education.getPersonalInfoId());
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM education WHERE id = ?";
        jdbcTemplate.update(sql, id);

    }

    @Override
    public List<Education> findEducationByPersonalInfoId(Long personalInfoId) {
        String sql = "SELECT id, degree, institucion, start_date, end_date, description, personal_info_id FROM education WHERE personal_info_id = ?";
        return jdbcTemplate.query(sql, educationRowMapper, personalInfoId);
    }
}
