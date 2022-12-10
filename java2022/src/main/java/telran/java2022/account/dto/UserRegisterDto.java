package telran.java2022.account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDto {
  String login;
  @Setter
  String password;
  String firstName;
  String lastName;
}
