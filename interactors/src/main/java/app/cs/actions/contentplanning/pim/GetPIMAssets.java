package app.cs.actions.contentplanning.pim;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.impl.pim.PIMRepository;
import app.cs.model.response.ResponseModel;
import app.cs.model.response.StringResponse;

@Component
public class GetPIMAssets {

	private PIMRepository pimRepository;

	@Autowired
	public GetPIMAssets(PIMRepository pimRepository) {
		this.pimRepository = pimRepository;
	}

	public ResponseModel execute(String id) {
		return new StringResponse(pimRepository.getAssetsFor(id));

	}

}
