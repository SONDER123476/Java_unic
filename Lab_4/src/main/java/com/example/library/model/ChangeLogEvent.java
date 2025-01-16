package com.example.library.model;

import org.springframework.context.ApplicationEvent;

public class ChangeLogEvent extends ApplicationEvent {
    private final String entityName;
    private final String changeType;
    private final String description;

    public ChangeLogEvent(Object source, String entityName, String changeType, String description) {
        super(source);
        this.entityName = entityName;
        this.changeType = changeType;
        this.description = description;
    }

    public String getEntityName() {
        return entityName;
    }

    public String getChangeType() {
        return changeType;
    }

    public String getDescription() {
        return description;
    }
}
