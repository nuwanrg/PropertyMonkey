package com.pm.common.persistence.model;


import java.util.stream.Stream;

import static java.util.stream.Stream.*;

public enum ERole {
    ROLE_USER,
    ROLE_MODERATOR,
    ROLE_ADMIN,
    ROLE_AGENT;

    public static Stream<ERole> stream() {
        return Stream.of(ERole.values());
    }
}