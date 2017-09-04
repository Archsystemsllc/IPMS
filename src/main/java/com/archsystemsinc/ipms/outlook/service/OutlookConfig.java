package com.archsystemsinc.ipms.outlook.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import microsoft.exchange.webservices.data.autodiscover.IAutodiscoverRedirectionUrl;
import microsoft.exchange.webservices.data.autodiscover.exception.AutodiscoverLocalException;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;

@Configuration
public class OutlookConfig {


	protected final Logger logger = LoggerFactory.getLogger(OutlookConfig.class);
	
	@Value( "${outlook.userid}" ) private String userid;
	@Value( "${outlook.password}" ) private String password;
	
	@Bean
	public ExchangeService exchangeService() {
		ExchangeService service = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
		ExchangeCredentials credentials = new WebCredentials(userid, password);

		service.setCredentials(credentials);
		try {
			service.autodiscoverUrl(userid, new IAutodiscoverRedirectionUrl() {
				
				@Override
				public boolean autodiscoverRedirectionUrlValidationCallback(String redirectionUrl)
						throws AutodiscoverLocalException { 
					return redirectionUrl.toLowerCase().startsWith("https://");
				}
			});
		} catch (Exception e) {
			logger.error("Unable to connect to outlook", e);
		}
		return service;

	}
}
