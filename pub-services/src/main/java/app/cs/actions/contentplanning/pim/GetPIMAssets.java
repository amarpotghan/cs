package app.cs.actions.contentplanning.pim;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.impl.pim.PIMRepository;

@Component
public class GetPIMAssets {

	private PIMRepository pimRepository;

	@Autowired
	public GetPIMAssets(PIMRepository pimRepository) {
		this.pimRepository = pimRepository;
	}

	public String execute(String id) {
		return pimRepository.getAssetsFor(id);

	}

}
