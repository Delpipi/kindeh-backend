package net.alexandrepaulkouame.utils;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import net.alexandrepaulkouame.entities.Role;
import net.alexandrepaulkouame.repositories.RoleRepository;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StringListToRoleSetDeserializer extends JsonDeserializer<Set<Role>> {
    private final RoleRepository roleRepository;

    // Inject the repository (or use other means to retrieve roles)
    public StringListToRoleSetDeserializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Set<Role> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        List<String> roleNames = p.readValuesAs(List.class).next();
        return new HashSet<>(roleRepository.findByNameIn(roleNames));
    }
}
