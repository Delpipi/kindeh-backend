package net.alexandrepaulkouame.repositories;

import net.alexandrepaulkouame.entities.Customer;
import net.alexandrepaulkouame.entities.Role;
import net.alexandrepaulkouame.entities.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Set<Role> findByNameIn(List<String> roleNames);
    /*@Query("SELECT r.customers FROM Role r WHERE r.name = :roleType")
    Set<Customer> findAllCutomersByRoleName(RoleName roleName);
    @Query("SELECT r FROM Role r WHERE r.customers IS EMPTY")
    List<Role> findAllRolesWithoutCustomers();*/
}
