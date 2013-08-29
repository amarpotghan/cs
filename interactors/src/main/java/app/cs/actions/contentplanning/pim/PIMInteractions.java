package app.cs.actions.contentplanning.pim;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.impl.pim.PIMRepository;

@Component
public class PIMInteractions {

	private PIMRepository pimRepository;

	@Autowired
	public PIMInteractions(PIMRepository pimRepository) {
		this.pimRepository = pimRepository;
	}

	public String getProducts(String id) {
		return pimRepository.getAssetsFor(id);

	}

}
