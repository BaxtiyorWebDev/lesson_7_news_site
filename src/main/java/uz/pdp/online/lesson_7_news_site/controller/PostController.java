package uz.pdp.online.lesson_7_news_site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.lesson_7_news_site.entity.Post;
import uz.pdp.online.lesson_7_news_site.payload.ApiResponse;
import uz.pdp.online.lesson_7_news_site.payload.PostDto;
import uz.pdp.online.lesson_7_news_site.service.PostService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PreAuthorize(value = "hasAnyAuthority('ADD_POST')")
    @PostMapping
    public HttpEntity<?> addPost(@Valid @RequestBody PostDto postDto) {
        ApiResponse apiResponse = postService.addPost(postDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyAuthority('EDIT_POST')")
    @PutMapping("/{id}")
    public HttpEntity<?> editPost(Long id, @Valid @RequestBody PostDto postDto) {
        ApiResponse apiResponse = postService.editPost(id, postDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }


    @GetMapping("/{id}")
    public HttpEntity<?> viewPost(@PathVariable Long id) {
        Post postById = postService.getPostById(id);
        return ResponseEntity.status(postById!=null?200:409).body(postById);
    }

    @GetMapping
    public HttpEntity<?> viewPostsPage(@RequestParam int page) {
        Page<Post> posts = postService.viewPostsPage(page);
        return ResponseEntity.status(posts!=null?200:409).body(posts);
    }


    @PreAuthorize(value = "hasAnyAuthority('DELETE_POST')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deletePostById(@PathVariable Long id) {
        ApiResponse apiResponse = postService.deletePostById(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
