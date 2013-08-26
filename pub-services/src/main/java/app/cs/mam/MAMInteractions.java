package app.cs.mam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.pim.PIMRepository;

@Component
public class MAMInteractions {
	
	private MAMRepository mamRepository;
	
	@Autowired
	public MAMInteractions(MAMRepository mamRepository) {
		this.mamRepository = mamRepository;
	}

	public String getAssets(String id) {
		return mamRepository.getAssetsFor(id);

	}

}