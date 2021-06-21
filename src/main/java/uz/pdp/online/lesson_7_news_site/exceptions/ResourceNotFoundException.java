package uz.pdp.online.lesson_7_news_site.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@AllArgsConstructor
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    private final String resourceName; // qaysi tabledan topolmadik

    private final String resourceField; // qaysi fieldi bo'yicha topolmadik

    //har qanaqa toifadagi
    private final Object object; // nima berib topolmading

}
