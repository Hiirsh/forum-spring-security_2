package telran.java2022.forum.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class PostDto {
  String id;
  String title;
  String content;
  String author;
  LocalDateTime dateCreated;
  List<String> tags;
  Integer likes;
  List<CommentDto> comments;
}
