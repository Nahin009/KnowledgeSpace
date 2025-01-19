package org.example.user_service.repository;

import org.example.user_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<User, UUID> {
}
