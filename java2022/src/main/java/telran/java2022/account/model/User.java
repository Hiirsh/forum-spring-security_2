package telran.java2022.account.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import telran.java2022.account.utils.Role;

@Getter
@EqualsAndHashCode(of = "login")
@Document(collection = "users")
@ManagedResource
public class User {
  @Id
  String login;
  String password;
  @Setter
  String firstName;
  @Setter
  String lastName;
  Set<Role> roles;
  LocalDateTime passwordExpiresAt;
  @Value("${password_expire_perion:30}")
  long passwordPeriod;

  public User() {
    roles = new HashSet<>();
    roles.add(Role.USER);
  }

  @Builder
  public User(String login, String password, String firstName, String lastName) {
    this();
    this.login = login;
    setPassword(password); // for encription this.password = encrypt(password)
    this.firstName = firstName;
    this.lastName = lastName;
  }

  @ManagedAttribute
  public void setPassword(String password) {
    this.password = password;
    this.passwordExpiresAt = LocalDateTime.now().plusDays(passwordPeriod);
  }

  public boolean addRole(String role) {
    Role newRole = Role.valueOf(role.toUpperCase());
    return this.roles.add(newRole);
  }

  public boolean deleteRole(String role) {
    return this.roles.remove(Role.valueOf(role.toUpperCase()));
  }

  public boolean isPasswordNotExpired() {
    return LocalDateTime.now().isBefore(passwordExpiresAt);
  }
}
