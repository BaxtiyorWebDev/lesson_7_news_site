package uz.pdp.online.lesson_7_news_site.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.online.lesson_7_news_site.entity.Post;
import uz.pdp.online.lesson_7_news_site.payload.ApiResponse;
import uz.pdp.online.lesson_7_news_site.payload.PostDto;
import uz.pdp.online.lesson_7_news_site.repository.PostRepos;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepos postRepos;

    public ApiResponse addPost(PostDto postDto) {
        boolean existsByTitleAndTextAndUrl = postRepos.existsByTitleAndTextAndUrl(postDto.getTitle(), postDto.getText(), postDto.getUrl());
        if (existsByTitleAndTextAndUrl)
            return new ApiResponse("Bunday maqolad mavjud",false);
        Post post = new Post(
                postDto.getTitle(),
                postDto.getText(),
                postDto.getUrl()
        );
        postRepos.save(post);
        return new ApiResponse("Maqola saqlandi",true);
    }


    public ApiResponse editPost(Long id, PostDto postDto) {
        Optional<Post> optionalPost = postRepos.findById(id);
        if (!optionalPost.isPresent())
            return new ApiResponse("Bunday maqola mavjud emas",false    );

        boolean existsByTitleAndTextAndUrlAndIdNot = postRepos.existsByTitleAndTextAndUrlAndIdNot(postDto.getTitle(), postDto.getText(), postDto.getUrl(), id);
        if (existsByTitleAndTextAndUrlAndIdNot)
            return new ApiResponse("Bunday maqola mavjud",false);

        Post editingPost = optionalPost.get();
        editingPost.setText(postDto.getText());
        editingPost.setTitle(postDto.getTitle());
        editingPost.setUrl(editingPost.getUrl());
        postRepos.save(editingPost);
        return new ApiResponse("Maqola tahrirlandi",true);
    }


    public Post getPostById(Long id) {
        Optional<Post> optionalPost = postRepos.findById(id);
        return optionalPost.orElse(null);
    }

    public Page<Post> viewPostsPage(int page) {
        Pageable pageable = PageRequest.of(page,20);
        return postRepos.findAll(pageable);
    }

    public ApiResponse deletePostById(Long id) {
        try {
            postRepos.deleteById(id);
            return new ApiResponse("Maqola o'chirildi",true);
        } catch (Exception e) {
            return new ApiResponse("Maqola topilmadi",false);
        }
    }
}
