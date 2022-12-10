package telran.java2022.forum.service.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Service
@Slf4j(topic = "Forum service")
public class ForumServiceLogging {

  @Pointcut("execution(* telran.java2022.forum.service.ForumServiceImpl.findPostById(String)) && args(id)")
  public void findPostById(String id) {
  }

  @Pointcut("execution(public Iterable<telran.java2022.forum.dto.PostDto>"
      + "telran.java2022.forum.service.ForumServiceImpl.findPosts*(..))")
  public void bulkinFind() {
  }

  @Pointcut("@annotation(ForumLogger) && args(.., id)")
  public void annotated(String id) {
  }

  @Before("findPostById(id)")
  public void findPostByIdLogging(String id) {
    log.info("post with id {} requested", id);
  }

  @Around("bulkinFind()")
  public Object bulkinFindLoggin(ProceedingJoinPoint pjp) throws Throwable {
    Object[] args = pjp.getArgs();
    long t1 = System.currentTimeMillis();
    Object retValue = pjp.proceed(args);
    long t2 = System.currentTimeMillis();
    log.info("method - {}, duration - {}", pjp.getSignature().toLongString(), (t2 - t1));
    return retValue;
  }

  @AfterReturning("annotated(id)")
  public void changePostLogger(String id) {
    log.info("Post with id {} changed.", id);

  }
}
