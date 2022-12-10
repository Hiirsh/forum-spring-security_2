package telran.java2022.forum.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import telran.java2022.forum.dao.ForumRepository;
import telran.java2022.forum.dto.CommentDto;
import telran.java2022.forum.dto.PeriodDto;
import telran.java2022.forum.dto.PostCreateDto;
import telran.java2022.forum.dto.PostDto;
import telran.java2022.forum.dto.exeptions.PostNotFoundExeprion;
import telran.java2022.forum.model.Comment;
import telran.java2022.forum.model.Post;
import telran.java2022.forum.service.logging.ForumLogger;

@Component
@AllArgsConstructor
@Slf4j()
public class ForumServiceImpl implements ForumService {
  final ModelMapper modelMapper;
  final ForumRepository repository;

  @Override
  public PostDto addPost(String author, PostCreateDto data) {
    Post post = Post.builder()
        .title(data.getTitle())
        .content(data.getContent())
        .author(author)
        .tags(data.getTags())
        .build();
    repository.save(post);
    return modelMapper.map(post, PostDto.class);
  }

  @Override
  public PostDto findPostById(String id) {
    log.info("post with id {} handled", id);
    Post post = repository.findById(id).orElseThrow(() -> new PostNotFoundExeprion(id));
    return modelMapper.map(post, PostDto.class);
  }

  @Override
  @ForumLogger
  public void addLike(String id) {
    repository.addLike(id);
  }

  @Override
  public List<PostDto> findPostsByAuthor(String author) {
    return repository.findByAuthor(author)
        .stream()
        .map(p -> modelMapper.map(p, PostDto.class))
        .collect(Collectors.toList());
  }

  @Override
  public PostDto addComent(String user, String postId, CommentDto comment) {
    Post post = repository.findById(postId).orElseThrow(() -> new PostNotFoundExeprion(postId));
    post.addComment(Comment.builder().user(user).message(comment.getMessage()).build());
    repository.save(post);
    return modelMapper.map(post, PostDto.class);
  }

  @Override
  public PostDto removePost(String postId) {
    Post post = repository.findById(postId).orElseThrow(() -> new PostNotFoundExeprion(postId));
    repository.deleteById(postId);
    return modelMapper.map(post, PostDto.class);
  }

  @Override
  public List<PostDto> findPostsByTags(List<String> tags) {
    return repository.findByTags(tags);
  }

  @Override
  public List<PostDto> findPostsByPeriod(PeriodDto period) {
    return repository.findByDateCreatedBetween(period.getDateFrom(), period.getDateTo());
  }

  @Override
  public PostDto updatePost(String postId, PostDto postUpdates) {
    Post post = repository.findById(postId).orElseThrow(() -> new PostNotFoundExeprion(postId));
    if (postUpdates.getTitle() != null) {
      post.setTitle(postUpdates.getTitle());
    }
    if (postUpdates.getContent() != null) {
      post.setContent(postUpdates.getContent());
    }
    if (postUpdates.getTags() != null) {
      post.setTags(postUpdates.getTags());
    }
    repository.save(post);
    return modelMapper.map(post, PostDto.class);
  }

}
