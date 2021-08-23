package com.software.employeemanager.security;

import com.google.common.collect.Sets;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.software.employeemanager.security.ApplicationUserPermissions.*;

@Getter
public enum ApplicationUserRols {
    ADMIN(Sets.newHashSet(EMPLOYEE_READ, EMPLOYEE_WRITE)),
    EMPLOYEE(Sets.newHashSet(EMPLOYEE_READ));

    private final Set<ApplicationUserPermissions> permissions;

    ApplicationUserRols(Set<ApplicationUserPermissions> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> setPermissions = getPermissions().stream()
                .map(permissions -> new SimpleGrantedAuthority(permissions.getPermission()))
                .collect(Collectors.toSet());
        setPermissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return setPermissions;
    }
}
