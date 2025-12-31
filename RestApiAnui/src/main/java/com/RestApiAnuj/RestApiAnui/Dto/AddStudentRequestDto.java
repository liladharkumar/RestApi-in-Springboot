package com.RestApiAnuj.RestApiAnui.Dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class AddStudentRequestDto {
        @NotBlank(message = "name is Required")
        @Size(min = 3,max = 30,message = "name should be of length 3 to 30 characters")
        private String name;
        @Email
        @NotBlank(message = "Email is Required")
        private String email;
}
