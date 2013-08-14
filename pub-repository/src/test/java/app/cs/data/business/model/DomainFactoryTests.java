package app.cs.data.business.model;

import org.junit.Before;
import org.junit.Test;

import app.cs.data.business.api.model.IDomainFactory;
import app.cs.data.business.dimension.MultiDimensionalObject;
import app.cs.data.business.model.DomainFactory;

import com.cs.data.api.core.GenericDomain;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

public class DomainFactoryTests {

	private IDomainFactory factory;

	@Before
	public void setUp() {
		factory = new DomainFactory();

	}

	@Test
	public void itShouldCreateInstanceOfContentObject() {
		// when
		GenericDomain testObject = factory.getDomainObject("MultiDimensionalObject");

		// then
		assertThat(testObject).isInstanceOf(MultiDimensionalObject.class);

	}
}
