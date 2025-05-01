package org.sopt.service;

import jakarta.transaction.Transactional;
import org.sopt.domain.Post;
import org.sopt.domain.User;
import org.sopt.dto.request.PostRequest;
import org.sopt.dto.response.PostResponse;
import org.sopt.global.common.exception.CustomException;
import org.sopt.global.common.exception.ErrorCode;
import org.sopt.global.util.Validator;
import org.sopt.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    @Transactional
    public void createPost(Long userId, PostRequest request) {
        Validator.validateTitle(request.title());
        Validator.validateContent(request.content());

        if (postRepository.existsByTitle(request.title())) {
            throw new CustomException(ErrorCode.DUPLICATE_POST_TITLE);
        }

        User writer = userService.findByIdOrThrow(userId);
        Post post = new Post(request.title(), request.content(), writer);
        postRepository.save(post);
    }

    public List<PostResponse> getAllPosts() {
        return postRepository.findAll().stream()
                .map(post -> new PostResponse(post.getId(), post.getTitle(), post.getContent()))
                .collect(Collectors.toList());
    }

    public PostResponse getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
        return new PostResponse(post.getId(), post.getTitle(), post.getContent());
    }

    @Transactional
    public void updatePost(Long id, PostRequest request) {
        String newTitle = request.title();
        String newContent = request.content();

        Validator.validateTitle(newTitle);
        Validator.validateContent(newContent);

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        if (postRepository.existsByTitleAndIdNot(newTitle, id)) {
            throw new CustomException(ErrorCode.DUPLICATE_POST_TITLE);
        }
        post.setTitle(newTitle);
        post.setContent(newContent);
    }

    @Transactional
    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_DELETE_NOT_FOUND));
        postRepository.delete(post);
    }

    public List<PostResponse> searchPostsByKeyword(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            throw new CustomException(ErrorCode.INVALID_INPUT_VALUE);
        }
        return postRepository.searchByKeyword(keyword.trim()).stream()
                .map(post -> new PostResponse(post.getId(), post.getTitle(), post.getContent()))
                .collect(Collectors.toList());
    }
}