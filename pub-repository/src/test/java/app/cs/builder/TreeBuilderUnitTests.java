package app.cs.builder;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.inmemory.InMemoryDimensionGroup;
import app.cs.model.HierarchicalObject;
import app.cs.repository.DimensionRepository;

@RunWith(MockitoJUnitRunner.class)
public class TreeBuilderUnitTests {

	private TreeBuilder treeBuilder;

	@Mock
	private InMemoryDimensionGroup cache;

	@Mock
	private DimensionRepository dimensionRepository;

	@Before
	public void setUp() {
		treeBuilder = new TreeBuilder(cache, dimensionRepository);
	}

	public void itShouldParseGivenStringWithDelimeter() {
		// given
		String structure = "Campaign-MasterPublication-Publication";

		// when
		String[] rules = treeBuilder.getTypes(structure);
		// then
		assertThat(rules).isNotEmpty();
		assertThat(rules.length).isEqualTo(3);
	}

	@Test
	public void itShouldSaparateNotConnectedTrees() {

		// given
		String type = "Campaign";
		List<HierarchicalObject> result = new ArrayList<HierarchicalObject>();

		// when

		when(dimensionRepository.getDimensionsOfType(type)).thenReturn(result);
		List<HierarchicalObject> models = treeBuilder.getAllSeparatedTrees(type);
		// then
		verify(dimensionRepository).getDimensionsOfType(type);
		assertThat(models).isEqualTo(result);
	}

	@Test
	public void itShouldBuildTreeForGivenRoot() {
		// given
		HierarchicalObject dimensionModel = new HierarchicalObject("cp01", "Campaign",
				"cp01", "cp01", "-1");
		ArrayList<String> groupIds = new ArrayList<String>();
		dimensionModel.setGroupId(groupIds);
		String[] rules = { "Campaign", "MasterPublication", "PublicationGroup",
				"Publication" };

		// when
		treeBuilder.buildTreeForRootNode(dimensionModel, rules, null);
		// then
		verify(dimensionRepository, times(1)).getDimensionsBy(rules[1],
				groupIds);
	}

	@Test
	public void itShouldReturnAllDimensionsOfGivenGroupIdsAndType() {
		// given
		List<String> groupIds = new ArrayList<String>();
		String type = "MasterPublication";

		// when

		treeBuilder.getAllChildrenOfCurrentRoot(groupIds, type);
		// then
		verify(dimensionRepository, times(1)).getDimensionsBy(type, groupIds);
	}

}
