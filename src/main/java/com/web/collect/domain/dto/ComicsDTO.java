package com.web.collect.domain.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComicsDTO {
    private String tittle;
    private String img;
    private String description;
    private String author;
    private String artist;
    private String value;

    @Override
    public String toString(){
        return "ComicsDTO{" +
                "tittle='" + tittle + '\'' +
                ", img='" + img + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", artist='" + artist + '\'' +
                ", value=" + value +
                '}';
    }
}
