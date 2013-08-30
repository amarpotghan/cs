package app.cs.mocks;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.cs.interfaces.mam.AssetsRepository;
import app.cs.interfaces.pim.IPIMRepository;

import com.cs.data.webservices.rest.RestClient;

@Component
public class PIMMocks implements AssetsRepository, IPIMRepository {

	private static final String CHARSET = "ISO-8859-1,utf-8;q=0.7,*;q=0.3";
	private static final String ACCEPT_CHARSET = "Accept-Charset";
	private static final String ACCEPTEDTYPES = "*/*";
	private static final String ACCEPT = "Accept";
	private static final String XML_HTTP_REQUEST = "XMLHttpRequest";
	private static final String X_REQUESTED_WITH = "X-Requested-With";
	private static final String USER_AGENT_INFO = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.63 Safari/537.31";
	private static final String USER_AGENT = "User-Agent";
	private static final String HOSTIP = "192.168.135.108";
	private static final String HOST = "Host";
	private static final String LANGUAGE = "en-US,en;q=0.8";
	private static final String ACCEPT_LANGUAGE = "Accept-Language";
	private final String BASE_URL = "http://192.168.135.108/CS13.0/admin/rest/pim/list/";
	private RestClient client;

	@Autowired
	public PIMMocks(RestClient client) {
		this.client = client;

	}

	@Override
	public String getAssetsFor(String id) {

		return "{\"62\":{\"id\":\"62\",\"label\":\"Media Player\",\"type\":\"item\",\"image\":\"http:\\/\\/192.168.135.108\\/CS13.0\\/admin\\/ImageServer.php?ID=a8a1ba62@CSLive&class=Pdmarticle&rand=9e280543f231782ff0ceda6ad83bc0b2&width=32&height=48\",\"isfolder\":true,\"description\":\"89,00, Blue\",\"service\":\"pim\"},\"91\":{\"id\":\"91\",\"label\":\"Books\",\"type\":\"item\",\"image\":\"http:\\/\\/192.168.135.108\\/CS13.0\\/admin\\/ImageServer.php?ID=f85e6a91@CSLive&class=Pdmarticle&rand=8ec636c4d5f14e6b6fa73eb0952bbc6f&width=32&height=48\",\"isfolder\":true,\"description\":\"24,80, white\",\"service\":\"pim\"},\"63\":{\"id\":\"63\",\"label\":\"Accessories\",\"type\":\"item\",\"image\":\"http:\\/\\/192.168.135.108\\/CS13.0\\/admin\\/ImageServer.php?ID=6198aa63@CSLive&class=Pdmarticle&rand=8ec636c4d5f14e6b6fa73eb0952bbc6f&width=32&height=48\",\"isfolder\":true,\"service\":\"pim\"}}";

	}

	private String formUrl(String id) {
		return id == null || id == "" ? BASE_URL : BASE_URL + id;
	}

	@Override
	public String getSearchResults(String searchQuery) {
		// TODO Auto-generated method stub
		return null;
	}

}
