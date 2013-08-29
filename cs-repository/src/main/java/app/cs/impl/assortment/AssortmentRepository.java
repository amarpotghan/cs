package app.cs.impl.assortment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.impl.helper.Finder;
import app.cs.impl.model.Assortment;
import app.cs.impl.model.MultiDimensionalObject;
import app.cs.interfaces.assortment.IAssortmentRepository;

import com.cs.data.api.core.nosql.mongodb.NoSqlRepository;

@Component
public class AssortmentRepository implements IAssortmentRepository {
	private NoSqlRepository noSqlRepository;

	private Finder finder;

	@Autowired
	public AssortmentRepository(NoSqlRepository noSqlRepository, Finder finder) {
		this.noSqlRepository = noSqlRepository;
		this.finder = finder;
	}

	@Override
	public void save(Assortment assortment, String path) {

		MultiDimensionalObject publication = getParentPublication(path);
		String parentId = finder.getParentId(path);
		MultiDimensionalObject parent = finder.find(publication, parentId);
		parent.addAssortment(assortment);
		noSqlRepository.save(parent);

	}

	@Override
	public Assortment getAssortmentObject() {
		return new Assortment();
	}

	@Override
	public MultiDimensionalObject getPublication(String path) {

		MultiDimensionalObject publication = getParentPublication(path);
		return publication;

	}

	@Override
	public MultiDimensionalObject getParentPublication(String path) {
		return noSqlRepository.getObjectByKey(finder.getPublicationId(path),
				MultiDimensionalObject.class);
	}

	@Override
	public void copy(Assortment assortment, String newPath) {

		MultiDimensionalObject publication = getParentPublication(newPath);
		String parentId = finder.getParentId(newPath);
		MultiDimensionalObject parent = finder.find(publication, parentId);
		parent.addAssortment(assortment);
		noSqlRepository.save(parent);

	}

}
