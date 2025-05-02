package org.sopt.controller;

import org.sopt.dto.request.PostRequest;
import org.sopt.dto.response.PostDetailResponse;
import org.sopt.dto.response.PostListResponse;
import org.sopt.global.common.base.BaseResponse;
import org.sopt.global.common.response.SuccessCode;
import org.sopt.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public BaseResponse<Void> createPost(
            @RequestHeader Long userId,
            @RequestBody final PostRequest request
    ) {
        postService.createPost(userId, request);
        return BaseResponse.success(SuccessCode.CREATE_POST, null);
    }

    @GetMapping
    public BaseResponse<List<PostListResponse>> getAllPosts() {
        return BaseResponse.success(SuccessCode.GET_ALL_POSTS, postService.getAllPosts());
    }

    @GetMapping("/{postId}")
    public BaseResponse<PostDetailResponse> getPostById(@PathVariable final Long postId) {
        return BaseResponse.success(SuccessCode.GET_POST, postService.getPostById(postId));
    }

    @PutMapping("/{postId}")
    public BaseResponse<Void> updatePostTitle(
        @PathVariable final Long postId,
        @RequestBody final PostRequest request
    ) {
        postService.updatePost(postId, request);
        return BaseResponse.success(SuccessCode.UPDATE_POST, null);
    }

    @DeleteMapping("/{postId}")
    public BaseResponse<Void> deletePostById(@PathVariable final Long postId) {
        postService.deletePost(postId);
        return BaseResponse.success(SuccessCode.DELETE_POST, null);
    }

    @GetMapping("/search")
    public BaseResponse<List<PostListResponse>> searchPostsByKeyword(@RequestParam final String keyword) {
        return BaseResponse.success(SuccessCode.SEARCH_POSTS, postService.searchPostsByKeyword(keyword));
    }
}
