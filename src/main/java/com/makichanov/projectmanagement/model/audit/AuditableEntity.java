package com.makichanov.projectmanagement.model.audit;

import java.sql.Timestamp;

/**
 * Interface should be implemented by all entities that needs to be audited.
 */
public interface AuditableEntity {

    /**
     * Returns create date of entity.
     *
     * @return timestamp of create date
     */
    Timestamp getCreateDate();

    /**
     * Sets create date of entity.
     *
     * @param t timestamp of creation
     */
    void setCreateDate(Timestamp t);

}
