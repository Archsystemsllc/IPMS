package com.archsystemsinc.ipms.persistence.service;

import java.util.List;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.springframework.data.domain.Page;

import com.archsystemsinc.ipms.common.ClientOperation;
import com.archsystemsinc.ipms.common.IEntity;
import com.archsystemsinc.ipms.common.IOperations;

public interface IService< T extends IEntity > extends IOperations< T >{
	
	// search
	
	List< T > search( final ImmutableTriple< String, ClientOperation, String >... constraints );
	Page< T > searchPaged( final int page, final int size, final String sortBy, final ImmutableTriple< String, ClientOperation, String >... constraints );
	
	// find - all
	
	Page< T > findPaginated( final int page, final int size, final String sortBy );
	
}
