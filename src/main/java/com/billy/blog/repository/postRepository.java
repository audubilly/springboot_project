package com.billy.blog.repository;

import com.billy.blog.models.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface postRepository extends MongoRepository<Post , String> {

}

