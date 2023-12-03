package com.grigoBiteUI.model.auth;

public enum ApplicationUserPermission {

    CAN_CRUD_CANTEEN("canteen:crud"),
    CAN_CRUD_TENANT("tenant:crud"),
    CAN_CRUD_MENU("menu:crud");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }


}
