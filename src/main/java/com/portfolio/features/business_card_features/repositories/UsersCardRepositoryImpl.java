package com.portfolio.features.business_card_features.repositories;

import com.portfolio.models.BusinessCardUser;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UsersCardRepositoryImpl implements UsersCardRepository {
    private final JdbcTemplate jdbcTemplate;

    public UsersCardRepositoryImpl(JdbcTemplate jdbc) {
        this.jdbcTemplate = jdbc;
    }

    @Override
    public BusinessCardUser getUserCardByUserId(long id) {
        final BusinessCardUsersRowCallBackHandler rowCallBackHandler = new BusinessCardUsersRowCallBackHandler();
        final String sql = "SELECT * FROM users LEFT JOIN hard_skills AS hs ON users.id = hs.user_id " +
                "LEFT JOIN titles AS titles ON users.id = titles.user_id WHERE users.id = ?;";
        jdbcTemplate.query(sql, ps -> ps.setLong(1, id), rowCallBackHandler);
        return rowCallBackHandler.getResult().get(id);
    }

    @Override
    public Collection<BusinessCardUser> getUsersCard() {
        final BusinessCardUsersRowCallBackHandler rowCallBackHandler = new BusinessCardUsersRowCallBackHandler();
        final String sql = "SELECT * FROM users LEFT JOIN hard_skills AS hs ON users.id = hs.user_id " +
                                               "LEFT JOIN titles ON users.id = titles.user_id;";
        jdbcTemplate.query(sql, rowCallBackHandler);
        return rowCallBackHandler.getResult().values();
    }

    @Override
    public Collection<BusinessCardUser> getUsersCardNotHidden() {
        final BusinessCardUsersRowCallBackHandler rowCallBackHandler = new BusinessCardUsersRowCallBackHandler();
        final String sql = "SELECT * FROM users LEFT JOIN skills AS hs ON users.id = hs.user_id " +
                "LEFT JOIN titles ON users.id = titles.user_id WHERE cv_hidden = false;";
        jdbcTemplate.query(sql, rowCallBackHandler);
        return rowCallBackHandler.getResult().values();
    }



}