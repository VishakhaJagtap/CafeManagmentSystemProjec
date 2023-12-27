package com.cafe.dto;

import com.cafe.enums.UserRole;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserDto {

    private Long id;

    private String email;

    private String name;

    private UserRole userRole;

    private MultipartFile img;

    private byte[] returnedImg;
}
