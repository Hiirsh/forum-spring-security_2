package telran.java2022.security.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import telran.java2022.account.dao.UserRepository;
import telran.java2022.account.model.User;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException(username));
    String[] roles = user.getRoles().stream()
        .map(r -> "ROLE_" + r.toString().toUpperCase())
        .toArray(String[]::new);
    System.out.println(user.isPasswordNotExpired());
    return new org.springframework.security.core.userdetails.User(
        username, user.getPassword(),
        true, true, user.isPasswordNotExpired(), true,
        AuthorityUtils.createAuthorityList(roles));
    // return new UserProfile(username, user.getPassword(),
    // AuthorityUtils.createAuthorityList(roles), user.isPasswordNotExpired());
  }
}
