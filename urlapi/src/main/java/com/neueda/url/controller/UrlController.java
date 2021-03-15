package com.neueda.url.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neueda.url.service.UrlService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/url")
public class UrlController {
	
	/* cross origins for getting requests from REACT JS to rest end point
	 * This controller is used for shortening the Long url by using the base 64
	 * and get back original url by ID
	 *  */
	
	private static final Logger log = LoggerFactory.getLogger(UrlController.class);
	private static final String TINYURL = "http://bit.ly/";
	
	@Autowired
	private UrlService urlService;
	
	@PostMapping("/longUrl")
	public String urlConversion(@RequestBody String longURL) {
		log.info("the long URL entered is ==> "+longURL);
		return TINYURL+urlService.convertToShortUrl(longURL);
	}
		
    @GetMapping("/shortUrl/{shortUrlId}")
    public String getAndRedirect(@PathVariable String shortUrlId) {
        String url = urlService.getOriginalUrl(shortUrlId);
        return url;
    }
}
