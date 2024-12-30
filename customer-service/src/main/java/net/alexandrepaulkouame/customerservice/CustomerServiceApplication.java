package net.alexandrepaulkouame.customerservice;

import net.alexandrepaulkouame.entities.Customer;
import net.alexandrepaulkouame.entities.Role;
import net.alexandrepaulkouame.entities.RoleName;
import net.alexandrepaulkouame.repositories.CustomerRepository;
import net.alexandrepaulkouame.repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@SpringBootApplication
@ComponentScan(basePackages = "net.alexandrepaulkouame")
@EntityScan(basePackages = "net.alexandrepaulkouame.entities")
@EnableJpaRepositories(basePackages = "net.alexandrepaulkouame.repositories")
public class CustomerServiceApplication {

	/*@Autowired
	private BCryptPasswordEncoder passwordEncoder;*/

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(RoleRepository roleRepository, CustomerRepository customerRepository){
		return args -> {

			roleRepository.saveAll(List.of(
					Role.builder()
							.name(RoleName.SUPER_ADMIN)
							.createdAt(Instant.now())
							.build(),
					Role.builder()
							.name(RoleName.ADMIN)
							.createdAt(Instant.now())
							.build(),
					Role.builder()
							.name(RoleName.RENTER)
							.createdAt(Instant.now())
							.build(),
					Role.builder()
							.name(RoleName.OWNER)
							.createdAt(Instant.now())
							.build()
			));
			List<Role> roles = roleRepository.findAll();
			roles.forEach(System.out::println);

			customerRepository.saveAll(List.of(
					Customer.builder()
							.firstName("Kouame")
							.lastName("Alexandre Paul")
							.phoneNumber("+2250702595516")
							.password("12345")
							.roles(Set.of(roles.get(0), roles.get(3)))
							.build(),
					Customer.builder()
							.firstName("Brou")
							.lastName("Marina")
							.phoneNumber("+2250504888547")
							.password("12345")
							.roles(Set.of(roles.get(3)))
							.build()
			));
			List<Customer> customers = customerRepository.findAll();
			customers.forEach(System.out::println);
		};
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
