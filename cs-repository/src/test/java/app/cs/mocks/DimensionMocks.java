package app.cs.mocks;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.impl.delegate.factory.DomainFactory;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.interfaces.dimension.IDimensionRepository;
import app.cs.interfaces.dimension.IInMemoryDimensionGroup;
import app.cs.utils.FileUtils;

import com.cs.data.api.core.GenericDomain;
import com.cs.data.api.core.nosql.mongodb.NoSqlRepository;

/**
 * The Class DimensionRepository
 */
@Component
public class DimensionMocks {

	/** The file utils. */
	private FileUtils fileUtils;

	/** The group cache. */
	private IInMemoryDimensionGroup groupCache;

	/** The no sql templatefor mongo. */
	private NoSqlRepository noSqlRepository;

	private DomainFactory factory;

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
	public DimensionMocks(FileUtils fileUtils,
			IInMemoryDimensionGroup groupCache,
			NoSqlRepository noSqlRepository, DomainFactory factory) {

		this.fileUtils = fileUtils;
		this.groupCache = groupCache;
		this.noSqlRepository = noSqlRepository;
		this.factory = factory;
	}

	public String createDimension(MultiDimensionalObject dimension) {

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
		String[] paths = path.split(",");
		for (String singlePath : paths) {
			noSqlRepository.updateById(singlePath, FIELDTOUPDATE, groupId,
					MultiDimensionalObject.class);
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

	public GenericDomain getDomain(String type) {

		return factory.getDomainObject(type);
	}

	public String getAllDimensions() throws IOException, URISyntaxException {
		return fileUtils.getFileContents("dimensions.json");
	}

	public List<MultiDimensionalObject> getDimensions() {

		return noSqlRepository.findAll(MultiDimensionalObject.class);
	}

	public List<MultiDimensionalObject> getDimensionsOfType(String type) {
		return noSqlRepository.getObjectsBy(TYPE, type,
				MultiDimensionalObject.class);
	}

	public List<MultiDimensionalObject> getDimensionsBy(String type2,
			List<String> groupIds) {
		return noSqlRepository.getObjectForAndCriteria(TYPE, type2, GROUPIDS,
				groupIds, MultiDimensionalObject.class);

	}

}
