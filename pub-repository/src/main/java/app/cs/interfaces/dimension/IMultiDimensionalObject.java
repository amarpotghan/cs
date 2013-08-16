package app.cs.interfaces.dimension;

import java.util.List;

import app.cs.interfaces.model.MultiDimensionalObject;

public interface IMultiDimensionalObject {

	/**
	 * Gets the checks if is folder.
	 *
	 * @return the checks if is folder
	 */
	public abstract boolean getIsFolder();

	/**
	 * Sets the checks if is folder.
	 *
	 * @param isFolder the new checks if is folder
	 */
	public abstract void setIsFolder(boolean isFolder);

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public abstract String getTitle();

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public abstract void setTitle(String title);

	/**
	 * Gets the group id.
	 *
	 * @return the group id
	 */
	public abstract List<String> getGroupId();

	/**
	 * Sets the group id.
	 *
	 * @param groupId the new group id
	 */
	public abstract void setGroupId(List<String> groupId);

	/**
	 * Gets the children.
	 *
	 * @return the children
	 */
	public abstract List<MultiDimensionalObject> getChildren();

	/**
	 * Sets the children.
	 *
	 * @param children the new children
	 */
	public abstract void setChildren(List<MultiDimensionalObject> children);

	/**
	 * Gets the path.
	 *
	 * @return the path
	 */
	public abstract String getPath();

	/**
	 * Sets the path.
	 *
	 * @param path the new path
	 */
	public abstract void setPath(String path);

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public abstract String getType();

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public abstract void setType(String type);

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public abstract String getId();

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public abstract void setId(String id);

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public abstract String getName();

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public abstract void setName(String name);

	/* (non-Javadoc)
	 * @see com.cs.data.core.GenericDomain#getKey()
	 */
	public abstract String getKey();

	/**
	 * Checks if is root.
	 *
	 * @return true, if is root
	 */
	public abstract boolean isRoot();

	/**
	 * Adds the to group id.
	 *
	 * @param groupId the group id
	 */
	public abstract void addToGroupId(String groupId);

	/**
	 * Checks for children.
	 *
	 * @return true, if successful
	 */
	public abstract boolean hasChildren();

	/**
	 * Checks if is page.
	 *
	 * @return true, if is page
	 */
	public abstract boolean isPage();

	/**
	 * Addchild.
	 *
	 * @param contentObject the content object
	 */
	public abstract void addchild(MultiDimensionalObject contentObject);

	/**
	 * Removes the child.
	 *
	 * @param contentObject the content object
	 */
	public abstract void removeChild(IMultiDimensionalObject contentObject);

}