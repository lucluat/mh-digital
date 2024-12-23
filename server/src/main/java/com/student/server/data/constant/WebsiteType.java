package com.student.server.data.constant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum WebsiteType {
    FACEBOOK(0),
    ZALO(1),
    LINKEDIN(2),
    NORON(3);

    private final int value;

    WebsiteType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @JsonCreator
    public static WebsiteType fromValue(int value) {
        for (WebsiteType type : WebsiteType.values()) {
            if (type.value == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown WebsiteType value: " + value);
    }

    @JsonValue
    public int toValue() {
        return this.value;
    }
}
