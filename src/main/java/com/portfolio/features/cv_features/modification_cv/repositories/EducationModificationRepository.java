package com.portfolio.features.cv_features.modification_cv.repositories;

import com.portfolio.models.cv_blocks.Education;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.naming.OperationNotSupportedException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

@Repository
public class EducationModificationRepository implements ModificationRepository<Education>{
    private final NamedParameterJdbcTemplate parameterJdbcTemplate;

    public EducationModificationRepository(NamedParameterJdbcTemplate parameterJdbcTemplate) {
        this.parameterJdbcTemplate = parameterJdbcTemplate;
    }

    @Override
    public long add(long userId, Education education) {
        String sql = "INSERT INTO education (user_id, specialization, degree, name_institute, start_education,  finish_education, type, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING id;";
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        parameterJdbcTemplate.getJdbcTemplate().update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, userId);
            ps.setString(2, education.getSpecialization());
            ps.setString(3, education.getDegree());
            ps.setString(4, education.getNameInstitute());
            ps.setDate(5, education.getStartEducation() == null ? null : Date.valueOf(education.getStartEducation()));
            ps.setDate(6, education.getFinishEducation() == null ? null : Date.valueOf(education.getFinishEducation()));
            ps.setString(7, education.getType().name());
            ps.setString(8, education.getDescription());
            return ps;
        }, keyHolder);
        return (Integer) keyHolder.getKey();
    }

    @Override
    public long deleteById(long id) {
        String sql = "DELETE FROM education WHERE id = ?";
        parameterJdbcTemplate.getJdbcTemplate().update(sql, id);
        return id;
    }

    @Override
    public void updateByUserId(long userId, Education element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Map<String, Object>> getTableRowsByUserId(long userId) {
        return null;
    }
}
