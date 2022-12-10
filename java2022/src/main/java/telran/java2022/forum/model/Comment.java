package telran.java2022.forum.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Comment {
  String user;
  String message;
  LocalDateTime dateCreated;
  int likes;

  @Builder
  public Comment(String user, String message) {
    this.user = user;
    this.message = message;
    likes = 0;
    dateCreated = LocalDateTime.now();
  }
  
}
