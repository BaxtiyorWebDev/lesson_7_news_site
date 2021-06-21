package uz.pdp.online.lesson_7_news_site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.lesson_7_news_site.entity.Lavozim;

import java.util.Optional;

public interface LavozimRepos extends JpaRepository<Lavozim,Long> {
    Optional<Lavozim> findByName(String name);

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);
}
