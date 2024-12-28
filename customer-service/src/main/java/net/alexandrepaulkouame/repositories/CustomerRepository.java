package net.alexandrepaulkouame.repositories;

import net.alexandrepaulkouame.entities.Customer;
import net.alexandrepaulkouame.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    boolean existsByPhoneNumber(String phoneNumber);
    public Optional<Customer> findByPhoneNumberAndPassword(String phoneNumber, String password);
    public Optional<Customer> findByEmail(String email);
    public Optional<Customer> findByResetToken(String resetToken);
    public Optional<Customer> findByPhoneNumber(String phoneNumber);
    public Optional<Customer> findByPassword(String password);
    public List<Customer> findAllByRoles(Set<Role> roles);
}
