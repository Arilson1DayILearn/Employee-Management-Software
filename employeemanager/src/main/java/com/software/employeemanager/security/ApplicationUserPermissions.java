package com.software.employeemanager.security;

import lombok.Getter;

@Getter
public enum ApplicationUserPermissions {
    EMPLOYEE_READ("employee:read"),
    EMPLOYEE_WRITE("employee:write");

    private final String permission;

    ApplicationUserPermissions(String permission) {
        this.permission = permission;
    }
}

