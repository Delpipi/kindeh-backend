package net.alexandrepaulkouame.services;

import net.alexandrepaulkouame.entities.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> getAllRoles();
    Set<Role> getAllRolesByNameIn(List<String> roleName);
}
