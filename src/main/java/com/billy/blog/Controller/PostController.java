package com.billy.blog.Controller;


import com.billy.blog.Dtos.PostDTO;
import com.billy.blog.models.Post;
import com.billy.blog.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3000)
@RestController
@Slf4j
@RequestMapping("api/v1/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/new")
      public ResponseEntity<?> createPost( @RequestBody PostDTO postDTO){
        log.info("Post DTO : {}", postDTO);
        postService.createPost(postDTO);
        return new ResponseEntity<>(new ApiResponse)

    }

}