package com.portfolio.features.cv_features.modification_cv.repositories;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

@Repository("titleModificationRepository")
public class TitleModificationRepository implements ModificationRepository<String> {
    private final NamedParameterJdbcTemplate parameterJdbcTemplate;

    public TitleModificationRepository(NamedParameterJdbcTemplate parameterJdbcTemplate) {
        this.parameterJdbcTemplate = parameterJdbcTemplate;
    }

    @Override
    public long add(long userId, String title) {
        String sql = "INSERT INTO titles (user_id, title) VALUES (?, ?) RETURNING id;";
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        parameterJdbcTemplate.getJdbcTemplate().update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, userId);
            ps.setString(2, title);
            return ps;
        }, keyHolder);
        return (Integer) keyHolder.getKey();
    }

    @Override
    public long deleteById(long id) {
        return 0;
    }

    @Override
    public void updateByUserId(long userId, String title) {
        final String sql = "UPDATE titles SET title = ? WHERE user_id = ?;";
        parameterJdbcTemplate.getJdbcTemplate().update(con -> {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, title);
            ps.setLong(2, userId);
            return ps;
        });
    }

    @Override
    public List<Map<String, Object>> getTableRowsByUserId(long userId) {
        final String sql = "SELECT * FROM titles WHERE user_id = ?;";
        return parameterJdbcTemplate.getJdbcTemplate().queryForList(sql, userId);
    }
}
