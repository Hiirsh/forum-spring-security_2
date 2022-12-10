package telran.java2022.forum.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.data.repository.CrudRepository;

import telran.java2022.forum.dto.PostDto;
import telran.java2022.forum.model.Post;

public interface ForumRepository extends CrudRepository<Post, String> {

  @Query("{'_id': ?0}")
  @Update("{'$inc': {'likes': 1}}")
  void addLike(String postId);

  List<Post> findByAuthor(String author);

  void deleteById(String postId);

  @Query("{'tags': {$in: ?0}}")
  List<PostDto> findByTags(List<String> tags);

  List<PostDto> findByDateCreatedBetween(LocalDate from, LocalDate to);

}
