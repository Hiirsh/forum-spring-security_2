package telran.java2022.security.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java2022.account.dao.UserRepository;
import telran.java2022.forum.dao.ForumRepository;
import telran.java2022.forum.model.Post;

@Service("customSecurity")
@RequiredArgsConstructor
public class CustomWebSecurity {
  final ForumRepository forumRepository;
  final UserRepository userRepository;

  public boolean checkPostAuthor(String postId, String username) {
    Post post = forumRepository.findById(postId).orElse(null);
    return post != null && username.equalsIgnoreCase(post.getAuthor());
  }
}
