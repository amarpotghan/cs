package app.cs.impl.dimension;

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

import app.cs.impl.chapter.InMemoryViewStructure;
import app.cs.impl.delegate.builder.TreeBuilder;
import app.cs.impl.delegate.factory.IDomainFactory;
import app.cs.interfaces.dimension.IMultiDimensionalObject;
import app.cs.interfaces.model.MultiDimensionalObject;

@RunWith(MockitoJUnitRunner.class)
public class DimensionServiceUnitTests {

	private DimensionService dimensionService;

	@Mock
	private IMultiDimensionalObject dimensionModel;

	@Mock
	private TreeBuilder treeBuilder;

	@Mock
	private DimensionRepository dimensionRepository;

	@Mock
	private IDomainFactory factory;

	@Mock
	private InMemoryViewStructure cache;

	@Before
	public void setUp() {
		dimensionService = new DimensionService(dimensionRepository,
				treeBuilder, factory, cache);
	}

	@Test
	public void itShouldGetAllDimensions() throws IOException,
			URISyntaxException {

		// given
		String expected = "testString";

		// when
		when(dimensionRepository.getAllDimensions()).thenReturn(expected);
		String dimensions = dimensionService.getAll();

		// then

		verify(dimensionRepository).getAllDimensions();
		assertThat(dimensions).isEqualToIgnoringCase(expected);

	}

	@Test
	public void itShouldCreateADimension() {
		// given
		String expectedDimensionId = "dimkension01";
		String name = "test";
		String path = "A,B";
		String type = "spread";
		String isFolder = "true";

		// when
		MultiDimensionalObject test = new MultiDimensionalObject();
		when(dimensionRepository.createDimension(test)).thenReturn(name);
		when(factory.getDomainObject("ContentObject")).thenReturn(test);
		String dimensionId = dimensionService
				.create(type, name, path, isFolder);

		// then
		verify(dimensionRepository).createDimension(test);
		assertThat(dimensionId).isEqualTo(name);

	}

	@Test
	public void itShouldGetDimensionsByStructure() {
		// given

		String structure = "C-MP-P";
		// when
		List<MultiDimensionalObject> models = dimensionService
				.getAllBy(structure);

		// then
		verify(cache).setCurrentViewStructure("view", structure);
		verify(treeBuilder).buildTree(structure);
	}

	@Test
	public void itShouldSaveCurrentViewStructureToCache() {
		// given
		String currentViewStructure = "C-M-P-D";
		// when

		dimensionService.setCurrentViewStructure(currentViewStructure);

		// then
		verify(cache).setCurrentViewStructure("view", currentViewStructure);
	}

}
