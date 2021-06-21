package uz.pdp.online.lesson_7_news_site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.lesson_7_news_site.entity.User;
import uz.pdp.online.lesson_7_news_site.payload.ApiResponse;
import uz.pdp.online.lesson_7_news_site.payload.RegisterDto;
import uz.pdp.online.lesson_7_news_site.payload.UserDto;
import uz.pdp.online.lesson_7_news_site.service.AuthService;
import uz.pdp.online.lesson_7_news_site.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize(value = "hasAnyAuthority('ADD_USER')")
    @PostMapping
    public HttpEntity<?> addUser(@Valid @RequestBody UserDto userDto) {
        ApiResponse apiResponse = userService.addUser(userDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyAuthority('EDIT_USER')")
    @PutMapping("/{id}")
    public HttpEntity<?> editUser(@PathVariable Long id, @Valid @RequestBody UserDto userDto) {
        ApiResponse apiResponse = userService.editUser(id, userDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyAuthority('VIEW_USERS')")
    @GetMapping
    public HttpEntity<?> getPageUsers(@RequestParam int page) {
        Page<User> pageUsers = userService.getPageUsers(page);
        return ResponseEntity.status(pageUsers!=null?200:409).body(pageUsers);
    }

    @PreAuthorize(value = "hasAnyAuthority('DELETE_LAVOZIM')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteUsersById(Long id) {
        ApiResponse apiResponse = userService.deleteUserById(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
