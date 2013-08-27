package app.cs.actions.contentplanning.mam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.interfaces.assets.AssetsRepository;
import app.cs.model.response.ResponseModel;
import app.cs.model.response.StringResponse;

@Component
public class GetMAMAssets {

	private AssetsRepository mamRepository;

	@Autowired
	public GetMAMAssets(AssetsRepository mamRepository) {
		this.mamRepository = mamRepository;
	}

	public ResponseModel execute(String id) {
		return new StringResponse(mamRepository.getAssetsFor(id));

	}

}