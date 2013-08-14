package app.cs.impl.delegate.factory;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import app.cs.impl.delegate.factory.DomainFactory;
import app.cs.interfaces.model.MultiDimensionalObject;

import com.cs.data.api.core.GenericDomain;

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
