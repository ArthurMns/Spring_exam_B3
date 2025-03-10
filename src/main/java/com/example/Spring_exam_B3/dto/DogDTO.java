package com.example.Spring_exam_B3.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DogDTO {
    private String name;
    private String breed;
    private String imgUrl;
}
