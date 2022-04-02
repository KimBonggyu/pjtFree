package com.coffee.util;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EntityUtil {

   public static <T> T bindEntity(T entity) {
	   return bindEntity(entity, null);
   }
   public static <T> T bindEntity(T entity, HttpServletRequest request) {
	   
	   try {   BeanUtils.setProperty(entity, "regNo", "admin");   } catch (Exception ignored) { /* 에러 무시 */ }
	   try {   BeanUtils.setProperty(entity, "modNo", "admin");   } catch (Exception ignored) { /* 에러 무시 */ }
	   
	   if(request == null) {
		   Optional<HttpServletRequest> req = RequestUtil.getCurrentHttpRequest();
		   if( req.isPresent() ) {
			   request = req.get();
		   }
	   }
	   if(request != null) {
		   try {   BeanUtils.setProperty(entity, "regUrl", request.getRequestURI());   } catch (Exception ignored) { /* 에러 무시 */ }
		   try {   BeanUtils.setProperty(entity, "modUrl", request.getRequestURI());   } catch (Exception ignored) { /* 에러 무시 */ }
	   }
	   
//	   try {   BeanUtils.setProperty(entity, "regDts", new Date());   } catch (Exception ignored) { /* 에러 무시 */ }
//	   try {   BeanUtils.setProperty(entity, "modDts", new Date());   } catch (Exception ignored) { /* 에러 무시 */ }
	   
	   log.debug("newEntity: {}", entity);
	   
	   return entity; 
   }
   
   
}
