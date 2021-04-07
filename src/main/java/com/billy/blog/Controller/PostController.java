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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/all")
    public ResponseEntity<?> getAllPosts() {
        try {
            List<PostDTO> allPosts = postService.getAllPosts();
            return new ResponseEntity<>(new ApiResponse(true, allPosts.toString()),HttpStatus.FOUND);
        }catch (PostException postException){
            return new ResponseEntity<>(new ApiResponse(false, postException.getMessage()),HttpStatus.BAD_REQUEST);


        }
    }
    @GetMapping ("{Id}")
    public ResponseEntity<?> getPostById( @Valid @PathVariable String Id) {
        log.info("Id : {}", Id);
        try {
            postService.getPostById(Id);
            return new ResponseEntity<>(new ApiResponse(true, "post gotten successfully"),
                    HttpStatus.FOUND);

        } catch (PostException postException) {
            return new ResponseEntity<>(new ApiResponse(false, postException.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{title}")
    public ResponseEntity<?> deletePostByTitle( @Valid @PathVariable String title) {
        log.info("Title : {}", title);
        try {
            postService.deletePostByTitle(title);
            return new ResponseEntity<>(new ApiResponse(true, "post deleted successfully"),
                    HttpStatus.NO_CONTENT);

        } catch (PostException postException) {
            return new ResponseEntity<>(new ApiResponse(false, postException.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("{Id}")
    public ResponseEntity<?> deletePostById( @Valid @PathVariable String Id) {
        log.info("Id : {}", Id);
        try {
            postService.deletePostById(Id);
            return new ResponseEntity<>(new ApiResponse(true, "post deleted successfully"),
                    HttpStatus.NO_CONTENT);

        } catch (PostException postException) {
            return new ResponseEntity<>(new ApiResponse(false, postException.getMessage()),
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

        } catch (PostException postException) {
            return new ResponseEntity<>(new ApiResponse(false, postException.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("{Id}")
            public ResponseEntity<?> updatePost( @PathVariable  @RequestBody String Id, PostDTO updatedPostDetails) {
        log.info("post DTO : {} \n Id : {}", updatedPostDetails, Id);
        try {
            PostDTO updatedPost = postService.updatePost(Id, updatedPostDetails);
            return new ResponseEntity<>(updatedPost, HttpStatus.OK);

        } catch (PostException postException) {
            return new ResponseEntity<>(new ApiResponse(false, postException.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }




}

