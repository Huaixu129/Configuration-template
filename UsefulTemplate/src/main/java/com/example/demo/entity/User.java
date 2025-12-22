// 实体类
package com.example.demo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.time.LocalDateTime;

@Schema(description = "用户实体")
@Data
public class User {

    @Schema(description = "用户ID", example = "1")
    private Long id;

    @Schema(description = "用户名", example = "testuser")
    private String username;

    @Schema(description = "密码", example = "123456")
    private String password;

    @Schema(description = "邮箱", example = "test@example.com")
    private String email;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}