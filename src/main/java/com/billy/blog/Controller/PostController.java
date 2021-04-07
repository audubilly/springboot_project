package com.billy.blog.Controller;


import com.billy.blog.Dtos.ApiResponse;
import com.billy.blog.Dtos.PostDTO;
import com.billy.blog.Exceptions.PostException;
import com.billy.blog.models.Post;
import com.billy.blog.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3000)
@RestController
@Slf4j
@RequestMapping("api/v1/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/new")
    public ResponseEntity<?> createPost(@Valid @RequestBody PostDTO postDTO) {
        log.info("Post DTO : {}", postDTO);
        postService.createPost(postDTO);
        return new ResponseEntity<>(new ApiResponse(true, "Post created successfully"), HttpStatus.CREATED);

    }

    @DeleteMapping("{Id}")
    public ResponseEntity<?> deletePostById(@Valid @PathVariable String Id) {
        log.info("Id : {}", Id);
        try {
            postService.deletePostById(Id);
            return new ResponseEntity<>(new ApiResponse(true, "post deleted successfully"),
                    HttpStatus.NO_CONTENT);

        } catch (PostException productException) {
            return new ResponseEntity<>(new ApiResponse(false, productException.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{title}")
    public ResponseEntity<?> deletePostByTitle(@PathVariable String title) {
        log.info("Title : {}", title);
        try {
            postService.deletePostByTitle(title);
            return new ResponseEntity<>(new ApiResponse(true, "post deleted successfully"),
                    HttpStatus.NO_CONTENT);

        } catch (PostException productException) {
            return new ResponseEntity<>(new ApiResponse(false, productException.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("{authorId}")
    public ResponseEntity<?> deletePostByAuthor(@Valid @PathVariable String authorId) {
        log.info("authorId : {}", authorId);
        try {
            postService.deletePostById(authorId);
            return new ResponseEntity<>(new ApiResponse(true, "post deleted successfully"),
                    HttpStatus.NO_CONTENT);

        } catch (PostException productException) {
            return new ResponseEntity<>(new ApiResponse(false, productException.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
}