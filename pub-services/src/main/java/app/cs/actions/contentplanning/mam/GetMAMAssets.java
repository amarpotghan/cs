package app.cs.actions.contentplanning.mam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.interfaces.assets.AssetsRepository;

@Component
public class GetMAMAssets {

	private AssetsRepository mamRepository;

	@Autowired
	public GetMAMAssets(AssetsRepository mamRepository) {
		this.mamRepository = mamRepository;
	}

	public String execute(String id) {
		return mamRepository.getAssetsFor(id);

	}

}