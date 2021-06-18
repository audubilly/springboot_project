package com.billy.blog.repository;

import com.billy.blog.Dtos.PostDTO;
import com.billy.blog.Exceptions.PostException;
import com.billy.blog.models.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends MongoRepository<Post,String> {
    Optional<Post> findPostByTitle (String title );
    List<Post> findPostsByAuthorId (String authorId);


}

