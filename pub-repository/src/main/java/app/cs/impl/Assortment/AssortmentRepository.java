package app.cs.impl.assortment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.helper.Finder;
import app.cs.model.Assortment;
import app.cs.model.MultiDimensionalObject;

import com.cs.data.api.core.nosql.mongodb.NoSqlRepository;
import com.cs.data.core.nosql.mongodb.MongoRepository;

@Component
public class AssortmentRepository {
	private NoSqlRepository noSqlRepository;

	private Finder finder;

	@Autowired
	public AssortmentRepository(NoSqlRepository noSqlRepository, Finder finder) {
		this.noSqlRepository = noSqlRepository;
		this.finder = finder;
	}

	public void save(Assortment assortment, String path) {

		MultiDimensionalObject publication = getParentPublication(path);
		String parentId = finder.getParentId(path);
		MultiDimensionalObject parent = finder.find(publication, parentId);
		parent.addAssortment(assortment);
		noSqlRepository.save(parent);

	}

	public Assortment getAssortmentObject() {
		return new Assortment();
	}

	public MultiDimensionalObject getPublication(String path) {

		MultiDimensionalObject Pub = getParentPublication(path);
		return Pub;

	}

	public MultiDimensionalObject getParentPublication(String path) {
		return noSqlRepository.getObjectByKey(finder.getPublicationId(path),
				MultiDimensionalObject.class);
	}
	
	public void move(Assortment assortment, String newPath) {

		MultiDimensionalObject publication = getParentPublication(newPath);
		String parentId = finder.getParentId(newPath);
		MultiDimensionalObject parent = finder.find(publication, parentId);
		parent.addAssortment(assortment);
		noSqlRepository.save(parent);

	}

}
