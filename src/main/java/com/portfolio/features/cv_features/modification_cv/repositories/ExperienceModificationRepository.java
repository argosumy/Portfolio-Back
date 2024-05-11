package com.portfolio.features.cv_features.modification_cv.repositories;

import com.portfolio.models.cv_blocks.Experience;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

@Repository
public class ExperienceModificationRepository implements ModificationRepository<Experience> {
    private final NamedParameterJdbcTemplate parameterJdbcTemplate;

    public ExperienceModificationRepository(NamedParameterJdbcTemplate parameterJdbcTemplate) {
        this.parameterJdbcTemplate = parameterJdbcTemplate;
    }

    @Override
    public long add(long userId, Experience experience) {
        String sql = "INSERT INTO experience (user_id, title, start_job, finish_job, description) VALUES (?, ?, ?, ?, ?) RETURNING id;";
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        parameterJdbcTemplate.getJdbcTemplate().update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, userId);
            ps.setString(2, experience.getTitle());
            ps.setDate(3, Date.valueOf(experience.getStartJob()));
            ps.setDate(4, experience.getFinishJob() == null ? null : Date.valueOf(experience.getFinishJob()));
            ps.setString(5, experience.getDescription());
            return ps;
        }, keyHolder);
        return (Integer) keyHolder.getKey();
    }

    @Override
    public long deleteById(long id) {
        String sql = "DELETE FROM experience WHERE id = ?";
        parameterJdbcTemplate.getJdbcTemplate().update(sql, id);
        return id;
    }

    @Override
    public void updateByUserId(long userId, Experience element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Map<String, Object>> getTableRowsByUserId(long userId) {
        return null;
    }
}
