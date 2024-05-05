package com.portfolio.features.cv_features.modification_cv.repositories;

import com.portfolio.models.cv_blocks.SkillsType;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

@Repository("hardSkillsModificationRepository")
public class HardSkillsModificationRepository implements ModificationRepository<String> {
    private final NamedParameterJdbcTemplate parameterJdbcTemplate;

    public HardSkillsModificationRepository(NamedParameterJdbcTemplate parameterJdbcTemplate) {
        this.parameterJdbcTemplate = parameterJdbcTemplate;
    }

    @Override
    public long add(long userId, String element) {
        String sql = "INSERT INTO skills (user_id, name, type) VALUES (?, ?, ?) RETURNING id;";
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        parameterJdbcTemplate.getJdbcTemplate().update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, userId);
            ps.setString(2, element);
            ps.setString(3, SkillsType.HARD.name());
            return ps;
        }, keyHolder);
        return (Integer) keyHolder.getKey();
    }

    @Override
    public long deleteById(long id) {
        return 0;
    }

    @Override
    public void updateByUserId(long userId, String element) {
        final String sql = "UPDATE skills SET name = ? WHERE user_id = ? AND type = 'HARD';";
        parameterJdbcTemplate.getJdbcTemplate().update(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, element);
            ps.setLong(2, userId);
            return ps;
        });
    }

    @Override
    public List<Map<String, Object>> getTableRowsByUserId(long userId) {
        return null;
    }
}
