package uz.pdp.online.lesson_7_news_site.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.online.lesson_7_news_site.entity.Comment;
import uz.pdp.online.lesson_7_news_site.entity.Post;
import uz.pdp.online.lesson_7_news_site.payload.ApiResponse;
import uz.pdp.online.lesson_7_news_site.payload.CommentDto;
import uz.pdp.online.lesson_7_news_site.repository.CommentRepos;
import uz.pdp.online.lesson_7_news_site.repository.PostRepos;

import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    CommentRepos commentRepos;
    @Autowired
    PostRepos postRepos;

    public ApiResponse addComment(CommentDto commentDto) {
        Optional<Post> optionalPost = postRepos.findById(commentDto.getPostId());
        if (!optionalPost.isPresent())
            return new ApiResponse("komment yozish uchun maqola topilmadi", false);

        Comment comment = new Comment(
                commentDto.getText(),
                optionalPost.get()
        );
        commentRepos.save(comment);
        return new ApiResponse("Komment saqlandi", true);
    }

    public ApiResponse editComment(Long id, CommentDto commentDto) {
        Optional<Comment> optionalComment = commentRepos.findById(id);
        Optional<Post> optionalPost = postRepos.findById(commentDto.getPostId());
        if (!optionalComment.isPresent())
            return new ApiResponse("Komment topilmadi", false);
        if (!optionalPost.isPresent())
            return new ApiResponse("Komment uchun maqola topilmadi", false);

        Comment editingComment = optionalComment.get();
        editingComment.setText(commentDto.getText());
        editingComment.setPost(optionalPost.get());
        commentRepos.save(editingComment);
        return new ApiResponse("Kommentariya tahrirlandi", true);
    }

    //TIZIM BOSHQARUVCHILARI UCHUN
    public ApiResponse deleteComment(Long id) {
        try {
            commentRepos.deleteById(id);
            return new ApiResponse("Komment o'chirildi", true);
        } catch (Exception e) {
            return new ApiResponse("Komment topilmadi", false);
        }
    }

    //ODDIY USER UCHUN
    public ApiResponse deleteMyComment(Long id) {
        try {
            commentRepos.deleteById(id);
            return new ApiResponse("Komment o'chirildi", true);
        } catch (Exception e) {
            return new ApiResponse("Komment topilmadi", false);
        }
    }

}
