package com.grigoBiteUI.model.auth;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum ApplicationUserPermission {

    CAN_CRUD_CANTEEN("canteen:crud"),
    CAN_CRUD_TENANT("tenant:crud"),
    CAN_RU_TENANT("tenant:ru"),
    CAN_CRUD_MENU("menu:crud"),
    CAN_UD_PESANAN("pesanan:ud");


    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    private final String permission;

    public String getPermission() {
        return permission;
    }




}
