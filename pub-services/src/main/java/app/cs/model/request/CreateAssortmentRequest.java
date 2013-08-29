package app.cs.model.request;

import app.cs.impl.model.Assortment;

public class CreateAssortmentRequest implements RequestModel {

	private Assortment assortment;
	private String newPath;

	public Assortment getAssortment() {
		return assortment;
	}

	public void setAssortment(Assortment assortment) {
		this.assortment = assortment;
	}

	public String getNewPath() {
		return newPath;
	}

	public void setNewPath(String newPath) {
		this.newPath = newPath;
	}

}
