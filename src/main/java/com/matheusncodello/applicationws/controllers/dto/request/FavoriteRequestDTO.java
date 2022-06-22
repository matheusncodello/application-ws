package com.matheusncodello.applicationws.controllers.dto.request;

public class FavoriteRequestDTO {
    private Long userId;
    private Long postId;

    public FavoriteRequestDTO(Long userId, Long postId) {
        this.userId = userId;
        this.postId = postId;
    }

    public FavoriteRequestDTO() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
