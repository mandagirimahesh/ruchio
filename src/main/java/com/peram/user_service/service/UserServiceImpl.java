package com.peram.user_service.service;

import com.peram.user_service.dto.UserRequest;
import com.peram.user_service.dto.UserResponse;
import com.peram.user_service.entity.User;
import com.peram.user_service.exception.UserNotFoundException;
import com.peram.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    @Transactional
    @Override
    @CachePut(value = "users", key = "#result.id")  // cache new user after creation
    public UserResponse createUser(UserRequest request) {
        if (request.name().isEmpty() && request.email().isEmpty() && request.password().isEmpty()) {
            throw new RuntimeException("Required all fields");
        }
        log.info("Creating new user with email: {}", request.email());
        userRepository.findByEmail(request.email()).ifPresent(u -> {
            throw new IllegalArgumentException("Email already exists: " + request.email());
        });
        User user = User.builder()
                .name(request.name())
                .email(request.email())
                .password(request.password())
                .build();
        User saved = userRepository.save(user);
        log.info("new user added to database");
        return new UserResponse(saved.getId(), saved.getName(), saved.getEmail());
    }

    @Override
    @Cacheable(value = "users", key = "#id") // cache by user id
    public UserResponse getUserById(Long id) {
        log.debug("Fetching user with id: {} (DB call)", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + id));
        return new UserResponse(user.getId(), user.getName(), user.getEmail());
    }

    @Override
    @Cacheable(value = "allUsers") // cache full list
    public List<UserResponse> getAllUsers() {
        log.debug("Fetching all users (DB call)");
        return userRepository.findAll().stream()
                .map(u -> new UserResponse(u.getId(), u.getName(), u.getEmail()))
                .toList();
    }

    @Transactional
    @Override
    @Caching(
            put = { @CachePut(value = "users", key = "#id") },
            evict = { @CacheEvict(value = "allUsers", allEntries = true) }
    )
    public UserResponse updateUser(Long id, UserRequest request) {
        log.info("Updating user with id: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + id));

        if (request.name() != null && !request.name().isBlank()) {
            user.setName(request.name());
        }
        if (request.email() != null && !request.email().isBlank()) {
            user.setEmail(request.email());
        }
        if (request.password() != null && !request.password().isBlank()) {
            user.setPassword(request.password()); // TODO: hash password
        }

        User updated = userRepository.save(user);

        return new UserResponse(updated.getId(), updated.getName(), updated.getEmail());
    }

    @Transactional
    @Override
    @Caching(evict = {
            @CacheEvict(value = "users", key = "#id"),
            @CacheEvict(value = "allUsers", allEntries = true)
    })
    public void deleteUser(Long id) {
        log.warn("Deleting user with id: {}", id);
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found: " + id);
        }
        userRepository.deleteById(id);
        log.warn("Deleted user with id: {}", id);
    }
}
