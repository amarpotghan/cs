package app.cs.actions.publicationplanning.dimension;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.actions.contentplanning.assortment.DimensionInteractions;
import app.cs.impl.delegate.factory.IDomainFactory;
import app.cs.impl.dimension.DimensionRepository;
import app.cs.impl.inmemory.InMemoryViewStructure;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.impl.slicingdicing.TreeBuilder;
import app.cs.interfaces.dimension.IMultiDimensionalObject;

@RunWith(MockitoJUnitRunner.class)
public class DimensionServiceUnitTests {

	private DimensionInteractions dimensionService;

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
		dimensionService = new DimensionInteractions(dimensionRepository,
				treeBuilder, cache);
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
		String name = "test";
		String path = "A,B";
		String type = "spread";
		boolean isFolder = true;

		// when
		MultiDimensionalObject test = new MultiDimensionalObject();
		when(dimensionRepository.getDomain("MultiDimensionalObject"))
				.thenReturn(test);
		when(dimensionRepository.createDimension(test)).thenReturn(name);
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
		dimensionService.getAllBy(structure);

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
