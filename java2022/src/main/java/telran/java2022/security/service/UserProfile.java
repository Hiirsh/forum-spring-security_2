package telran.java2022.security.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

public class UserProfile extends User {

  private static final long serialVersionUID = 3890063154971793848L;
  @Getter
  private boolean passwordNotExpired;

  public UserProfile(String username, String password, Collection<? extends GrantedAuthority> authorities, boolean passwordNotExpired) {
    super(username, password, authorities);
    this.passwordNotExpired = passwordNotExpired;
  }

}
