package com.mjv.mjvracingbackend.model.enums;

public enum Status {

    OPEN(0, "OPEN"),
    PROGRESS(1, "PROGRESS"),
    CLOSED(2, "CLOSED");

    private Integer code;
    private String description;

    Status(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static Status toEnum(Integer code) {
        if (code == null) {
            return null;
        }

        for (Status s : Status.values()) {
            if (code.equals(s.getCode())) {
                return s;
            }
        }
        throw new IllegalArgumentException("Invalid status");
    }
}
