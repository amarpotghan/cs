package app.cs.impl.mam;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.impl.mam.MAMRepository;
import app.cs.interfaces.mam.AssetsRepository;

import com.cs.data.webservices.rest.RestClient;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MAMRepositoryUnitTests {

	private AssetsRepository mamRepository;

	@Mock
	private RestClient restClient;

	private final String LIST_URL = "http://192.168.135.108/CS13.0/admin/rest/mam/list/";
	private final String SEARCH_URL = "http://192.168.135.108/CS13.0/admin/rest/mam/search/";

	@Before
	public void setup() {
		mamRepository = new MAMRepository(restClient);
	}

	@Test
	public void itShouldRetrieveAllMediaAssetsFromGivenSourceUrlIfIdIsProvided() {

		// given
		String id = "62";
		Map<String, String> headerParameters = new HashMap<String, String>();
		String result = "result";

		headerParameters.put("Accept-Language", "en-US,en;q=0.8");
		headerParameters.put("Host", "192.168.135.108");
		headerParameters
				.put("User-Agent",
						"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.63 Safari/537.31");
		headerParameters.put("X-Requested-With", "XMLHttpRequest");
		headerParameters.put("Accept", "*/*");
		headerParameters
				.put("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3");
		String url = LIST_URL + id;

		// when
		when(restClient.get(url, headerParameters)).thenReturn(result);
		String assets = mamRepository.getAssetsFor(id);

		// then

		verify(restClient).get(url, headerParameters);
		assertThat(assets).isEqualTo(result);
	}

	@Test
	public void itShouldRetrieveAllAssetsFromGivenSourceUrlIfIdIsNotProvided() {
		// given
		String id = null;
		Map<String, String> headerParameters = new HashMap<String, String>();
		String result = "result";

		headerParameters.put("Accept-Language", "en-US,en;q=0.8");
		headerParameters.put("Host", "192.168.135.108");
		headerParameters
				.put("User-Agent",
						"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.63 Safari/537.31");
		headerParameters.put("X-Requested-With", "XMLHttpRequest");
		headerParameters.put("Accept", "*/*");
		headerParameters
				.put("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3");
		String url = LIST_URL + id;

		// when
		when(restClient.get(LIST_URL, headerParameters)).thenReturn(result);
		String assets = mamRepository.getAssetsFor(id);

		// then

		verify(restClient).get(LIST_URL, headerParameters);
		assertThat(assets).isEqualTo(result);

	}

	@Test
	public void itShouldRetrieveSearchResults() {
		// given

		String searchQuery = "benchmark";
		String expectedResult = "expected";
		// when
		Map<String, String> headerParameters = new HashMap<String, String>();
		String result = "result";

		headerParameters.put("Accept-Language", "en-US,en;q=0.8");
		headerParameters.put("Host", "192.168.135.108");
		headerParameters
				.put("User-Agent",
						"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.63 Safari/537.31");
		headerParameters.put("X-Requested-With", "XMLHttpRequest");
		headerParameters.put("Accept", "*/*");
		headerParameters
				.put("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3");

		String url = SEARCH_URL + searchQuery;
		when(restClient.get(url, headerParameters)).thenReturn(expectedResult);

		String searchResults = mamRepository.getSearchResults(searchQuery);
		// then
		verify(restClient).get(url, headerParameters);
		assertThat(searchResults).isEqualTo(expectedResult);

	}

}