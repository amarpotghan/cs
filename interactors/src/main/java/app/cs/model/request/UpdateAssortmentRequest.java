package app.cs.model.request;

import org.springframework.stereotype.Component;

import app.cs.impl.model.Assortment;

@Component
public class UpdateAssortmentRequest implements RequestModel {

	private Assortment assortment;
	private String path;

	public Assortment getAssortment() {
		return assortment;
	}

	public void setAssortment(Assortment assortment) {
		this.assortment = assortment;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	
}
