package uz.pdp.online.lesson_7_news_site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.online.lesson_7_news_site.entity.Comment;

public interface CommentRepos extends JpaRepository<Comment,Long> {
}
