package uz.pdp.online.lesson_7_news_site.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PostDto {

    @NotBlank
    private String title;

    @NotBlank
    private String text;

    @NotBlank
    private String url;
}
