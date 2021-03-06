package app.cs.interfaces.dimension;

import java.util.List;


public interface IInMemoryDimensionGroup {

	/**
	 * Gets the dimension group id for the given path.
	 *
	 * @param path the path
	 * @return the dimension group id for
	 */
	public abstract String getDimensionGroupIdFor(String path);

	/**
	 * Update cache for the given group taking the given dimension and groupid.
	 *
	 * @param dimension the dimension
	 * @param groupId the group id
	 */
	public abstract void updateCache(IMultiDimensionalObject dimension,
			String groupId);

	/**
	 * Adds the new group taking the groupId.
	 *
	 * @param dimension the dimension
	 * @param groupId the group id
	 */
	public abstract void addNewGroup(IMultiDimensionalObject dimension,
			String groupId);

	/**
	 * Checks if the Given Dimension Exists
	 *
	 * @param path the path
	 * @return true, if successful
	 */
	public abstract boolean ifGroupIdExistsFor(String path);

	/**
	 * Gets the all groups.
	 *
	 * @return the all groups
	 */
	public abstract List<String> getAllGroups();

}