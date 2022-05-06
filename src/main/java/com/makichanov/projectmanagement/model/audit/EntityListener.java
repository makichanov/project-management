package com.makichanov.projectmanagement.model.audit;

import javax.persistence.PrePersist;
import java.sql.Timestamp;
import java.time.ZonedDateTime;

public class EntityListener {

    @PrePersist
    private void onPreCreate(AuditableEntity entity) {
        Timestamp currentTime = Timestamp.valueOf(ZonedDateTime.now().toLocalDateTime());
        entity.setCreateDate(currentTime);
    }

}
