package app.cs.interfaces.assortment;

import app.cs.impl.model.Assortment;
import app.cs.impl.model.MultiDimensionalObject;

public interface IAssortmentRepository {

	public abstract void save(Assortment assortment, String path);

	public abstract Assortment getAssortmentObject();

	public abstract MultiDimensionalObject getPublication(String path);

	public abstract MultiDimensionalObject getParentPublication(String path);

	public abstract void copy(Assortment assortment, String newPath);

}