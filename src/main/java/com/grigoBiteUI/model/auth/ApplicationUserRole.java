package com.grigoBiteUI.model.auth;

import java.util.Set;

import static com.grigoBiteUI.model.auth.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    ADMIN(Set.of(CAN_CRUD_CANTEEN, CAN_CRUD_TENANT, CAN_CRUD_MENU)),
    PENJUAL(Set.of(CAN_CRUD_MENU)),
    PEMBELI(Set.of());

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
}
