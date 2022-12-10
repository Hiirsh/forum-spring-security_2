package telran.java2022.account.dto.extentions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NoArgsConstructor;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "User already exists")
@NoArgsConstructor
public class UserAlreadyExests extends RuntimeException {
  private static final long serialVersionUID = 6066364373531884549L;

  public UserAlreadyExests(String login) {
    super("User " + login + " exist");
  }

}
