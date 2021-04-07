package com.billy.blog.service;

import com.billy.blog.Dtos.PostDTO;
import com.billy.blog.Exceptions.PostException;
import com.billy.blog.models.Post;
import com.billy.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    PostRepository postRepository;


    @Override
    public void createPost(PostDTO postDTO) {
        Post post = PostDTO.unpackDTO(postDTO);
        createPost(post);

    }

    private void createPost(Post post){
        postRepository.save(post);
    }

    @Override
    public List<PostDTO> getAllPosts() {
        List<PostDTO> postDTOS = new ArrayList<>();
        for (Post post: getAllPostsFromDb()) {
            postDTOS.add(PostDTO.packDTO(post));
        }
        return postDTOS;
    }


    private List<Post> getAllPostsFromDb(){
        return postRepository.findAll();
    }

    @Override
    public PostDTO getPostById(String id) throws PostException {
        return PostDTO.packDTO(findPostById(id));
        
    }

    private Post findPostById(String id) throws PostException {
        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()) {
            return postOptional.get();
        } else {
            throw new PostException("No post found with that Id");
        }
    }

    @Override
    public void deletePostById(String id) throws PostException {
        Post postToDelete = findPostById(id);
        deletePost(postToDelete);
    }

    private void deletePost(Post post){
        postRepository.delete(post);
    }

    @Override
    public void deletePostByTitle(String title) throws PostException {
     Post postToDelete = findPostById(title);
        deletePost(postToDelete);
    }

    @Override
    public void deleteAllPostByAuthor(String authorId) throws PostException {
        Post postToDelete = findPostById(authorId);
        deletePost(postToDelete);

    }

    @Override
    public PostDTO updatePost(String id, PostDTO updatedPostDetails) throws PostException {
        Post postToUpdate = findPostById(id);
        if(!postToUpdate.getTitle().equals(updatedPostDetails.getTitle()))
            postToUpdate.setTitle(updatedPostDetails.getTitle());

        if(!postToUpdate.getBody().equals(updatedPostDetails.getBody()))
            postToUpdate.setBody(updatedPostDetails.getBody());

        if(!postToUpdate.getPublishDate().equals(updatedPostDetails.getPublishDate()))
            postToUpdate.setPublishDate(updatedPostDetails.getPublishDate());

        if(!postToUpdate.getAuthorId().equals(updatedPostDetails.getAuthorId()))
            postToUpdate.setAuthorId(updatedPostDetails.getAuthorId());

        Post updatedPost = savePost(postToUpdate);
        return PostDTO.packDTO(updatedPost);
    }

    private Post savePost(Post post){
       return postRepository.save(post);
    }
}
