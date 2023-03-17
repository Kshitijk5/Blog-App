package com.blog.blogapp.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PagePostResponse {
    private List<PostDto> content;
    private int pageNo;
    private int pagesize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
