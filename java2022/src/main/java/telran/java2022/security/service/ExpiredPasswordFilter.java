// package telran.java2022.security.service;

// import java.io.IOException;
// import java.security.Principal;

// import javax.servlet.FilterChain;
// import javax.servlet.ServletException;
// import javax.servlet.ServletRequest;
// import javax.servlet.ServletResponse;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;

// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.web.filter.GenericFilterBean;

// import org.springframework.stereotype.Service;
// @Service
// public class ExpiredPasswordFilter extends GenericFilterBean {

//   @Override
//   public void doFilter(ServletRequest req, ServletResponse res, FilterChain next)
//       throws IOException, ServletException {
//     HttpServletRequest request = (HttpServletRequest) req;
//     HttpServletResponse responce = (HttpServletResponse) res;
//     Principal principal = request.getUserPrincipal();
//     if (principal != null && checkEndPoint(request.getMethod(), request.getServletPath())) {
//       UserProfile userProfile = (UserProfile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//       if (!userProfile.isPasswordNotExpired()) {
//       responce.sendError(403, "Password expired.");
//       return;
//       }
//     }
//     next.doFilter(request, responce);
//   }

//   private boolean checkEndPoint(String method, String path) {
//     return !("PUT".equals(method) && path.matches("/account/password/?"));
//   }

// }
