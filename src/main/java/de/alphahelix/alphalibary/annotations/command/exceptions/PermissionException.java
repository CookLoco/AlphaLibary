package de.alphahelix.alphalibary.annotations.command.exceptions;

public class PermissionException extends CommandException {

    private String permission;

    public PermissionException(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

}
