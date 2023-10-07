package com.springSecurity.springSecurityV2.models;

import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Table(name = "roles")
public enum Role {
    ADMIN,
    USER
}
