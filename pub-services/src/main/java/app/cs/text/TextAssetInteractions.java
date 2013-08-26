/*package app.cs.text;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.text.TextAssetRepository;

@Component
public class TextAssetInteractions {
	
	private TextAssetRepository textAssetRepository;
	
	@Autowired
	public TextAssetInteractions(TextAssetRepository textAssetRepository) {
		this.textAssetRepository = textAssetRepository;
	}

	public String getAssets(String id) {
		return textAssetRepository.getAssetsFor(id);

	}

}*/