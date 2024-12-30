package net.alexandrepaulkouame.web;

import net.alexandrepaulkouame.entities.Role;
import net.alexandrepaulkouame.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class RoleGraphqlController {

    @Autowired
    private RoleService roleService;

    @QueryMapping
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }
}
