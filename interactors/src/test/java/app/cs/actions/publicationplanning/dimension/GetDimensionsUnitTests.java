package app.cs.actions.publicationplanning.dimension;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.model.request.CreateDimensionRequest;
import app.cs.model.response.ResponseModel;
import app.cs.model.response.StringResponse;

import app.cs.actions.publicationplanning.perspective.TreeBuilder;
import app.cs.interfaces.dimension.IDimensionRepository;
import app.cs.impl.chapter.InMemoryViewStructure;
import app.cs.impl.delegate.factory.IDomainFactory;
import app.cs.impl.dimension.DimensionRepository;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.interfaces.dimension.IDimensionRepository;
import app.cs.interfaces.dimension.IMultiDimensionalObject;

@RunWith(MockitoJUnitRunner.class)
public class GetDimensionsUnitTests {

	private GetDimensions getDimensions;

	@Mock
	private DimensionRepository dimensionRepository;

	@Mock
	private InMemoryViewStructure cache;

	@Before
	public void setUp() {
		getDimensions = new GetDimensions(dimensionRepository);
	}

	@Test
	public void itShouldGetAllDimensions() throws IOException,
			URISyntaxException {

		// given
		String expected = "testString";

		// when
		when(dimensionRepository.getAllDimensions()).thenReturn(expected);
		ResponseModel dimensions = getDimensions.execute(null);
		
		StringResponse response = (StringResponse)dimensions;

		// then
		verify(dimensionRepository).getAllDimensions();
		assertThat(response.getResponseString()).isEqualTo(expected);

	}

}
