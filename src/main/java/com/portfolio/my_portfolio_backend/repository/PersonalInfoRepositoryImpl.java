package com.portfolio.my_portfolio_backend.repository;

import com.portfolio.my_portfolio_backend.model.PersonalInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PersonalInfoRepositoryImpl implements IPersonalInfoRepository{

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<PersonalInfo> personalInfoRowMapper = (result, numRow) -> {
        PersonalInfo info = new PersonalInfo();
        info.setId(result.getLong("id"));
        info.setFirstName(result.getString("first_name"));
        info.setLastName(result.getString("last_name"));
        info.setTitle(result.getString("title"));
        info.setProfileDescription(result.getString("profile_description"));
        info.setProfileImageURL(result.getString("profile_image_url"));
        info.setYearsOfExperience(result.getObject("years_of_experience", Integer.class)); // Usar getObject para los objetos nulos
        info.setEmail(result.getString("email"));
        info.setPhone(result.getString("phone"));
        info.setLinkedinURL(result.getString("linkedin_url"));
        info.setGithubURL(result.getString("github_url"));
        return info;
    };

    @Override
    public PersonalInfo save(PersonalInfo personalInfo) {
        return null;
    }

    @Override
    public Optional<PersonalInfo> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<PersonalInfo> findAll() {
        String sql = "SELECT * FROM personal_info";
        return jdbcTemplate.query(sql, personalInfoRowMapper);
    }

    @Override
    public void deleteById(Long id) {

    }
}
