package telran.java2022.forum.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Document(collection = "post")
public class Post {
  String id;
  @Setter
  String title;
  @Setter
  String content;
  String author;
  LocalDateTime dateCreated;
  @Setter
  List<String> tags;
  Integer likes;
  List<Comment> comments;

  @Builder
  public Post(String title, String content, String author, List<String> tags) {
    this.title = title;
    this.content = content;
    this.author = author;
    this.dateCreated = LocalDateTime.now();
    this.tags = tags;
    this.likes = 0;
    this.comments = new ArrayList<>();
  }

  public Post addComment(Comment message) {
    comments.add(message);
    return this;
  }

}
