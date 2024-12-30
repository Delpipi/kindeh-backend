package net.alexandrepaulkouame.dto;

import java.util.List;

public record CustomerDTO (
    String firstName,
    String lastName,
    String phoneNumber,
    String password,
    Boolean isActive,
    String email,
    List<String> roles,
    List<String> documents
){}
