package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.repository.PostRepository;

import java.util.List;

public class PostService {
    private PostRepository postRepository = new PostRepository();
    private int postId = 1;

    public void createPost(String title) {
        validateTitle(title);
        checkDuplicateTitle(title);
        Post post = new Post(postId++, title);
        postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(int id) {
        return postRepository.findPostById(id);
    }

    public void editPostById(int id, String title) {
        Post post = getPostById(id);
        if (post == null) {
            throw new IllegalArgumentException("해당 ID의 게시글이 존재하지 않습니다.");
        }
        if (!validateTitle(title) || !checkDuplicateTitle(title)) return;
        getPostById(id).setTitle(title);
    }

    public boolean deletePostById(int id) {
        return postRepository.deletePostById(id);
    }

    private boolean validateTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("제목이 비어 있습니다.");
        }
        if (title.length() > 30) {
            throw new IllegalArgumentException("제목은 최대 30자까지 가능합니다.");
        }
        return true;
    }

    private boolean checkDuplicateTitle(String title) {
        for (Post post : postRepository.findAll()) {
            if (post.getTitle().equals(title)) {
                throw new IllegalArgumentException("이미 존재하는 게시글 제목입니다.");
            }
        }
        return true;
    }
}
