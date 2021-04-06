package com.billy.blog.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private String title;
    private String body;
    private LocalDate publishDate;
    private String authorId;

}
