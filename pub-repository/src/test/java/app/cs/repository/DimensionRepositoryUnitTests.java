package app.cs.repository;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.cs.data.core.nosql.mongodb.MongoRepository;

import app.cs.inmemory.InMemoryDimensionGroup;
import app.cs.model.HierarchicalObject;
import app.cs.repository.api.IDimensionRepository;
import app.cs.utils.FileUtils;

@RunWith(MockitoJUnitRunner.class)
public class DimensionRepositoryUnitTests {
	private IDimensionRepository dimensionRepository;

	@Mock
	private HierarchicalObject dimensionModel;

	@Mock
	private FileUtils fileUtils;

	@Mock
	private InMemoryDimensionGroup cache;

	@Mock
	private MongoRepository repository;

	@Before
	public void setUp() {

		dimensionRepository = new DimensionRepository(fileUtils, cache,
				repository);

	}

	@Test
	public void itShouldCreateADimension() {

		// given
		String dimensionId = "test";
		String groupId = "group";
		String path = "testPath";
		HierarchicalObject dimension = new HierarchicalObject("c01", "campaign",
				"co01","co01", "testPath");
		// when
		when(cache.ifGroupIdExistsFor(dimension.getPath())).thenReturn(true);
		when(cache.getDimensionGroupIdFor(dimension.getPath())).thenReturn(
				groupId);
		String actualId = dimensionRepository.createDimension(dimension);

		// then
		verify(cache).ifGroupIdExistsFor(dimension.getPath());
		verify(cache).updateCache(dimension, groupId);
		assertThat(actualId).isEqualTo(dimension.getId());

	}

	@Test
	public void itShouldGetAllDimensionInstances() {

		// given
		List<HierarchicalObject> expectedModels = new ArrayList<HierarchicalObject>();
		expectedModels.add(new HierarchicalObject());
		// when
		when(repository.findAll(HierarchicalObject.class)).thenReturn(
				expectedModels);
		List<HierarchicalObject> dimensions = dimensionRepository.getDimensions();
		// then
		verify(repository).findAll(HierarchicalObject.class);
		assertThat(dimensions).isNotEmpty();
		assertThat(dimensions).isEqualTo(expectedModels);

	}

	@Test
	public void itShouldGetDimensionsByType() {
		// given
		String type = "Campaign";
		// when

		dimensionRepository.getDimensionsOfType(type);
		// then

		verify(repository).getObjectsBy("type", type, HierarchicalObject.class);
	}

	@Test
	public void itShouldReturnAllDimensionsByGroupIdsAndType() {
		// given

		List<String> groupIds = null;
		String type2 = null;
		// when
		dimensionRepository.getDimensionsBy(type2, groupIds);
		// then
		verify(repository).getObjectForAndCriteria("type", type2, "groupIds",
				groupIds, HierarchicalObject.class);
	}

}
