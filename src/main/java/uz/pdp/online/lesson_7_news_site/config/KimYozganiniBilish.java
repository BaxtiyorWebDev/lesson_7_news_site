package uz.pdp.online.lesson_7_news_site.config;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import uz.pdp.online.lesson_7_news_site.entity.User;

import java.util.Optional;

public class KimYozganiniBilish implements AuditorAware<User> {

    @Override
    public Optional<User> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null // sistemaga kimdir kirgan bo'lsin
                && authentication.isAuthenticated()// sistemaga kirib turgan bo'lsin
                && !authentication.getPrincipal().equals("anonymousUser")) {// shu user anonymous bo'lmasin
            User user = (User)authentication.getPrincipal();
            Optional<User> user1 = Optional.of(user);
            return user1;
        }
            return Optional.empty();
    }
}
