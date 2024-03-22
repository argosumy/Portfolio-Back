package com.portfolio.features.business_card_features.repositories;

import com.portfolio.features.user_features.modification_user.repositories.mapers.UserRowMapper;
import com.portfolio.models.BusinessCardUser;
import com.portfolio.models.User;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class BusinessCardUsersRowCallBackHandler implements RowCallbackHandler {
    private final UserRowMapper rowMapper;
    private final Map<Long, BusinessCardUser> cardUserMap;

    public BusinessCardUsersRowCallBackHandler() {
        this.rowMapper = new UserRowMapper();
        this.cardUserMap = new HashMap<>();
    }

    @Override
    public void processRow(ResultSet rs) throws SQLException {
            User user = rowMapper.mapRow(rs, 0);
            String arie = rs.getString("name");
            long id = user.getId();
            if (cardUserMap.containsKey(id)) {
                BusinessCardUser cardUser = cardUserMap.get(id);
                List<String> practiceAreas = cardUser.getPracticeAreas();
                practiceAreas.add(arie);
            } else {
                List<String> practiceAreas = new ArrayList<>();
                String title = rs.getString("title");
                boolean isHidden = rs.getBoolean("cv_hidden");
                if(arie != null) {
                    practiceAreas.add(arie);
                }
                BusinessCardUser card = new BusinessCardUser(user, title, practiceAreas);
                card.setHidden(isHidden);
                cardUserMap.put(id, card);
            }
    }

    protected Map<Long, BusinessCardUser> getResult() {
        return cardUserMap;
    }
}
