package com.neueda.url.controllertest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.neueda.url.controller.UrlController;
import com.neueda.url.service.UrlService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UrlControllerTest {
	
	@Mock
	UrlService mockUrlService;
	
	@InjectMocks
	UrlController urlController;
	
	@Test
	public void urlConversionTest() {
		String TINYURL = "http://bit.ly/";
		String shortUrl = "https://www.google.com/search?q=cobertura+configur"
				+ "ation+with+executions+in+spring+boot&rlz=1C1CHBF_enIN934IE936"
				+ "&oq=cobertura+configuration+with+executions&"
				+ "aqs=chrome.1.69i57j33i160.10790j0j7&sourceid=chrome&ie=UTF-8";
		when(mockUrlService.convertToShortUrl(shortUrl)).thenReturn("ax");
		assertEquals(TINYURL+"ax", urlController.urlConversion(shortUrl));
	}

	@Test
	public void getAndRedirectTest() {
		String shortUrl = "https://www.google.com/search?q=cobertura+configur"
				+ "ation+with+executions+in+spring+boot&rlz=1C1CHBF_enIN934IE936"
				+ "&oq=cobertura+configuration+with+executions&"
				+ "aqs=chrome.1.69i57j33i160.10790j0j7&sourceid=chrome&ie=UTF-8";
		when(mockUrlService.getOriginalUrl("bg")).thenReturn(shortUrl);
		
		assertEquals(shortUrl,urlController.getAndRedirect("bg"));
	}
	
}
