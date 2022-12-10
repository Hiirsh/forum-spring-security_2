package telran.java2022.forum.dto.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NoArgsConstructor;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Post not found")
@NoArgsConstructor
public class PostNotFoundExeprion extends RuntimeException{
  private static final long serialVersionUID = 1L;

  public PostNotFoundExeprion(String id) {
    super("Post id=" + id + "not found");
  }
}
