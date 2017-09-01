package com.archsystemsinc.ipms.spring.web;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.archsystemsinc.ipms.sec.model.Privilege;
import com.archsystemsinc.ipms.sec.model.Role;
import com.archsystemsinc.ipms.sec.model.dto.User;

@Configuration
@ComponentScan( { "com.archsystemsinc.ipms.web", "com.archsystemsinc.ipms.sec.web", "com.archsystemsinc.ipms.persistence.event","com.archsystemsinc.ipms.jira" } )
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter{

	public WebConfig(){
		super();
	}

	// beans

	@Bean
	public XStreamMarshaller xstreamMarshaller(){
		final XStreamMarshaller xStreamMarshaller = new XStreamMarshaller();
		xStreamMarshaller.setAutodetectAnnotations( true );
		xStreamMarshaller.setAnnotatedClasses( new Class[ ] { User.class, Role.class, Privilege.class } );
		xStreamMarshaller.getXStream().addDefaultImplementation( java.sql.Timestamp.class, java.util.Date.class );

		return xStreamMarshaller;
	}

	@Bean
	public MappingJacksonHttpMessageConverter mappingJacksonHttpMessageConverter() {
		final MappingJacksonHttpMessageConverter mappingJacksonHttpMessageConverter = new MappingJacksonHttpMessageConverter();

		return mappingJacksonHttpMessageConverter;
	}

	@Bean
	public MarshallingHttpMessageConverter marshallingHttpMessageConverter(){
		final MarshallingHttpMessageConverter marshallingHttpMessageConverter = new MarshallingHttpMessageConverter();
		final XStreamMarshaller xstreamMarshaller = xstreamMarshaller();
		marshallingHttpMessageConverter.setMarshaller( xstreamMarshaller );
		marshallingHttpMessageConverter.setUnmarshaller( xstreamMarshaller );

		return marshallingHttpMessageConverter;
	}

	@Override
	public void configureMessageConverters( final List< HttpMessageConverter< ? >> converters ){
		super.configureMessageConverters( converters );

		converters.add( marshallingHttpMessageConverter() );
		converters.add(mappingJacksonHttpMessageConverter());
	}

}
