package com.grigoBiteUI.model.auth;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.grigoBiteUI.model.auth.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    ADMIN(Set.of(CAN_CRUD_CANTEEN, CAN_CRUD_TENANT, CAN_CRUD_MENU)),
    PENJUAL(Set.of(CAN_CRUD_MENU, CAN_CRU_TENANT)),
    PEMBELI(Set.of(CAN_UD_PESANAN));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthority() {
        Set<SimpleGrantedAuthority> authorities = getPermissions().stream().map(permission -> new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_"+ this.name()));
        return authorities;
    }
}
