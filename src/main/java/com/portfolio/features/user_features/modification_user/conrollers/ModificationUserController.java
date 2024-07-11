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
            "OR hasAnyAuthority('HIGH_PRIORITY')")
    @PutMapping("/users/{id}")
    public User updateUser(@RequestBody User user, @PathVariable("id") long id) {
        user.setId(id);
        return modificationUserService.updateUser(user);
    }

    @PreAuthorize("hasAnyAuthority('HIGH_PRIORITY') AND #id != 1")
    @DeleteMapping("/users/{id}")
    public int deleteUser(@PathVariable("id") long id) {
        return modificationUserService.deleteUserById(id);
    }

    @PreAuthorize("@customAuthorizationServiceByUserId.isAccountOwner(authentication, #id) " +
            "OR hasAnyAuthority('MODIFICATION_CV')")
    @GetMapping("/users/{id}")
    public User findUserById(@PathVariable("id") long id) {
        return modificationUserService.getUserById(id);
    }

    @PreAuthorize("hasAnyAuthority('MODIFICATION_CV')")
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return modificationUserService.getAllUsers();
    }

    @PreAuthorize("@customAuthorizationServiceByUserId.isAccountOwner(authentication, #id)")
    @DeleteMapping("/users/{id}/phone")
    public long deleteUsersPhone(@PathVariable("id") long id) {
        return modificationUserService.deletePhoneByUserId(id);
    }
}
