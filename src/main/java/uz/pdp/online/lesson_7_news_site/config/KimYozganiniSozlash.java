package uz.pdp.online.lesson_7_news_site.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.UUID;

@Configuration // configuration yozishidan maqsad beanni ishlatish
@EnableJpaAuditing // jpani auditing qilishiga ruxsat berdik
public class KimYozganiniSozlash {

    @Bean
    public AuditorAware<Long> auditorAware() {
        return new KimYozganiniBilish();
    }
}
