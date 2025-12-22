// UserController.java
package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Tag(name = "用户管理", description = "用户相关接口")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "用户注册", description = "新用户注册接口")
    @PostMapping("/register")
    public Result<String> register(@RequestBody User user) {
        userService.register(user);
        return Result.success("注册成功");
    }

    @Operation(summary = "用户登录", description = "用户登录接口,返回JWT Token")
    @PostMapping("/login")
    public Result<Map<String, String>> login(@RequestBody User user) {
        String token = userService.login(user.getUsername(), user.getPassword());
        Map<String, String> data = new HashMap<>();
        data.put("token", token);
        return Result.success("登录成功", data);
    }

    @Operation(
            summary = "获取用户信息",
            description = "获取当前登录用户信息,需要JWT Token",
            security = @SecurityRequirement(name = "Bearer")
    )
    @GetMapping("/info")
    public Result<User> getUserInfo(@RequestParam String username) {
        User user = userService.getUserInfo(username);
        return Result.success(user);
    }
}