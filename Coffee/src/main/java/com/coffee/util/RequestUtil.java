package com.coffee.util;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RequestUtil {

   public static Optional<HttpServletRequest> getCurrentHttpRequest() {
      log.debug("getCurrentHttpRequest");
       return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
           .filter(ServletRequestAttributes.class::isInstance)
           .map(ServletRequestAttributes.class::cast)
           .map(ServletRequestAttributes::getRequest);
   }

}