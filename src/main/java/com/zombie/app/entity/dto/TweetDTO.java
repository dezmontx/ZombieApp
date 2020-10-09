package com.zombie.app.entity.dto;

import lombok.Data;

@Data
public class TweetDTO {
    private Long id;
    private String message;
    private Long graveyardId;
}
