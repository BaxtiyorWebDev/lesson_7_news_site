package uz.pdp.online.lesson_7_news_site.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CommentDto {

    @NotBlank
    private String text;

    @NotNull
    private Long postId;
}
