package myproject.spring.boot.repository;

import myproject.spring.boot.domain.entity.User;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, UUID> {

    Optional<User> findByFederatedUserId(String federatedUserId);

    boolean existsByFederatedUserId(String federatedUserId);


    boolean existsByEmail(String email);


}
