package org.github.food2gether.profileservice;

import com.github.food2gether.shared.ExceptionHandler;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

@ApplicationScoped
public class ExceptionMapper {

  @ServerExceptionMapper
  public Response handleException(Exception e) {
    return ExceptionHandler.handle(e);
  }

}