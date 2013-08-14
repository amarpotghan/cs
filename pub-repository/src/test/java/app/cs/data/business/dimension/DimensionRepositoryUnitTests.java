package app.cs.data.business.dimension;

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

import app.cs.data.business.api.dimension.IDimensionRepository;
import app.cs.data.business.api.dimension.IMultiDimensionalObject;
import app.cs.data.business.dimension.DimensionRepository;
import app.cs.data.business.dimension.InMemoryDimensionGroup;
import app.cs.data.business.dimension.MultiDimensionalObject;
import app.cs.utils.FileUtils;

import com.cs.data.core.nosql.mongodb.MongoRepository;

@RunWith(MockitoJUnitRunner.class)
public class DimensionRepositoryUnitTests {
	private IDimensionRepository dimensionRepository;

	@Mock
	private IMultiDimensionalObject dimensionModel;

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
		MultiDimensionalObject dimension = new MultiDimensionalObject("c01", "campaign",
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
		List<MultiDimensionalObject> expectedModels = new ArrayList<MultiDimensionalObject>();
		expectedModels.add(new MultiDimensionalObject());
		// when
		when(repository.findAll(MultiDimensionalObject.class)).thenReturn(
				expectedModels);
		List<MultiDimensionalObject> dimensions = dimensionRepository.getDimensions();
		// then
		verify(repository).findAll(MultiDimensionalObject.class);
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

		verify(repository).getObjectsBy("type", type, MultiDimensionalObject.class);
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
				groupIds, MultiDimensionalObject.class);
	}

}
