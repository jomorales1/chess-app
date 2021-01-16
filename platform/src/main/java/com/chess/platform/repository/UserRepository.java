package com.chess.platform.repository;

import com.chess.platform.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findByUsername(String username);
    @Transactional
    void deleteByUsername(String username);
}
