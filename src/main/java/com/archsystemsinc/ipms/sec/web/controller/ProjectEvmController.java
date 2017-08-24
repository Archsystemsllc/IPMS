package com.archsystemsinc.ipms.sec.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriComponentsBuilder;

import com.archsystemsinc.ipms.common.exceptions.ConflictException;
import com.archsystemsinc.ipms.common.web.RestPreconditions;
import com.archsystemsinc.ipms.sec.model.ProjectEvm;
import com.archsystemsinc.ipms.sec.persistence.service.IProjectEvmService;
import com.archsystemsinc.ipms.sec.util.SecurityConstants;
import com.archsystemsinc.ipms.util.SearchCommonUtil;
import com.archsystemsinc.ipms.web.common.AbstractController;

@Controller
@RequestMapping( value = "projectevm" )
public class ProjectEvmController extends AbstractController< ProjectEvm >{

	@Autowired private IProjectEvmService service;

	public ProjectEvmController(){
		super( ProjectEvm.class );
	}

	// API

	// search

	@RequestMapping( params = { SearchCommonUtil.Q_PARAM },method = RequestMethod.GET )
	@ResponseBody
	public List< ProjectEvm > search( @RequestParam( SearchCommonUtil.Q_PARAM ) final String queryString ){
		return searchInternal( queryString );
	}

	// find - all/paginated

	@RequestMapping( params = { "page", "size" },method = RequestMethod.GET )
	@ResponseBody
	public List< ProjectEvm > findPaginated( @RequestParam( "page" ) final int page, @RequestParam( "size" ) final int size, @RequestParam( value = "sortBy",required = false ) final String sortBy, final UriComponentsBuilder uriBuilder, final HttpServletResponse response ){
		return findPaginatedInternal( page, size, sortBy, uriBuilder, response );
	}

	@RequestMapping( method = RequestMethod.GET )
	@ResponseStatus( HttpStatus.SEE_OTHER )
	public void findAll( final UriComponentsBuilder uriBuilder, final HttpServletResponse response ){
		findAllRedirectToPagination( uriBuilder, response );
	}

	// find - one

	@RequestMapping( value = "/{id}",method = RequestMethod.GET )
	@ResponseBody
	public ProjectEvm findOne( @PathVariable( "id" ) final Long id, final UriComponentsBuilder uriBuilder, final HttpServletResponse response ){
		return findOneInternal( id, uriBuilder, response );
	}

	@RequestMapping( params = "name",method = RequestMethod.GET )
	@ResponseBody
	public ProjectEvm findOneByName( @RequestParam( "name" ) final String name ){
		ProjectEvm resource = null;
		try{
			resource = RestPreconditions.checkNotNull( getService().findByName( name ) );
		}
		catch( final InvalidDataAccessApiUsageException ex ){
			logger.error( "InvalidDataAccessApiUsageException on find operation" );
			logger.warn( "InvalidDataAccessApiUsageException on find operation", ex );
			throw new ConflictException( ex );
		}

		return resource;
	}

	// create

	//@RequestMapping( method = RequestMethod.POST, produces="application/xml" )
	//@ResponseStatus( HttpStatus.CREATED )
	// @Secured( SecurityConstants.PRIVILEGE_USER_WRITE )
	//public void create( @RequestBody final ProjectEvm resource, final UriComponentsBuilder uriBuilder, final HttpServletResponse response ){
	//	createInternal( resource, uriBuilder, response );
	//}

	@RequestMapping( method = RequestMethod.POST, consumes="application/xml", produces="application/xml" )
	@ResponseStatus( HttpStatus.CREATED )
	// @Secured( SecurityConstants.PRIVILEGE_USER_WRITE )
	public void create1( @RequestBody final ProjectEvm resource) throws Exception{
		
		try {
			ProjectEvm evm = getService().findOne(resource.getId());
			if(evm!=null) {
				service.delete(resource.getId());
			}
			service.create(resource);
		}catch(Exception ex) {
			throw new Exception( ex );
		}
		
	}
	
	// update

	@RequestMapping( method = RequestMethod.PUT )
	@ResponseStatus( HttpStatus.OK )
	@Secured( SecurityConstants.CAN_USER_WRITE )
	public void update( @RequestBody final ProjectEvm resource ){
		updateInternal( resource );
	}
	// delete

	@RequestMapping( value = "/{id}",method = RequestMethod.DELETE )
	@ResponseStatus( HttpStatus.NO_CONTENT )
	@Secured( SecurityConstants.CAN_USER_WRITE )
	public void delete( @PathVariable( "id" ) final Long id ){
		deleteByIdInternal( id );
	}

	// Spring

	@Override
	protected final IProjectEvmService getService(){
		return service;
	}

}
