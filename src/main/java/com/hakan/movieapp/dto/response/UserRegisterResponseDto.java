package com.hakan.movieapp.dto.response;

import com.hakan.movieapp.entity.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserRegisterResponseDto {
    private Long id;
    private String email;
    private String name;
}
