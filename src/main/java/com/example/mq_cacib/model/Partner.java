package com.example.mq_cacib.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Entity
@Data
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String alias;

    @NotBlank
    private String type;

    @Enumerated(EnumType.STRING)
    private Direction direction;

    private String application;

    @Enumerated(EnumType.STRING)
    private FlowType processedFlowType;

    @NotBlank
    private String description;

    public enum Direction { INBOUND, OUTBOUND }
    public enum FlowType { MESSAGE, ALERTING, NOTIFICATION }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank String getAlias() {
        return alias;
    }

    public void setAlias(@NotBlank String alias) {
        this.alias = alias;
    }

    public @NotBlank String getType() {
        return type;
    }

    public void setType(@NotBlank String type) {
        this.type = type;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public FlowType getProcessedFlowType() {
        return processedFlowType;
    }

    public void setProcessedFlowType(FlowType processedFlowType) {
        this.processedFlowType = processedFlowType;
    }

    public @NotBlank String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank String description) {
        this.description = description;
    }
}
