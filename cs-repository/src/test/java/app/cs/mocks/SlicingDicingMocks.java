package app.cs.mocks;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cs.data.core.nosql.redis.RedisRepository;

import app.cs.impl.dimension.DimensionRepository;
import app.cs.impl.dimension.InMemoryDimensionGroup;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.interfaces.dimension.IDimensionRepository;
import app.cs.interfaces.dimension.IMultiDimensionalObject;
import app.cs.interfaces.slicingdicing.ITreeBuilder;
import app.cs.utils.ArrayUtils;
import app.cs.utils.FileUtils;

/**
 * The Class TreeBuilder.
 */
@Component
public class SlicingDicingMocks implements ITreeBuilder {

	/** The cache. */

	/** The repository. */
	private IDimensionRepository repository;

	/** The utils. */
	private ArrayUtils utils;

	/** The delimeter. */
	private final String DELIMETER = "-";

	/**
	 * Instantiates a new tree builder.
	 * 
	 * @param cache
	 *            the cache
	 * @param repository
	 *            the repository
	 */
	@Autowired
	public SlicingDicingMocks(IDimensionRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<MultiDimensionalObject> buildTree(String structure) {
		try {
			if (structure.equals("MasterPublication-Campaign-Publication")) {

				return new ObjectMapper()
						.readValue(
								"[\n    {\n        \"id\": \"MP01\",\n        \"type\": \"MasterPublication\",\n        \"path\": \"-1\",\n        \"name\": \"MP01\",\n        \"title\": \"MP01\",\n        \"isFolder\": true,\n        \"children\": [\n            {\n                \"id\": \"CP01\",\n                \"type\": \"Campaign\",\n                \"path\": \"-1,MP01\",\n                \"name\": \"CP01\",\n                \"title\": \"CP01\",\n                \"isFolder\": true,\n                \"children\": [\n                    {\n                        \"id\": \"P01\",\n                        \"type\": \"Publication\",\n                        \"path\": \"MP01,CP01\",\n                        \"name\": \"P01\",\n                        \"title\": \"P01\",\n                        \"isFolder\": true,\n                        \"children\": [\n                            {\n                                \"id\": \"Chapter02\",\n                                \"type\": \"Chapter\",\n                                \"path\": \"CP01,MP01,P01\",\n                                \"name\": \"Chapter02\",\n                                \"title\": \"Chapter02\",\n                                \"isFolder\": true,\n                                \"children\": [\n                                    {\n                                        \"id\": \"Page03\",\n                                        \"type\": \"Page\",\n                                        \"path\": \"CP01,MP02,P02,Chapter02\",\n                                        \"name\": \"Page03\",\n                                        \"title\": \"Page03\",\n                                        \"isFolder\": false,\n                                        \"children\": null,\n                                        \"assortment\": null,\n                                        \"groupId\": null\n                                    },\n                                    {\n                                        \"id\": \"Page02\",\n                                        \"type\": \"Page\",\n                                        \"path\": \"CP01,MP02,P02,Chapter02\",\n                                        \"name\": \"Page02\",\n                                        \"title\": \"Page02\",\n                                        \"isFolder\": false,\n                                        \"children\": null,\n                                        \"assortment\": null\n                                    }\n                                ],\n                                \"assortment\": null\n                            }\n                        ],\n                        \"assortment\": null\n                    }\n                ],\n                \"assortment\": null,\n                \"groupId\": [\n                    \"f879d2a2-2f54-4069-9590-820e649cbb93\",\n                    \"9c0e3e7c-7dc9-4bfa-90a4-d5912d640fe9\",\n                    \"37801368-1d75-4096-ab74-9b6663bf0512\"\n                ]\n            }\n        ],\n        \"assortment\": null,\n      \n        \"groupId\": [\n            \"9c0e3e7c-7dc9-4bfa-90a4-d5912d640fe9\"\n        ]\n    },\n    {\n        \"id\": \"MP02\",\n        \"type\": \"MasterPublication\",\n        \"path\": \"-1\",\n        \"name\": \"MP02\",\n        \"title\": \"MP02\",\n        \"isFolder\": true,\n        \"children\": [\n            {\n                \"id\": \"CP01\",\n                \"type\": \"Campaign\",\n                \"path\": \"-1,MP02\",\n                \"name\": \"CP01\",\n                \"title\": \"CP01\",\n                \"isFolder\": true,\n                \"children\": [\n                    {\n                        \"id\": \"P02\",\n                        \"type\": \"Publication\",\n                        \"path\": \"MP02,CP01\",\n                        \"name\": \"P02\",\n                        \"title\": \"P02\",\n                        \"isFolder\": true,\n                        \"children\": [\n                            {\n                                \"id\": \"Chapter01\",\n                                \"type\": \"Chapter\",\n                                \"path\": \"MP02,CP01,P02\",\n                                \"name\": \"Chapter01\",\n                                \"title\": \"Chapter01\",\n                                \"isFolder\": true,\n                                \"children\": [],\n                                \"assortment\": null\n                            }\n                        ],\n                        \"assortment\": null\n                    }\n                ],\n                \"groupId\": [\n                    \"f879d2a2-2f54-4069-9590-820e649cbb93\",\n                    \"9c0e3e7c-7dc9-4bfa-90a4-d5912d640fe9\",\n                    \"37801368-1d75-4096-ab74-9b6663bf0512\"\n                ]\n            }\n        ],\n        \"assortment\": null,\n        \"groupId\": [\n            \"37801368-1d75-4096-ab74-9b6663bf0512\"\n        ]\n    },\n    {\n        \"id\": \"MP03\",\n        \"type\": \"MasterPublication\",\n        \"path\": \"-1\",\n        \"name\": \"MP03\",\n        \"title\": \"MP03\",\n        \"isFolder\": true,\n        \"children\": [\n            {\n                \"id\": \"CP02\",\n                \"type\": \"Campaign\",\n                \"path\": \"-1,MP03\",\n                \"name\": \"CP02\",\n                \"title\": \"CP02\",\n                \"isFolder\": true,\n                \"children\": [\n                    {\n                        \"id\": \"P03\",\n                        \"type\": \"Publication\",\n                        \"path\": \"MP03,CP02\",\n                        \"name\": \"P03\",\n                        \"title\": \"P03\",\n                        \"isFolder\": true,\n                        \"children\": [\n                            {\n                                \"id\": \"Page01\",\n                                \"type\": \"Page\",\n                                \"path\": \"CP02,MP03,P03\",\n                                \"name\": \"Page01\",\n                                \"title\": \"Page01\",\n                                \"isFolder\": false,\n                                \"children\": null,\n                                \"assortment\": null\n                            }\n                        ],\n                        \"assortment\": null,\n                        \"groupId\": [\n                            \"a5c9cca9-34d3-4d96-ae78-2927c73b061b\"\n                        ]\n                    }\n                ],\n                \"assortment\": null,\n                \"groupId\": [\n                    \"7c8e3991-e0f3-4e4d-8489-fb73cdde7d4a\",\n                    \"a5c9cca9-34d3-4d96-ae78-2927c73b061b\"\n                ]\n            }\n        ],\n        \"assortment\": null,\n        \"groupId\": [\n            \"a5c9cca9-34d3-4d96-ae78-2927c73b061b\"\n        ]\n    }\n]",
								new TypeReference<List<MultiDimensionalObject>>() {
								});
			} else {
				return new ObjectMapper()
						.readValue(
								"[\n    {\n        \"id\": \"CP01\",\n        \"type\": \"Campaign\",\n        \"path\": \"-1\",\n        \"name\": \"CP01\",\n        \"title\": \"CP01\",\n        \"isFolder\": true,\n        \"children\": [\n            {\n                \"id\": \"MP01\",\n                \"type\": \"MasterPublication\",\n                \"path\": \"-1,CP01\",\n                \"name\": \"MP01\",\n                \"title\": \"MP01\",\n                \"isFolder\": true,\n                \"children\": [\n                    {\n                        \"id\": \"P01\",\n                        \"type\": \"Publication\",\n                        \"path\": \"CP01,MP01\",\n                        \"name\": \"P01\",\n                        \"title\": \"P01\",\n                        \"isFolder\": true,\n                        \"children\": [\n                            {\n                                \"id\": \"Chapter02\",\n                                \"type\": \"Chapter\",\n                                \"path\": \"CP01,MP01,P01\",\n                                \"name\": \"Chapter02\",\n                                \"title\": \"Chapter02\",\n                                \"isFolder\": true,\n                                \"children\": [\n                                    {\n                                        \"id\": \"Page03\",\n                                        \"type\": \"Page\",\n                                        \"path\": \"CP01,MP02,P02,Chapter02\",\n                                        \"name\": \"Page03\",\n                                        \"title\": \"Page03\",\n                                        \"isFolder\": false,\n                                        \"children\": null,\n                                        \"assortment\": null\n                                    },\n                                    {\n                                        \"id\": \"Page02\",\n                                        \"type\": \"Page\",\n                                        \"path\": \"CP01,MP02,P02,Chapter02\",\n                                        \"name\": \"Page02\",\n                                        \"title\": \"Page02\",\n                                        \"isFolder\": false,\n                                        \"children\": null,\n                                        \"assortment\": null\n                                    }\n                                ],\n                                \"groupId\": null\n                            }\n                        ],\n                        \"assortment\": null,\n                        \"groupId\": [\n                            \"9c0e3e7c-7dc9-4bfa-90a4-d5912d640fe9\"\n                        ]\n                    }\n                ],\n                \"assortment\": null,\n                \"groupId\": [\n                    \"9c0e3e7c-7dc9-4bfa-90a4-d5912d640fe9\"\n                ]\n            },\n            {\n                \"id\": \"MP02\",\n                \"type\": \"MasterPublication\",\n                \"path\": \"-1,CP01\",\n                \"name\": \"MP02\",\n                \"title\": \"MP02\",\n                \"isFolder\": true,\n                \"children\": [\n                    {\n                        \"id\": \"P02\",\n                        \"type\": \"Publication\",\n                        \"path\": \"CP01,MP02\",\n                        \"name\": \"P02\",\n                        \"title\": \"P02\",\n                        \"isFolder\": true,\n                        \"children\": [\n                            {\n                                \"id\": \"Chapter01\",\n                                \"type\": \"Chapter\",\n                                \"path\": \"MP02,CP01,P02\",\n                                \"name\": \"Chapter01\",\n                                \"title\": \"Chapter01\",\n                                \"isFolder\": true,\n                                \"children\": [],\n                                \"assortment\": null\n                            }\n                        ],\n                        \"assortment\": null,\n                        \"groupId\": [\n                            \"37801368-1d75-4096-ab74-9b6663bf0512\"\n                        ]\n                    }\n                ],\n                \"assortment\": null,\n                \"groupId\": [\n                    \"37801368-1d75-4096-ab74-9b6663bf0512\"\n                ]\n            }\n        ],\n        \"assortment\": null,\n        \"groupId\": [\n            \"f879d2a2-2f54-4069-9590-820e649cbb93\",\n            \"9c0e3e7c-7dc9-4bfa-90a4-d5912d640fe9\",\n            \"37801368-1d75-4096-ab74-9b6663bf0512\"\n        ]\n    },\n    {\n        \"id\": \"CP02\",\n        \"type\": \"Campaign\",\n        \"path\": \"-1\",\n        \"name\": \"CP02\",\n        \"title\": \"CP02\",\n        \"isFolder\": true,\n        \"children\": [\n            {\n                \"id\": \"MP03\",\n                \"type\": \"MasterPublication\",\n                \"path\": \"-1,CP02\",\n                \"name\": \"MP03\",\n                \"title\": \"MP03\",\n                \"isFolder\": true,\n                \"children\": [\n                    {\n                        \"id\": \"P03\",\n                        \"type\": \"Publication\",\n                        \"path\": \"CP02,MP03\",\n                        \"name\": \"P03\",\n                        \"title\": \"P03\",\n                        \"isFolder\": true,\n                        \"children\": [\n                            {\n                                \"id\": \"Page01\",\n                                \"type\": \"Page\",\n                                \"path\": \"CP02,MP03,P03\",\n                                \"name\": \"Page01\",\n                                \"title\": \"Page01\",\n                                \"isFolder\": false,\n                                \"children\": null,\n                                \"assortment\": null\n                            }\n                        ],\n                        \"assortment\": null,\n                        \"groupId\": [\n                            \"a5c9cca9-34d3-4d96-ae78-2927c73b061b\"\n                        ]\n                    }\n                ],\n                \"assortment\": null,\n                \"groupId\": [\n                    \"a5c9cca9-34d3-4d96-ae78-2927c73b061b\"\n                ]\n            }\n        ],\n        \"assortment\": null,\n        \"groupId\": [\n            \"7c8e3991-e0f3-4e4d-8489-fb73cdde7d4a\",\n            \"a5c9cca9-34d3-4d96-ae78-2927c73b061b\"\n        ]\n    }\n]",
								new TypeReference<List<MultiDimensionalObject>>() {
								});

			}
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * Gets the types.
	 * 
	 * @param structure
	 *            the structure
	 * @return the types
	 */
	public String[] getTypes(String structure) {
		// TODO Auto-generated method stub
		return structure.split(DELIMETER);
	}

	/**
	 * Gets the all separated trees.
	 * 
	 * @param type
	 *            the type
	 * @return the all separated trees
	 */
	public List<MultiDimensionalObject> getAllSeparatedTrees(String type) {
		return repository.getDimensionsOfType(type);

	}

	/**
	 * Builds the tree for the given structure provided.
	 * 
	 * @param root
	 *            the root
	 * @param orderTypes
	 *            the order types
	 * @param groupIdsRequiredForCurrentIteration
	 *            the group ids required for current iteration
	 */
	public void buildTreeForRootNode(IMultiDimensionalObject root,
			String[] orderTypes,
			List<String> groupIdsRequiredForCurrentIteration) {
		List<String> groupIds = null;
		IMultiDimensionalObject currentRoot = root;
		if (groupIdsRequiredForCurrentIteration == null) {
			groupIds = currentRoot.getGroupId();
		} else {
			groupIds = intersectGroupIds(currentRoot.getGroupId(),
					groupIdsRequiredForCurrentIteration);

		}
		String[] typesOfDimensions = skipFirstOrderType(orderTypes);
		if (typesOfDimensions.length <= 0)
			return;
		List<MultiDimensionalObject> childrenOfCurrentLevel = getAllChildrenOfCurrentRoot(
				groupIds, typesOfDimensions[0]);

		currentRoot.setChildren(childrenOfCurrentLevel);

		for (IMultiDimensionalObject child : childrenOfCurrentLevel) {

			child.setPath(removeMinusOne(currentRoot.getPath()) + ","
					+ currentRoot.getName());

			buildTreeForRootNode(child, typesOfDimensions, groupIds);

		}

	}

	protected String removeMinusOne(String path) {
		path = path.startsWith("-1") && path.length() > 2 ? path.substring(3)
				: path;

		return path;
	}

	/**
	 * Gets the all children of current root.
	 * 
	 * @param groupIds
	 *            the group ids
	 * @param type
	 *            the type
	 * @return the all children of current root
	 */
	public List<MultiDimensionalObject> getAllChildrenOfCurrentRoot(
			List<String> groupIds, String type) {
		return repository.getDimensionsBy(type, groupIds);
	}

	/**
	 * Skip first order type.
	 * 
	 * @param orderTypes
	 *            the order types
	 * @return the string[]
	 */
	private String[] skipFirstOrderType(String[] orderTypes) {
		utils = new ArrayUtils();
		orderTypes = utils.skip(orderTypes, 1);
		return orderTypes;
	}

	/**
	 * Intersect group ids.
	 * 
	 * @param groupIds
	 *            the group ids
	 * @param groupIdsRequiredForCurrentIteration
	 *            the group ids required for current iteration
	 * @return the list
	 */
	private List<String> intersectGroupIds(List<String> groupIds,
			List<String> groupIdsRequiredForCurrentIteration) {
		utils = new ArrayUtils();

		return utils
				.intersection(groupIds, groupIdsRequiredForCurrentIteration);

	}

	public static void main(String[] args) throws JsonParseException,
			JsonMappingException, IOException {

		ITreeBuilder builder = new SlicingDicingMocks(new DimensionRepository(
				new FileUtils(), new InMemoryDimensionGroup(
						new RedisRepository(null)), null, null));
		System.out.println(builder
				.buildTree("MasterPublication-Campaign-Publication"));

	}

}
