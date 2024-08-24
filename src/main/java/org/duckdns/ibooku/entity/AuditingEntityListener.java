package org.duckdns.ibooku.entity;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class AuditingEntityListener {
    @PrePersist
    public void prePersist(Object object) {
        if (object instanceof BaseEntity) {
            BaseEntity entity = (BaseEntity) object;
            entity.setCreatedAt(ZonedDateTime.now(ZoneId.of("Asia/Seoul")));
            entity.setUpdatedAt(ZonedDateTime.now(ZoneId.of("Asia/Seoul")));
        }
    }

    @PreUpdate
    public void preUpdate(Object object) {
        if (object instanceof BaseEntity) {
            BaseEntity entity = (BaseEntity) object;
            entity.setUpdatedAt(ZonedDateTime.now(ZoneId.of("Asia/Seoul")));
        }
    }
}