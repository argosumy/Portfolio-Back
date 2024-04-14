package com.portfolio.features.user_features.modification_user.conrollers;

import com.portfolio.features.user_features.modification_user.services.ModificationUserService;
import com.portfolio.models.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ModificationUserController {
    private final ModificationUserService modificationUserService;

    public ModificationUserController(ModificationUserService modificationUserService) {
        this.modificationUserService = modificationUserService;
    }

    @PreAuthorize("@customAuthorizationServiceByUserId.isAccountOwner(authentication, #id) " +
            "OR hasAnyAuthority('DIRECTOR')")
    @PutMapping("/users/{id}")
    public User updateUser(@RequestBody User user, @PathVariable("id") long id) {
        user.setId(id);
        return modificationUserService.updateUser(user);
    }

    @PreAuthorize("hasAnyAuthority('MODIFICATION_ADMIN','DIRECTOR') AND #id != 1")
    @DeleteMapping("/users/{id}")
    public int deleteUser(@PathVariable("id") long id) {
        return modificationUserService.deleteUserById(id);
    }

    @PreAuthorize("@customAuthorizationServiceByUserId.isAccountOwner(authentication, #id) " +
            "OR hasAnyAuthority('DIRECTOR', 'MODIFICATION_ADMIN')")
    @GetMapping("/users/{id}")
    public User findUserById(@PathVariable("id") long id) {
        return modificationUserService.getUserById(id);
    }

    @PreAuthorize("hasAnyAuthority('DIRECTOR', 'MODIFICATION_ADMIN')")
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return modificationUserService.getAllUsers();
    }
}
