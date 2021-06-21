package uz.pdp.online.lesson_7_news_site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.lesson_7_news_site.entity.User;

import java.util.Optional;

public interface UserRepos extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}
