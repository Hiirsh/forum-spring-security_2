package telran.java2022.account.service;

import telran.java2022.account.dto.UserAddRolesDto;
import telran.java2022.account.dto.UserDto;
import telran.java2022.account.dto.UserRegisterDto;
import telran.java2022.account.dto.UserUpdateDto;

public interface UserService {
  UserDto registerUser(UserRegisterDto registerDto);

  UserDto loginUser(String[] credentials);

  UserDto removeUser(String login);

  UserDto updateUser(String login, UserUpdateDto updateDto);

  UserAddRolesDto addRole(String login, String role);
  
  UserAddRolesDto deleteRole(String login, String role);

  void changePassword(String login, String newPassword);
}
