package net.alexandrepaulkouame.services.impl;

import net.alexandrepaulkouame.entities.Customer;
import net.alexandrepaulkouame.entities.Role;
import net.alexandrepaulkouame.entities.RoleName;
import net.alexandrepaulkouame.repositories.RoleRepository;
import net.alexandrepaulkouame.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl  implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Set<Role> getAllRolesByNameIn(List<String> roleName) {
        return roleRepository.findByNameIn(roleName);
    }
}
