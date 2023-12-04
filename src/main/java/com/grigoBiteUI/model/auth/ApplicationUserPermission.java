package com.grigoBiteUI.model.auth;

public enum ApplicationUserPermission {

    CAN_CRUD_CANTEEN("canteen:crud"),
    CAN_CRUD_TENANT("tenant:crud"),
    CAN_RU_TENANT("tenant:ru"),
    CAN_CRUD_MENU("menu:crud"),
    CAN_UD_PESANAN("pesanan:ud");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }


}
