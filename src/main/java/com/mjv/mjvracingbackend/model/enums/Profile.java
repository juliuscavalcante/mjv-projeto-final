package com.mjv.mjvracingbackend.model.enums;

public enum Profile {

    ADMIN(0, "ROLE_ADMIN"),
    USER(1, "ROLE_USER");

    private Integer code;
    private String description;

    Profile(Integer code, String description) {
        this.code = code;
        this.description  = description;
    }

    public static Profile toEnum(Integer code) {
        if (code == null) {
            return null;
        }

        for (Profile p : Profile.values()) {
            if (code.equals(p.getCode())) {
                return p;
            }
        }
        throw new IllegalArgumentException("Invalid profile");
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
