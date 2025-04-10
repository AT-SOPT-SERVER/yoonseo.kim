package org.sopt.repository;

import org.sopt.domain.Post;

import java.io.*;
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

    public List<Post> searchPostsByKeyword(String keyword) {
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

    public void savePostsToFile(String path) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (Post post : postList) {
                writer.write(post.getId() + ", " + post.getTitle() + ", " + post.getCreatedAt().toString());
                writer.newLine();
            }
        }
    }

    public List<String> loadPostsFromFile(String path) throws IOException {
        List<String> postList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String postLine;
            while ((postLine = reader.readLine()) != null) {
                postList.add(postLine);
            }
        }
        return postList;
    }
}
