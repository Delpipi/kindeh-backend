package net.alexandrepaulkouame.utils;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import net.alexandrepaulkouame.entities.Role;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

public class RoleSetToStringListSerializer extends JsonSerializer<Set<Role>> {
    @Override
    public void serialize(Set<Role> roleSet, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        gen.writeObject(roleSet.stream().map(Role::getName).collect(Collectors.toList()));
    }
}
