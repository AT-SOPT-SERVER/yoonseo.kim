package org.sopt.dto.response;

public record PostDetailResponse(
    Long id,
    String title,
    String content,
    String writerName
) {

}