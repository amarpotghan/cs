package app.cs.impl.model;

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
	private String path;

	/** The name. */
	private String name;

	/** The title. */
	private String title;

	/** The is folder.TODO */
	private boolean isFolder;

	private List<Assortment> assortments;

	private List<MultiDimensionalObject> children;

	private List<Product> products;

	private DimensionInfo dimensionInfo;

	public DimensionInfo getDimensionInfo() {
		return dimensionInfo;
	}

	public void setDimensionInfo(DimensionInfo dimensionInfo) {
		this.dimensionInfo = dimensionInfo;
	}

	/**
	 * Instantiates a new content object.
	 */
	public MultiDimensionalObject() {
	}

	public List<Assortment> getAssortments() {
		return assortments;
	}

	public void setAssortments(List<Assortment> assortments) {
		this.assortments = assortments;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public boolean getIsFolder() {
		return isFolder;
	}

	@Override
	public void setIsFolder(boolean isFolder) {
		this.isFolder = isFolder;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	/** The group ids. */
	private List<String> groupIds;

	public List<Assortment> getAssortment() {
		return assortments;
	}

	public void setAssortment(List<Assortment> assortment) {
		this.assortments = assortment;
	}

	/** The children. */
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

	@Override
	public void setGroupId(List<String> groupId) {
		this.groupIds = groupId;
	}

	@Override
	public List<MultiDimensionalObject> getChildren() {
		return children;
	}

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
			boolean isFolder) {
		this.id = name;
		this.name = name;
		this.title = name;
		this.type = type;
		this.path = path;
		this.isFolder = isFolder;

	}

	@Override
	public String getPath() {
		return path;
	}

	@Override
	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getObjectKey() {
		return "DIMENSION";
	}

	@Override
	public String getKey() {
		return getId();
	}

	@Override
	public String toString() {
		return "DimensionModel [id=" + id + ", type=" + type + ", path=" + path
				+ ", title=" + title + ", name=" + name + ", groupIds="
				+ groupIds + ", children=" + children + "]";
	}

	@Override
	public boolean isRoot() {
		return path == "-1" ? true : false;
	}

	@Override
	public void addToGroupId(String groupId) {
		if (groupIds == null) {
			groupIds = new ArrayList<String>();
			groupIds.add(groupId);
		} else {

			groupIds.add(groupId);
		}

	}

	@Override
	public boolean hasChildren() {
		return this.children == null ? false : true;
	}

	@Override
	public boolean isPage() {
		return type == PAGE ? true : false;
	}

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

	@Override
	public void addAssortment(Assortment assortment) {
		List<Assortment> newChildren;
		if (this.assortments == null) {
			newChildren = new ArrayList<Assortment>();
			newChildren.add(assortment);
			this.assortments = newChildren;

		} else {
			this.assortments.add(assortment);
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

		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;

		MultiDimensionalObject other = (MultiDimensionalObject) obj;
		if (id.equals(other.id))
			return true;
		else
			return false;

	}
}
