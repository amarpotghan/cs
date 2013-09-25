package app.cs.model.response;

import java.io.Serializable;
import java.util.List;

import app.cs.impl.model.MultiDimensionalObject;

public class TreeResponse implements ResponseModel,Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public TreeResponse(List<MultiDimensionalObject> tree) {
		super();
		this.tree = tree;
	}

	List<MultiDimensionalObject> tree;

	public List<MultiDimensionalObject> getTree() {
		return tree;
	}

	public void setTree(List<MultiDimensionalObject> tree) {
		this.tree = tree;
	}
}
