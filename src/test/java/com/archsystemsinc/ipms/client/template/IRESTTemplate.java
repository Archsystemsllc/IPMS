package com.archsystemsinc.ipms.client.template;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import com.archsystemsinc.ipms.client.marshall.IMarshaller;
import com.archsystemsinc.ipms.common.ClientOperation;
import com.archsystemsinc.ipms.common.IEntity;
import com.archsystemsinc.ipms.common.IOperations;
import com.jayway.restassured.specification.RequestSpecification;

public interface IRESTTemplate< T extends IEntity > extends IOperations< T >, IEntityOperations< T >, ITemplateAsResponse< T >, ITemplateAsURI< T >{
	
	String getURI();
	
	// authentication
	
	RequestSpecification givenAuthenticated();
	
	IMarshaller getMarshaller();
	
	// search
	
	List< T > search( final Pair< Long, ClientOperation > idOp, final Pair< String, ClientOperation > nameOp );
	
	List< T > search( final Pair< Long, ClientOperation > idOp, final Pair< String, ClientOperation > nameOp, final int page, final int size );
	
}
