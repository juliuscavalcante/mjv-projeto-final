package com.mjv.mjvracingbackend.model.enums;

public enum Priority {

    LOW(0, "LOW_PRIORITY"),
    MEDIUM(1, "MEDIUM_PRIORITY"),
    HIGH(2, "HIGH_PRIORITY"),;

    private Integer code;
    private String description;


    Priority(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static Priority toEnum(Integer code) {
        if (code == null) {
            return null;
        }

        for (Priority pr : Priority.values()) {
            if (code.equals(pr.getCode())) {
                return pr;
            }
        }
        throw new IllegalArgumentException("Invalid priority");
    }
}

