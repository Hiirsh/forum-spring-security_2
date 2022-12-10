package telran.java2022.account.controller;

import java.security.Principal;
import java.util.Base64;

// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java2022.account.dto.UserAddRolesDto;
import telran.java2022.account.dto.UserDto;
import telran.java2022.account.dto.UserRegisterDto;
import telran.java2022.account.dto.UserUpdateDto;
import telran.java2022.account.service.UserService;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class UserController {
  final UserService userService;

  @PostMapping("/register")
  public UserDto createUser(@RequestBody UserRegisterDto registerDto) {
    return userService.registerUser(registerDto);
  }

  @PostMapping("/login")
  public UserDto loginUser(@RequestHeader("Authorization") String token) {
    String basicAuth = token.split(" ")[1];
    String decode = new String(Base64.getDecoder().decode(basicAuth));
    String[] credentials = decode.split(":");
    return userService.loginUser(credentials);
  }

  @DeleteMapping("/user/{login}")
  // @PreAuthorize("#login.equals(principal.getUsername()) or hasRole('ADMIN')")
  public UserDto removeUser(@PathVariable String login) {
    return userService.removeUser(login);
  }

  @PutMapping("/user/{login}")
  // @PreAuthorize("#login == authentication.name")
  public UserDto updateUser(@PathVariable String login, @RequestBody UserUpdateDto updates) {
    return userService.updateUser(login, updates);
  }

  @PutMapping("/user/{login}/role/{role}")
  public UserAddRolesDto addRole(@PathVariable String login, @PathVariable String role) {
    return userService.addRole(login, role);
  }

  @DeleteMapping("/user/{login}/role/{role}")
  public UserAddRolesDto deleteRole(@PathVariable String login, @PathVariable String role) {
    return userService.deleteRole(login, role);
  }

  @PutMapping("/password")
  public void changePassword(Principal principal, @RequestHeader("X-Password") String newPAssword) {
    userService.changePassword(principal.getName(), newPAssword);
  }

}
