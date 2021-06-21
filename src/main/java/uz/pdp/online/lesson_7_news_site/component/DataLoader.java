package uz.pdp.online.lesson_7_news_site.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.online.lesson_7_news_site.entity.Lavozim;
import uz.pdp.online.lesson_7_news_site.entity.User;
import uz.pdp.online.lesson_7_news_site.entity.enums.Huquq;
import uz.pdp.online.lesson_7_news_site.repository.LavozimRepos;
import uz.pdp.online.lesson_7_news_site.repository.UserRepos;
import uz.pdp.online.lesson_7_news_site.utils.AppConstants;

import java.util.Arrays;

import static uz.pdp.online.lesson_7_news_site.entity.enums.Huquq.*;


@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepos userRepos;
    @Autowired
    private LavozimRepos lavozimRepos;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${spring.sql.init.mode}")
    private String initialMode;

    @Override
    public void run(String... args) throws Exception {
        if (initialMode.equals("always")) {
            Huquq[] huquqs = Huquq.values();
            Lavozim admin = lavozimRepos.save(new Lavozim(
                    AppConstants.ADMIN, // adminga hamma huquqlarni berdik
                    Arrays.asList(huquqs),
                    "Sistema egasi"
            ));

            Lavozim user = lavozimRepos.save(new Lavozim(
                    AppConstants.USER,
                    Arrays.asList(ADD_COMMENT, EDIT_COMMENT, DELETE_MY_COMMENT),
                    "oddiy foydalanuvchi"
            ));

            userRepos.save(new User(
                    "Admin",
                    "admin",
                    passwordEncoder.encode("admin123"),
                    admin,
                    true
            ));

            userRepos.save(new User(
                    "User",
                    "user",
                    passwordEncoder.encode("user123"),
                    user,
                    true
            ));
        }
    }
}
