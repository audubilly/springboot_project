package com.billy.blog.service;

import com.billy.blog.Dtos.PostDTO;
import com.billy.blog.Exceptions.PostException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
     void createPost (PostDTO postDTO);
     List<PostDTO> getAllPosts();
     PostDTO getPostById(String id) throws PostException;
     void deletePostById(String id) throws PostException;
     void deletePostByTitle(String title) throws PostException;
     void deleteAllPostByAuthor(String authorId) throws PostException;
     PostDTO updatePost(String id, PostDTO postDTO) throws PostException;



}
