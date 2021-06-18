package com.billy.blog.service;

import com.billy.blog.Dtos.PostDTO;
import com.billy.blog.Exceptions.PostException;
import com.billy.blog.models.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
     void createPost (PostDTO postDTO);
     List<PostDTO> getAllPosts() throws PostException;
     PostDTO getPostById(String id) throws PostException;
     void deletePostById(String id) throws PostException;
     void deletePostByTitle(String title) throws PostException;
     void deleteAllPostByAuthor(String authorId) throws PostException;
     PostDTO updatePost(String id, PostDTO postDTO) throws PostException;
     public PostDTO getPostByTitle(String title) throws PostException;



}
