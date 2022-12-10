package telran.java2022.forum.service;

import java.util.List;

import telran.java2022.forum.dto.CommentDto;
import telran.java2022.forum.dto.PeriodDto;
import telran.java2022.forum.dto.PostCreateDto;
import telran.java2022.forum.dto.PostDto;

public interface ForumService {
  PostDto addPost(String author, PostCreateDto data);

  PostDto findPostById(String id);

  void addLike(String id);

  List<PostDto> findPostsByAuthor(String author);

  PostDto addComent(String author, String postId, CommentDto comment);

  PostDto removePost(String postId);

  List<PostDto> findPostsByTags(List<String> tags);

  List<PostDto> findPostsByPeriod(PeriodDto period);

  PostDto updatePost(String postId, PostDto postUpdates);
}
