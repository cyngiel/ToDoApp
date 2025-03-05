package com.ps.todoapp.repository;

import com.ps.todoapp.entity.user.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends ListCrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
