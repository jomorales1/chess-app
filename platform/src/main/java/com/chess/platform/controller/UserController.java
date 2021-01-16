package com.chess.platform.controller;

import com.chess.platform.model.User;
import com.chess.platform.pojo.UserPOJO;
import com.chess.platform.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    private static final String USER_NOT_FOUND = "User not found";

    private static final String USER_CREATED = "User created";

    private static final String INVALID_INFO_FORMAT = "The format of the information provided is wrong";

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    private PasswordEncoder passwordEncoder;

    public UserController(UserService userService) {
        this.userService = userService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @GetMapping(path = "/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        User user = this.userService.findByUsername(username);
        if (user == null) {
            LOGGER.error(USER_NOT_FOUND);
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<String> createUser(@RequestBody UserPOJO userPOJO) {
        if (!UserPOJO.isRequestInfoValid(userPOJO)) {
            LOGGER.error(INVALID_INFO_FORMAT);
            return ResponseEntity.badRequest().body(INVALID_INFO_FORMAT);
        }
        userPOJO.setPassword(this.passwordEncoder.encode(userPOJO.getPassword()));
        User user = UserPOJO.mapRequestEntity(userPOJO);
        this.userService.save(user);
        return ResponseEntity.ok(USER_CREATED);
    }
}
