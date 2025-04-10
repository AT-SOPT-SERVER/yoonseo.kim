package org.sopt.repository;

import org.sopt.domain.Post;

import java.util.ArrayList;
import java.util.List;

public class PostRepository {
    List<Post> postList = new ArrayList<>();

    public void save(Post post) {
        postList.add(post);
    }

    public List<Post> findAll() {
        return postList;
    }

    public Post findPostById(int id) {
        for (Post post : postList) {
            if (post.getId() == id) {
                return post;
            }
        }
        return null;
    }

    public boolean deletePostById(int id) {
        for (Post post : postList) {
            if (post.getId() == id) {
                postList.remove(post);
                return true;
            }
        }
        return false;
    }

    public List<Post> searchPostByKeyword(String keyword) {
        List<Post> searchResult = new ArrayList<>();
        if (keyword == null || keyword.trim().isEmpty()) {
            return searchResult;
        }
        for (Post post : postList) {
            if (post.getTitle() != null && post.getTitle().toLowerCase().contains(keyword)) {
                searchResult.add(post);
            }
        }
        return searchResult;
    }
}
