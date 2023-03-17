package com.blog.blogapp.service.impl;

import com.blog.blogapp.Dto.PagePostResponse;
import com.blog.blogapp.Dto.PostDto;
import com.blog.blogapp.entities.Post;
import com.blog.blogapp.exception.ResourceNotFoundException;
import com.blog.blogapp.repository.PostRepository;
import com.blog.blogapp.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PagePostResponse getAllPosts(int pageNo, int pageSize,String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize,sort);
//
        Page<Post> posts = postRepository.findAll(pageable);
        //map entity list  to list of dto
        List<PostDto> content = posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

        PagePostResponse pagePostResponse = new PagePostResponse(
                content,
                posts.getNumber(),
                posts.getSize(),
                posts.getTotalElements(),
                posts.getTotalPages(),
                posts.isLast()
        );

        return pagePostResponse;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        //converting Dto to entity
        Post post = mapToEntity(postDto);
        // saving the data(post)
        post = postRepository.save(post);

        //save method returns the saved post and now the post object will contain the id
        //map entity to dto and send to controller


        return mapToDto(post);
    }

    @Override
    public PostDto getbyId(long id) {

        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "ID", id + ""));

        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "ID", id + ""));
        //setting new values /updating the values
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        //saving...
        post = postRepository.save(post);

        //mapping to dto
        return mapToDto(post);
    }

    @Override
    public String deleteByID(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "ID", id + ""));
        postRepository.delete(post);
        return "Post with ID-" + id + " deleted";
    }


    //converts postDto entity to post entity
    private Post mapToEntity(PostDto postDto) {
        Post post = modelMapper.map(postDto,Post.class);

        //        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());

        return post;

    }

    //converts post entity to postDto entity
    private PostDto mapToDto(Post post) {
        PostDto postDto = modelMapper.map(post,PostDto.class);
//        postDto.setId(post.getId());
//        postDto.setTitle(post.getTitle());
//        postDto.setDescription(post.getDescription());
//        postDto.setContent(post.getContent());

        return postDto;

    }
}
