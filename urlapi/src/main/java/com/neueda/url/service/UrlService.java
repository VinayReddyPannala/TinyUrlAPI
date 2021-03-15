package com.neueda.url.service;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neueda.url.dao.UrlRepository;
import com.neueda.url.entity.Url;


@Service
public class UrlService {

	private static final Logger log = LoggerFactory.getLogger(UrlService.class);
	
	@Autowired
	private BaseConversion baseConversion;
	
	@Autowired
	private UrlRepository urlRepository;
	
    public String convertToShortUrl(String longURL) {
        Url url = new Url();
        url.setLongUrl(longURL);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 15); 
		/* the url will expires in 15 days */
        url.setExpiryDate(cal.getTime());
        url.setCreatedDate(new Date());
        log.info("The value of url entity "+ url.getId());
        log.info("the value of url entity long url " + url.getLongUrl());
        Url entity = urlRepository.save(url);
        
        /* gets incremental id every time..no collision for generating the tiny url */
        return baseConversion.encode(entity.getId()); 
    }
	
    public String getOriginalUrl(String shortUrl) {
         long id = baseConversion.decode(shortUrl);
         Url entity = urlRepository.findById(id).
        		orElseThrow(() -> new EntityNotFoundException("There is no entity with " + shortUrl));

        if (entity.getExpiryDate() != null && entity.getExpiryDate().before(new Date())){
            urlRepository.delete(entity);
            throw new EntityNotFoundException("Link expired!");
        }

        return entity.getLongUrl();
    }
}
