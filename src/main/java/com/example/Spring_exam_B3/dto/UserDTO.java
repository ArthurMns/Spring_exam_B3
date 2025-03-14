package com.example.Spring_exam_B3.dto;

import com.example.Spring_exam_B3.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private String username;
    private String password;
    private boolean enabled;
    private Role role;
    private LocalDateTime tokenExpiration;
    private String claimToken;

}
