package com.archsystemsinc.ipms.persistence;

import static com.archsystemsinc.ipms.common.ClientOperation.EQ;
import static com.archsystemsinc.ipms.common.ClientOperation.NEG_EQ;
import static com.archsystemsinc.ipms.util.SearchCommonUtil.ID;
import static com.archsystemsinc.ipms.util.SearchCommonUtil.NAME;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.archsystemsinc.ipms.common.ClientOperation;
import com.archsystemsinc.ipms.common.INameableEntity;
import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.spring.context.ContextTestConfig;
import com.archsystemsinc.ipms.spring.persistence.jpa.PersistenceJPAConfig;
import com.archsystemsinc.ipms.spring.testing.TestingConfig;
import com.archsystemsinc.ipms.util.IDUtils;
import com.archsystemsinc.ipms.util.SearchCommonUtil;

@SuppressWarnings( "unchecked" )
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = { TestingConfig.class, PersistenceJPAConfig.class, ContextTestConfig.class },loader = AnnotationConfigContextLoader.class )
public abstract class AbstractSearchPersistenceIntegrationTest< T extends INameableEntity >{
	
	// search/filter
	
	@Test
	public final void whenSearchByNameIsPerformed_thenNoExceptions(){
		final T existingEntity = getAPI().create( createNewEntity() );
		
		// When
		final ImmutableTriple< String, ClientOperation, String > nameConstraint = new ImmutableTriple< String, ClientOperation, String >( NAME, EQ, existingEntity.getName() );
		getAPI().search( nameConstraint );
	}
	
	// by id
	
	@Test
	public final void givenEntityWithIdExists_whenSearchByIdIsPerformed_thenResultIsFound(){
		final T existingEntity = getAPI().create( createNewEntity() );
		
		// When
		final ImmutableTriple< String, ClientOperation, String > idConstraint = new ImmutableTriple< String, ClientOperation, String >( ID, EQ, existingEntity.getId().toString() );
		final List< T > searchResults = getAPI().search( idConstraint );
		
		// Then
		assertThat( searchResults, hasItem( existingEntity ) );
	}
	
	@Test
	public final void givenEntityWithIdDoesNotExist_whenSearchByIdIsPerformed_thenResultIsNotFound(){
		final T existingEntity = getAPI().create( createNewEntity() );
		
		// When
		final ImmutableTriple< String, ClientOperation, String > idConstraint = new ImmutableTriple< String, ClientOperation, String >( ID, EQ, IDUtils.randomPositiveLongAsString() );
		final List< T > searchResults = getAPI().search( idConstraint );
		
		// Then
		assertThat( searchResults, not( hasItem( existingEntity ) ) );
	}
	
	// by name
	
	@Test
	public final void givenEntityWithNameExists_whenSearchByNameIsPerformed_thenResultIsFound(){
		final T existingEntity = getAPI().create( createNewEntity() );
		
		// When
		final ImmutableTriple< String, ClientOperation, String > nameConstraint = new ImmutableTriple< String, ClientOperation, String >( NAME, EQ, existingEntity.getName() );
		final List< T > searchResults = getAPI().search( nameConstraint );
		
		// Then
		assertThat( searchResults, hasItem( existingEntity ) );
	}
	
	@Test
	public final void givenEntityWithNameDoesNotExist_whenSearchByNameIsPerformed_thenResultIsNotFound(){
		final T existingEntity = getAPI().create( createNewEntity() );
		
		// When
		final ImmutableTriple< String, ClientOperation, String > nameConstraint = new ImmutableTriple< String, ClientOperation, String >( NAME, EQ, randomAlphabetic( 8 ) );
		final List< T > searchResults = getAPI().search( nameConstraint );
		
		// Then
		assertThat( searchResults, not( hasItem( existingEntity ) ) );
	}
	
	@Test
	public final void givenEntitiesExists_whenSearchByNegatedNameIsPerformed_thenResultsAreCorrect(){
		final T existingEntity1 = getAPI().create( createNewEntity() );
		final T existingEntity2 = getAPI().create( createNewEntity() );
		
		// When
		final ImmutableTriple< String, ClientOperation, String > nameConstraint = new ImmutableTriple< String, ClientOperation, String >( NAME, EQ, existingEntity1.getName() );
		final List< T > searchResults = getAPI().search( nameConstraint );
		
		// Then
		assertThat( searchResults, hasItem( existingEntity1 ) );
		assertThat( searchResults, not( hasItem( existingEntity2 ) ) );
	}
	
	// by id and name
	
	@Test
	public final void givenEntityExists_whenSearchIsPerformedByIdAndName_thenResultIsFound(){
		final T existingEntity = getAPI().create( createNewEntity() );
		
		// When
		final ImmutableTriple< String, ClientOperation, String > nameConstraint = new ImmutableTriple< String, ClientOperation, String >( NAME, EQ, existingEntity.getName() );
		final ImmutableTriple< String, ClientOperation, String > idConstraint = new ImmutableTriple< String, ClientOperation, String >( ID, EQ, existingEntity.getId().toString() );
		final List< T > searchResults = getAPI().search( nameConstraint, idConstraint );
		
		// Then
		assertThat( searchResults, hasItem( existingEntity ) );
	}
	
	@Test
	public final void givenEntityExists_whenSearchIsPerformedByIncorrectIdAndCorrectName_thenResultIsNotFound(){
		final T existingEntity = getAPI().create( createNewEntity() );
		
		// When
		final ImmutableTriple< String, ClientOperation, String > nameConstraint = new ImmutableTriple< String, ClientOperation, String >( NAME, EQ, existingEntity.getName() );
		final ImmutableTriple< String, ClientOperation, String > idConstraint = new ImmutableTriple< String, ClientOperation, String >( ID, EQ, IDUtils.randomPositiveLongAsString() );
		final List< T > searchResults = getAPI().search( nameConstraint, idConstraint );
		
		// Then
		assertThat( searchResults, not( hasItem( existingEntity ) ) );
	}
	
	@Test
	public final void givenEntityExists_whenSearchIsPerformedByCorrectIdAndIncorrectName_thenResultIsNotFound(){
		final T existingEntity = getAPI().create( createNewEntity() );
		
		// When
		final ImmutableTriple< String, ClientOperation, String > nameConstraint = new ImmutableTriple< String, ClientOperation, String >( NAME, EQ, randomAlphabetic( 8 ) );
		final ImmutableTriple< String, ClientOperation, String > idConstraint = new ImmutableTriple< String, ClientOperation, String >( ID, EQ, existingEntity.getId().toString() );
		final List< T > searchResults = getAPI().search( nameConstraint, idConstraint );
		
		// Then
		assertThat( searchResults, not( hasItem( existingEntity ) ) );
	}
	
	// by negated id,name
	
	@Test
	public final void whenSearchByNegatedNameIsPerformed_thenResultsAreFound(){
		final T existingEntity1 = getAPI().create( createNewEntity() );
		final T existingEntity2 = getAPI().create( createNewEntity() );
		
		// When
		final ImmutableTriple< String, ClientOperation, String > nameConstraint = new ImmutableTriple< String, ClientOperation, String >( NAME, NEG_EQ, existingEntity1.getName() );
		final List< T > searchResults = getAPI().search( nameConstraint );
		
		// Then
		assertThat( searchResults, not( hasItem( existingEntity1 ) ) );
		assertThat( searchResults, hasItem( existingEntity2 ) );
	}
	
	@Test
	public final void whenSearchByNegatedIdIsPerformed_thenResultsAreFound(){
		final T existingEntity1 = getAPI().create( createNewEntity() );
		final T existingEntity2 = getAPI().create( createNewEntity() );
		
		// When
		final ImmutableTriple< String, ClientOperation, String > idConstraint = new ImmutableTriple< String, ClientOperation, String >( ID, NEG_EQ, existingEntity1.getId().toString() );
		final List< T > searchResults = getAPI().search( idConstraint );
		
		// Then
		assertThat( searchResults, not( hasItem( existingEntity1 ) ) );
		assertThat( searchResults, hasItem( existingEntity2 ) );
	}
	
	// template method
	
	protected abstract IService< T > getAPI();
	// protected abstract IEntityOperations< T > getEntityOperations();
	
	protected abstract T createNewEntity();
	
	// util
	
	final Triple< String, ClientOperation, String > createNameConstraint( final ClientOperation operation, final String nameValue ){
		return new ImmutableTriple< String, ClientOperation, String >( SearchCommonUtil.NAME, operation, nameValue );
	}
	
	final Triple< String, ClientOperation, String > createIdConstraint( final ClientOperation operation, final Long idValue ){
		return new ImmutableTriple< String, ClientOperation, String >( SearchCommonUtil.ID, operation, idValue.toString() );
	}
	
}
