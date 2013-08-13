package app.cs.factory;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import app.cs.model.HierarchicalObject;

import com.cs.data.api.core.GenericDomain;

public class DomainFactoryTests {

	private DomainFactory factory;

	@Before
	public void setUp() {
		factory = new DomainFactory();

	}

	@Test
	public void itShouldCreateInstanceOfContentObject() {
		// when
		GenericDomain testObject = factory.getDomainObject("ContentObject");

		// then
		assertThat(testObject).isInstanceOf(HierarchicalObject.class);

	}
}
