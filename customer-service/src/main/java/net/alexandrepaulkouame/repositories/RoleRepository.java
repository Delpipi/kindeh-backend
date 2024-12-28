package net.alexandrepaulkouame.repositories;

import net.alexandrepaulkouame.entities.Role;
import net.alexandrepaulkouame.entities.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByNameIn(List<String> roleNames);
    Optional<Role> findByName(RoleType roleType);
}
