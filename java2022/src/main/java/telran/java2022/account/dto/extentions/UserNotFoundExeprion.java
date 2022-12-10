package telran.java2022.account.dto.extentions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NoArgsConstructor;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User not found")
@NoArgsConstructor
public class UserNotFoundExeprion extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public UserNotFoundExeprion(String login) {
    super("User with login " + login + " not found");
  }
}
