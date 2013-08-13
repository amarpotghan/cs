package app.cs.repository;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.cs.data.api.core.nosql.NoSqlRepository;

import app.cs.inmemory.InMemoryDimensionGroup;
import app.cs.model.HierarchicalObject;
import app.cs.repository.api.IDimensionRepository;
import app.cs.utils.FileUtils;

/**
 * The Class DimensionRepository.
 * TODO remove out all annaotation from classes
 */
@Component
public class DimensionRepository implements IDimensionRepository {

	/** The file utils. */
	private FileUtils fileUtils;

	/** The group cache. */
	private InMemoryDimensionGroup groupCache;

	/** The no sql templatefor mongo. */
	private NoSqlRepository noSqlRepository;

	/** The fieldtoupdate. */
	private final String FIELDTOUPDATE = "groupIds";

	/** The type. */
	private final String TYPE = "type";

	/** The groupids. */
	private final String GROUPIDS = "groupIds";

	/**
	 * Instantiates a new dimension repository.
	 * 
	 * @param fileUtils
	 *            the file utils
	 * @param groupCache
	 *            the group cache
	 * @param noSqlTemplateforMongo
	 *            the no sql templatefor mongo
	 */
	@Autowired
	public DimensionRepository(FileUtils fileUtils,
			InMemoryDimensionGroup groupCache, NoSqlRepository noSqlRepository) {

		this.fileUtils = fileUtils;
		this.groupCache = groupCache;
		this.noSqlRepository = noSqlRepository;
	}

	/* (non-Javadoc)
	 * @see app.cs.repository.IDimensionRepository#createDimension(app.cs.model.ContentObject)
	 */
	@Override
	public String createDimension(HierarchicalObject dimension) {
		String groupId = getDimensionGroupId(dimension.getPath());
		if (groupCache.ifGroupIdExistsFor(dimension.getPath())) {
			dimension.addToGroupId(groupId);
			dimension.setChildren(null);
			noSqlRepository.save(dimension);
			groupCache.updateCache(dimension, groupId);
		} else {
			groupId = UUID.randomUUID().toString();
			groupCache.addNewGroup(dimension, groupId);

			updateGroupIdForAllAncestor(dimension.getPath(), groupId);
			dimension.addToGroupId(groupId);
			dimension.setChildren(null);
			noSqlRepository.save(dimension);
		}

		return dimension.getId();
	}

	/**
	 * Update group id for all ancestor.
	 * 
	 * @param path
	 *            the path
	 * @param groupId
	 *            the group id
	 */
	private void updateGroupIdForAllAncestor(String path, String groupId) {
		int count = 0;
		String[] paths = path.split(",");
		for (String singlePath : paths) {
			noSqlRepository.updateById(singlePath, FIELDTOUPDATE, groupId,
					HierarchicalObject.class);
		}

	}

	
	/**
	 * Gets the dimension group id.
	 * 
	 * @param path
	 *            the path
	 * @return the dimension group id
	 */
	private String getDimensionGroupId(String path) {

		return groupCache.getDimensionGroupIdFor(path);
	}

	/* (non-Javadoc)
	 * @see app.cs.repository.IDimensionRepository#getAllDimensions()
	 */
	@Override
	public String getAllDimensions() throws IOException, URISyntaxException {
		// TODO Auto-generated method stub
		return fileUtils.getFileContents("dimensions.json");
	}

	/* (non-Javadoc)
	 * @see app.cs.repository.IDimensionRepository#getDimensions()
	 */
	@Override
	public List<HierarchicalObject> getDimensions() {

		return noSqlRepository.findAll(HierarchicalObject.class);
	}

	/* (non-Javadoc)
	 * @see app.cs.repository.IDimensionRepository#getDimensionsOfType(java.lang.String)
	 */
	@Override
	public List<HierarchicalObject> getDimensionsOfType(String type) {
		// TODO Auto-generated method stub
		return noSqlRepository.getObjectsBy(TYPE, type, HierarchicalObject.class);
	}

	/* (non-Javadoc)
	 * @see app.cs.repository.IDimensionRepository#getDimensionsBy(java.lang.String, java.util.List)
	 */
	@Override
	public List<HierarchicalObject> getDimensionsBy(String type2,
			List<String> groupIds) {
		return noSqlRepository.getObjectForAndCriteria(TYPE, type2, GROUPIDS,
				groupIds, HierarchicalObject.class);

	}

}
