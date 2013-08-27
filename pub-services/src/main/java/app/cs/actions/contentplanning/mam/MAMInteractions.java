package app.cs.actions.contentplanning.mam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.interfaces.assets.AssetsRepository;

@Component
public class MAMInteractions {
	
	private AssetsRepository mamRepository;
	
	@Autowired
	public MAMInteractions(AssetsRepository mamRepository) {
		this.mamRepository = mamRepository;
	}

	public String getAssets(String id) {
		return mamRepository.getAssetsFor(id);

	}

}