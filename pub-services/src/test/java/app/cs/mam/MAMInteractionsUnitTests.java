package app.cs.mam;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.cs.impl.mam.MAMRepository;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MAMInteractionsUnitTests {

	private MAMInteractions mamInteractions;

	@Mock
	private MAMRepository mamRepository;

	private String id = "62";

	@Before
	public void setUp() {
		mamInteractions = new MAMInteractions(mamRepository);
	}

	@Test
	public void itShouldGetAssetsFromMAMRepository() {

		String expectedAssetList = "test assets";
		// when
		when(mamRepository.getAssetsFor(id)).thenReturn(expectedAssetList);
		String assetList = mamInteractions.getAssets(id);

		// then
		verify(mamRepository).getAssetsFor(id);
		assertThat(assetList).isEqualTo(expectedAssetList);

	}
}