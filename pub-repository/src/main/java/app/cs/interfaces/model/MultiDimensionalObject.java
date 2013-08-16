package app.cs.interfaces.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import app.cs.interfaces.dimension.IMultiDimensionalObject;

import com.cs.data.api.core.GenericDomain;

/**
 * The Class ContentObject. TODO implement interface TODO remove all the
 * annotation from class file TODO rename to dimensionObject
 */
@Component
public class MultiDimensionalObject implements Serializable, GenericDomain,
		IMultiDimensionalObject {

	/** The page. */
	private final String PAGE = "page";

	/** The id. */
	private String id;

	/** The type. */
	private String type;

	/** The path. TODO */
	private  String path;

	/** The name. */
	private String name;

	/** The title. */
	private String title;

	/** The is folder.TODO */
	private  String isFolder;

	/**
	 * Instantiates a new content object.
	 */
	public MultiDimensionalObject() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cs.data.business.model.IMultiDimensionalObject#getIsFolder()
	 */
	@Override
	public String getIsFolder() {
		return isFolder;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cs.data.business.model.IMultiDimensionalObject#setIsFolder(java.lang
	 * .String)
	 */
	@Override
	public void setIsFolder(String isFolder) {
		this.isFolder = isFolder;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cs.data.business.model.IMultiDimensionalObject#getTitle()
	 */
	@Override
	public String getTitle() {
		return title;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cs.data.business.model.IMultiDimensionalObject#setTitle(java.lang
	 * .String)
	 */
	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	/** The group ids. */
	private List<String> groupIds;

	/** The children. */
	private List<MultiDimensionalObject> children;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cs.data.business.model.IMultiDimensionalObject#getGroupId()
	 */
	@Override
	public List<String> getGroupId() {
		return groupIds;
	}

	/**
	 * Instantiates a new content object.
	 * 
	 * @param id
	 *            the id
	 * @param type
	 *            the type
	 * @param title
	 *            the title
	 * @param name
	 *            the name
	 * @param path
	 *            the path
	 */
	public MultiDimensionalObject(String id, String type, String title,
			String name, String path) {
		this.id = id;
		this.type = type;
		this.name = name;
		this.title = title;
		this.path = path;
	}

	/**
	 * Instantiates a new content object.
	 * 
	 * @param id
	 *            the id
	 * @param type
	 *            the type
	 * @param path
	 *            the path
	 * @param name
	 *            the name
	 * @param groupId
	 *            the group id
	 * @param children
	 *            the children
	 */
	public MultiDimensionalObject(String id, String type, String path,
			String name, List<String> groupId,
			List<MultiDimensionalObject> children) {
		super();
		this.id = id;
		this.type = type;
		this.path = path;
		this.name = name;
		this.groupIds = groupId;
		this.children = children;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cs.data.business.model.IMultiDimensionalObject#setGroupId(java.util
	 * .List)
	 */
	@Override
	public void setGroupId(List<String> groupId) {
		this.groupIds = groupId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cs.data.business.model.IMultiDimensionalObject#getChildren()
	 */
	@Override
	public List<MultiDimensionalObject> getChildren() {
		return children;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cs.data.business.model.IMultiDimensionalObject#setChildren(java.util
	 * .List)
	 */
	@Override
	public void setChildren(List<MultiDimensionalObject> children) {
		this.children = children;
	}

	/**
	 * Instantiates a new content object.
	 * 
	 * @param name
	 *            the name
	 * @param type
	 *            the type
	 * @param path
	 *            the path
	 * @param isFolder
	 *            the is folder
	 */
	public MultiDimensionalObject(String name, String type, String path,
			String isFolder) {
		this.id = name;
		this.name = name;
		this.title = name;
		this.type = type;
		this.path = path;
		this.isFolder = isFolder;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cs.data.business.model.IMultiDimensionalObject#getPath()
	 */
	@Override
	public String getPath() {
		return path;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cs.data.business.model.IMultiDimensionalObject#setPath(java.lang.
	 * String)
	 */
	@Override
	public void setPath(String path) {
		this.path = path;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cs.data.business.model.IMultiDimensionalObject#getType()
	 */
	@Override
	public String getType() {
		return type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cs.data.business.model.IMultiDimensionalObject#setType(java.lang.
	 * String)
	 */
	@Override
	public void setType(String type) {
		this.type = type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cs.data.business.model.IMultiDimensionalObject#getId()
	 */
	@Override
	public String getId() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cs.data.business.model.IMultiDimensionalObject#setId(java.lang.String
	 * )
	 */
	@Override
	public void setId(String id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cs.data.business.model.IMultiDimensionalObject#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cs.data.business.model.IMultiDimensionalObject#setName(java.lang.
	 * String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cs.data.core.GenericDomain#getObjectKey()
	 */
	@Override
	public String getObjectKey() {
		// TODO Auto-generated method stub
		return "DIMENSION";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cs.data.core.GenericDomain#getKey()
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cs.data.business.model.IMultiDimensionalObject#getKey()
	 */
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DimensionModel [id=" + id + ", type=" + type + ", path=" + path
				+ ", title=" + title + ", name=" + name + ", groupIds="
				+ groupIds + ", children=" + children + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cs.data.business.model.IMultiDimensionalObject#isRoot()
	 */
	@Override
	public boolean isRoot() {
		return path == "-1" ? true : false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cs.data.business.model.IMultiDimensionalObject#addToGroupId(java.
	 * lang.String)
	 */
	@Override
	public void addToGroupId(String groupId) {
		if (groupIds == null) {
			groupIds = new ArrayList<String>();
			groupIds.add(groupId);
		} else {

			groupIds.add(groupId);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cs.data.business.model.IMultiDimensionalObject#hasChildren()
	 */
	@Override
	public boolean hasChildren() {
		return this.children == null ? false : true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.cs.data.business.model.IMultiDimensionalObject#isPage()
	 */
	@Override
	public boolean isPage() {
		return type == PAGE ? true : false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cs.data.business.model.IMultiDimensionalObject#addchild(com.cs.data
	 * .business.model.MultiDimensionalObject)
	 */
	@Override
	public void addchild(MultiDimensionalObject contentObject) {
		List<MultiDimensionalObject> newChildren;
		if (this.children == null) {
			newChildren = new ArrayList<MultiDimensionalObject>();
			newChildren.add(contentObject);
			this.children = newChildren;

		} else {
			this.children.add(contentObject);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cs.data.business.model.IMultiDimensionalObject#removeChild(com.cs
	 * .data.business.model.IMultiDimensionalObject)
	 */
	@Override
	public void removeChild(IMultiDimensionalObject contentObject) {
		if (children != null) {
			this.children.remove(contentObject);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((PAGE == null) ? 0 : PAGE.hashCode());
		result = prime * result
				+ ((children == null) ? 0 : children.hashCode());
		result = prime * result
				+ ((groupIds == null) ? 0 : groupIds.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((isFolder == null) ? 0 : isFolder.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;

		MultiDimensionalObject other = (MultiDimensionalObject) obj;
		if (id.equals(other.id))
			return true;
		if (PAGE == null) {
			if (other.PAGE != null)
				return false;
		} else if (!PAGE.equals(other.PAGE))
			return false;
		if (children == null) {
			if (other.children != null)
				return false;
		} else if (!children.equals(other.children))
			return false;
		if (groupIds == null) {
			if (other.groupIds != null)
				return false;
		} else if (!groupIds.equals(other.groupIds))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (isFolder == null) {
			if (other.isFolder != null)
				return false;
		} else if (!isFolder.equals(other.isFolder))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
}
