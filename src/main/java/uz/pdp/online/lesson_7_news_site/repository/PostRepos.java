package uz.pdp.online.lesson_7_news_site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.lesson_7_news_site.entity.Post;

public interface PostRepos extends JpaRepository<Post, Long> {

    boolean existsByTitleAndTextAndUrl(String title, String text, String url);

    boolean existsByTitleAndTextAndUrlAndIdNot(String title, String text, String url, Long id);

}
