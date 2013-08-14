package app.cs.data.business.inmemory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.data.business.api.inmemory.IInMemoryDimensionGroup;
import app.cs.data.business.api.model.IMultiDimensionalObject;

import com.cs.data.api.core.nosql.redis.InMemoryNoSqlRepository;


/**
 * The Class DimensionGroupCache.
 */
@Component
public class InMemoryDimensionGroup implements IInMemoryDimensionGroup {

	/** The no sql template for redis. */
	private InMemoryNoSqlRepository inMemoryNosqlRepository;

	/**
	 * Instantiates a new dimension group cache.
	 *
	 * @param noSqlTemplateForRedis the no sql template for redis
	 */
	@Autowired
	public InMemoryDimensionGroup(InMemoryNoSqlRepository noSqlTemplateForRedis) {
		this.inMemoryNosqlRepository = noSqlTemplateForRedis;

	}

	/* (non-Javadoc)
	 * @see com.cs.data.business.inmemory.IInMemoryDimensionGroup#getDimensionGroupIdFor(java.lang.String)
	 */
	@Override
	public String getDimensionGroupIdFor(String path) {
		// TODO Auto-generated method stub
		return inMemoryNosqlRepository.get(path);
	}

	/* (non-Javadoc)
	 * @see com.cs.data.business.inmemory.IInMemoryDimensionGroup#updateCache(com.cs.data.business.api.model.IMultiDimensionalObject, java.lang.String)
	 */
	@Override
	public void updateCache(IMultiDimensionalObject dimension, String groupId) {

		delete(dimension);
		inMemoryNosqlRepository.set(
				dimension.getPath().concat("," + dimension.getName()), groupId);
	}

	/**
	 * Delete.
	 *
	 * @param dimension the dimension
	 */
	private void delete(IMultiDimensionalObject dimension) {
		// TODO Auto-generated method stub

		inMemoryNosqlRepository.delete(dimension.getPath());

	}

	/* (non-Javadoc)
	 * @see com.cs.data.business.inmemory.IInMemoryDimensionGroup#addNewGroup(com.cs.data.business.api.model.IMultiDimensionalObject, java.lang.String)
	 */
	@Override
	public void addNewGroup(IMultiDimensionalObject dimension, String groupId) {
		// TODO Auto-generated method stub
		if (dimension.isRoot()) {
			inMemoryNosqlRepository.set(dimension.getName(), groupId);
		} else {
			inMemoryNosqlRepository.set(
					dimension.getPath().concat("," + dimension.getName()),
					groupId);
		}

	}

	/* (non-Javadoc)
	 * @see com.cs.data.business.inmemory.IInMemoryDimensionGroup#ifGroupIdExistsFor(java.lang.String)
	 */
	@Override
	public boolean ifGroupIdExistsFor(String path) {
		return getDimensionGroupIdFor(path) == null ? false : true;

	}

	/* (non-Javadoc)
	 * @see com.cs.data.business.inmemory.IInMemoryDimensionGroup#getAllGroups()
	 */
	@Override
	public List<String> getAllGroups() {
		// TODO Auto-generated method stub
		return null;
	}

}
