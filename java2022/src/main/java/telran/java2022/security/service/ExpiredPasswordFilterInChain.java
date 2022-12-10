package telran.java2022.security.service;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.filter.GenericFilterBean;

import org.springframework.stereotype.Service;

@Service
public class ExpiredPasswordFilterInChain extends GenericFilterBean {

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain next)
      throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) req;
    HttpServletResponse responce = (HttpServletResponse) res;
    Principal principal = request.getUserPrincipal();
    if (principal != null) {
      User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      if (checkEndPoint(request.getMethod(), request.getServletPath())) {
        if (!user.isCredentialsNonExpired()) {
          responce.sendError(403, "Password expired.");
          return;
        }
      }
      if (!user.isCredentialsNonExpired()) {
        user = new User(user.getUsername(), user.getPassword(), true, true, true, true, user.getAuthorities());
      }
    }
    next.doFilter(request, responce);
  }

  private boolean checkEndPoint(String method, String path) {
    return !("PUT".equals(method) && path.matches("/account/password/?"));
  }

}
