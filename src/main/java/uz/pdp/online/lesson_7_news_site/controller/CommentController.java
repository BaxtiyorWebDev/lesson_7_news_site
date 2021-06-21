package uz.pdp.online.lesson_7_news_site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.online.lesson_7_news_site.payload.ApiResponse;
import uz.pdp.online.lesson_7_news_site.payload.CommentDto;
import uz.pdp.online.lesson_7_news_site.service.CommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    /*
    ADD_COMMENT,
    EDIT_COMMENT, // USERNI COMMENTINI O'ZIDAN BOSHQA HECH KIM O'ZGARTIRISH HUQUQIGA EGA EMAS
    DELETE_COMMNET, // TIZIMDAGI ODAM UCHUN
    DELETE_MY_COMMENT, // USER UCHUN
    * */

    @Autowired
    private CommentService commentService;


    @PreAuthorize(value = "hasAnyAuthority('ADD_COMMENT')")
    @PostMapping
    public HttpEntity<?> addComment(@RequestBody CommentDto commentDto) {
        ApiResponse apiResponse = commentService.addComment(commentDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyAuthority('EDIT_COMMENT')")
    @PutMapping("/{id}")
    public HttpEntity<?> editComment(@PathVariable Long id, @RequestBody CommentDto commentDto) {
        ApiResponse apiResponse = commentService.editComment(id, commentDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }


    @PreAuthorize(value = "hasAnyAuthority('DELETE_COMMNET')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteComment(@PathVariable Long id) {// TIZIMDAGI ODAM UCHUN
        ApiResponse apiResponse = commentService.deleteComment(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyAuthority('DELETE_MY_COMMENT')")
    @DeleteMapping("/deleteMyComment/{id}")
    public HttpEntity<?> deleteMyComment(@PathVariable Long id) {// ODDIY USER UCHUN
        ApiResponse apiResponse = commentService.deleteComment(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

}
