package app.cs.impl.Assortment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cs.data.core.nosql.mongodb.MongoRepository;

import app.cs.interfaces.model.Assortment;

@Component
public class AssortmentRepository {
	private MongoRepository mongoRepository;

	@Autowired
	public AssortmentRepository(MongoRepository mongoRepository) {
		this.mongoRepository = mongoRepository;
	}

	public void save(Assortment assortment, String path) {

		mongoRepository.save(assortment);

	}

	public Assortment getAssortmentObject() {
		return new Assortment();
	}

}
