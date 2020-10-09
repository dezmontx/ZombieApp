package com.zombie.app.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
    private Integer status;
    private String token;

    public ResponseDTO(Integer status){
        this.status = status;
    }
}
