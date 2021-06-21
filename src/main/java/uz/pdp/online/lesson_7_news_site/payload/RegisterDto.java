package uz.pdp.online.lesson_7_news_site.payload;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RegisterDto {

    @NotNull(message = "FullName bo'sh bo'lmasin")
    private String fullName;

    @NotNull(message = "Username bo'sh bo'lmasin")
    private String username;

    @NotNull(message = "Parol bo'sh bo'lmasin")
    private String password;

    @NotNull(message = "Takroriy parol bo'sh bo'lmasin")
    private String prePassword;

}
