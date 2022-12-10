package telran.java2022.forum.dto;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class PostCreateDto {
  String author;
  String title;
  String content;
  List<String> tags;
}
