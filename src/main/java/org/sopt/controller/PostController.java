package org.sopt.controller;

import org.sopt.domain.Post;
import org.sopt.service.PostService;

import java.util.List;

public class PostController {
    private PostService postService = new PostService();

    public boolean createPost(final String title) {
        try {
            postService.createPost(title);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    public Post getPostById(int id) {
        return postService.getPostById(id);
    }

    public boolean updatePostTitle(int id, String title) {
        try {
            postService.editPostById(id, title);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deletePostById(int id) {
        return postService.deletePostById(id);
    }
}
