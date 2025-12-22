// UserService.java
package com.example.demo.service;

import com.example.demo.common.ResultCode;
import com.example.demo.entity.User;
import com.example.demo.exception.BusinessException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtUtil jwtUtil;

    // 注册
    public void register(User user) {
        // 检查用户是否已存在
        if (userMapper.findByUsername(user.getUsername()) != null) {
            throw new BusinessException(ResultCode.USER_ALREADY_EXIST);
        }
        // 密码加密
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));

        int result = userMapper.insert(user);
        if (result <= 0) {
            throw new BusinessException("注册失败");
        }
    }

    // 登录
    public String login(String username, String password) {
        User user = userMapper.findByUsername(username);
        if (user == null) {
            throw new BusinessException(ResultCode.USER_NOT_EXIST);
        }

        // 验证密码
        String encryptedPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!encryptedPassword.equals(user.getPassword())) {
            throw new BusinessException(ResultCode.USERNAME_OR_PASSWORD_ERROR);
        }

        // 生成Token
        return jwtUtil.generateToken(username);
    }

    // 获取用户信息
    public User getUserInfo(String username) {
        User user = userMapper.findByUsername(username);
        if (user == null) {
            throw new BusinessException(ResultCode.USER_NOT_EXIST);
        }
        // 清空密码字段,不返回给前端
        user.setPassword(null);
        return user;
    }
}