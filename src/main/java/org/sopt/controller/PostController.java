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

    public List<Post> searchPostsByKeyword(String keyword) {
        try {
            return postService.searchPostsByKeyword(keyword);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public boolean deletePostById(int id) {
        return postService.deletePostById(id);
    }

    public void savePostsToFile(String path) {
        try {
            postService.savePosts(path);
            System.out.println("게시글 파일 저장에 성공했습니다.");
        } catch (Exception e) {
            System.out.println("게시글 파일 저장 실패: " + e.getMessage());
        }
    }

    public void loadPostsFromFile(String path) {
        try {
            List<String> postList = postService.loadPosts(path);
            if (postList.isEmpty()) {
                System.out.println("파일에 저장된 내용이 없습니다.");
            } else {
                for (String postLine : postList) {
                    System.out.println(postLine);
                }
            }
        } catch (Exception e) {
            System.out.println("파일 불러오기 실패: " + e.getMessage());
        }
    }
}
