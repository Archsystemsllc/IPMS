/**
* Copyright (c) 2017, Archsystems Inc and/or its affiliates. All rights reserved.
*/
package com.archsystemsinc.ipms.sec.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * The utility class for all Security related calls
 * @author Beni
 *
 */
public class SecurityUtil {
	
	/**
	 * Returns the current user is admin user or not
	 * @return true if admin user, otherwise false
	 */
	public static boolean isAdminUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null)
			return true;
		
		if(authentication != null) {
			for(GrantedAuthority ga:authentication.getAuthorities()) {
				if(ga.getAuthority().equals("AdminOfSecurityService")) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * This methods checks the current user has permission to the given url or not
	 * @param uri The url to check 
	 * @return true if the current user has access otherwise false
	 */
	public static boolean hasPermission(String uri) {
		
		if(isAdminUser())
			return true;
		
		String resource = null;
		if(uri.matches("^/\\S+/app/\\S+") || uri.matches("^/app/\\S+"))
			resource = uri.substring(uri.indexOf("/app/"));	
		
		if(resource!=null && !uri.endsWith(".js") && !uri.endsWith(".css")) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if(authentication != null) {	
				for(GrantedAuthority ga:authentication.getAuthorities()) {
					String authorities[] = ga.getAuthority().split(",");
					for (String authority : authorities) {
						if(resource.matches(authority.trim())) {
							return true;
						}
					}
				}
			}
			return false;
		} else {
			return true;
		}	
	}
}
