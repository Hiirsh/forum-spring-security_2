package telran.java2022.account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import telran.java2022.account.utils.Role;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserAddRolesDto {
  String login;
  Iterable<Role> roles;
}
