package org.sopt.service;

import jakarta.transaction.Transactional;
import org.sopt.domain.Post;
import org.sopt.dto.response.PostResponse;
import org.sopt.repository.PostRepository;
import org.sopt.util.Validator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void createPost(String title) {
        Validator.validateTitle(title);
        if (postRepository.existsByTitle(title)) {
            throw new IllegalArgumentException("이미 존재하는 제목입니다.");
        }
        postRepository.save(new Post(title));
    }

    public List<PostResponse> getAllPosts() {
        return postRepository.findAll().stream()
                .map(post -> new PostResponse(post.getId(), post.getTitle()))
                .collect(Collectors.toList());
    }

    public PostResponse getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 ID입니다."));
        return new PostResponse(post.getId(), post.getTitle());
    }

    @Transactional
    public void updatePost(Long id, String newTitle) {
        Validator.validateTitle(newTitle);
        if (postRepository.existsByTitle(newTitle)) {
            throw new IllegalArgumentException("이미 존재하는 제목입니다.");
        }
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 ID입니다."));
        post.setTitle(newTitle);
    }

    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("삭제할 게시글이 존재하지 않습니다."));
        postRepository.delete(post);
    }

    public List<PostResponse> searchPostsByKeyword(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return List.of();
        }
        return postRepository.searchByKeyword(keyword.trim()).stream()
                .map(post -> new PostResponse(post.getId(), post.getTitle()))
                .collect(Collectors.toList());
    }
}