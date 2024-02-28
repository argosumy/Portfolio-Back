package com.portfolio.features.user_features.modification_user.conrollers;

import com.portfolio.features.user_features.modification_user.services.ModificationUserService;
import com.portfolio.models.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ModificationUserController {
    private final ModificationUserService modificationUserService;

    public ModificationUserController(ModificationUserService modificationUserService) {
        this.modificationUserService = modificationUserService;
    }

    @PutMapping("/users/{id}")
    public User updateUser(@RequestBody User user, @PathVariable("id") long id) {
        user.setId(id);
        return modificationUserService.updateUser(user);
    }

    @DeleteMapping("/users/{id}")
    public int deleteUser(@PathVariable("id") long id) {
        return modificationUserService.deleteUserById(id);
    }

    @GetMapping("/users/{id}")
    public User findUserById(@PathVariable("id") long id) {
        return modificationUserService.getUserById(id);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return modificationUserService.getAllUsers();
    }
}
