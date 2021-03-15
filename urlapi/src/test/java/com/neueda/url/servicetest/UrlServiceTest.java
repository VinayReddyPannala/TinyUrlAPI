package com.neueda.url.servicetest;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.neueda.url.dao.UrlRepository;
import com.neueda.url.entity.Url;
import com.neueda.url.service.BaseConversion;
import com.neueda.url.service.UrlService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UrlServiceTest {
	
	Logger log = LoggerFactory.getLogger(UrlServiceTest.class);
	
	@Mock
	private BaseConversion mockBaseConversion;
	
	@Mock
	private UrlRepository mockUrlRepository;
	
	@InjectMocks
	UrlService mockUrlServiceTest;
    
    @Test
    public void getShortUrlTest() {
        Url url = new Url();
        url.setLongUrl("https://www.google.com/search?q=things+to+do+in+athlone&rlz=1C1CHBF_enIN93"
        		+ "4IE936&oq=things+to+do+in+athlone&aqs=chrome..69i57j0l2j0i22i30l7.20791j0j7&"
        		+ "sourceid=chrome&ie=UTF-8");
        url.setCreatedDate(new Date());
        url.setId(5);

        log.info("mockUrlRepository=====>"+mockUrlRepository);
        log.info("mockBaseConversion=====>"+mockBaseConversion);
        
        when(mockUrlRepository.save(any(Url.class))).thenReturn(url);
        when(mockBaseConversion.encode(url.getId())).thenReturn("f");

        String urlRequest = "https://www.google.com/search?q=things+to+do+in+athlone&rlz=1C1CHBF_enIN93"
        		+ "4IE936&oq=things+to+do+in+athlone&aqs=chrome..69i57j0l2j0i22i30l7.20791j0j7&"
        		+ "sourceid=chrome&ie=UTF-8";

        assertEquals("f", mockUrlServiceTest.convertToShortUrl(urlRequest));
    }

    @Test
    public void getOriginalUrlTest() {
        when(mockBaseConversion.decode("h")).thenReturn((long) 7);
        String urlRequest = "https://www.google.com/search?q=things+to+do+in+athlone&rlz=1C1CHBF_enIN93"
        		+ "4IE936&oq=things+to+do+in+athlone&aqs=chrome..69i57j0l2j0i22i30l7.20791j0j7&"
        		+ "sourceid=chrome&ie=UTF-8";

        Url url = new Url();
        url.setLongUrl("https://www.google.com/search?q=things+to+do+in+athlone&rlz=1C1CHBF_enIN93"
        		+ "4IE936&oq=things+to+do+in+athlone&aqs=chrome..69i57j0l2j0i22i30l7.20791j0j7&"
        		+ "sourceid=chrome&ie=UTF-8");
        url.setCreatedDate(new Date());
        url.setId(7);

        when(mockUrlRepository.findById((long) 7)).thenReturn(java.util.Optional.of(url));
        assertEquals(urlRequest, mockUrlServiceTest.getOriginalUrl("h"));

    }
	
}
