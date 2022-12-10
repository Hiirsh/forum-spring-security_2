package telran.java2022.forum.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import telran.java2022.forum.dto.CommentDto;
import telran.java2022.forum.dto.PeriodDto;
import telran.java2022.forum.dto.PostCreateDto;
import telran.java2022.forum.dto.PostDto;
import telran.java2022.forum.service.ForumService;

@RestController
@AllArgsConstructor
public class ForumController {
  ForumService forumService;

  @PostMapping("/forum/post/{author}")
  @ResponseStatus(HttpStatus.CREATED)
  public PostDto addPost(@PathVariable String author, @RequestBody PostCreateDto postCreateDto) {
    return forumService.addPost(author, postCreateDto);
  }

  @GetMapping("/forum/post/{postId}")
  public PostDto findPost(@PathVariable String postId) {
    return forumService.findPostById(postId);
  }

  @PutMapping("/forum/post/{postId}/like")
  public void addLike(@PathVariable String postId) {
    forumService.addLike(postId);
  }

  @GetMapping("/forum/posts/author/{author}")
  public List<PostDto> findPostsByAuthor(@PathVariable String author) {
    return forumService.findPostsByAuthor(author);
  }

  @PutMapping("/forum/post/{postId}/comment/{user}")
  public PostDto addComment(@PathVariable String postId, @PathVariable String user,
      @RequestBody CommentDto commentDto) {
    return forumService.addComent(user, postId, commentDto);
  }

  @DeleteMapping("/forum/post/{postId}")
  public PostDto deletePost(@PathVariable String postId) {
    return forumService.removePost(postId);
  }

  @PostMapping("/forum/posts/period")
  public List<PostDto> posts(@RequestBody PeriodDto period) {
    return forumService.findPostsByPeriod(period);
  }
  @PostMapping("/forum/posts/tags")
  public List<PostDto> posts(@RequestBody List<String> tags) {
    return forumService.findPostsByTags(tags);
  }

  @PutMapping("/forum/post/{postId}")
  public PostDto updatePost(@PathVariable String postId, @RequestBody PostDto tags) {
    return forumService.updatePost(postId, tags);
  }
}
