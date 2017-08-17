/**
 * Cycling stats controller.
 */
package net.luisalbertogh.cyclingstats.controllers;

import java.util.Arrays;
import java.util.Map;
import java.util.logging.Level;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import net.luisalbertogh.cyclingstats.domain.Rider;
import net.luisalbertogh.cyclingstats.tools.HtmlParser;

/**
 * @author loga
 *
 */
@RestController
public class CSController {
    
    /** A logger reference */
    private Logger logger;
    
    /** Rest template */
    @Autowired
    protected RestTemplate restTemplate;
    
    /** Html parser */
    @Autowired
    protected HtmlParser htmlParser;
    
    /** HTTP headers */
    protected HttpHeaders headers;
    
    /** Base URL */
    protected static final String URL = "http://www.procyclingstats.com";
    
    /**
     * Default constructor.
     */
    public CSController(){
        /* Logger */
        logger = LoggerFactory.getLogger(this.getClass());
        
        /* Set HTTP headers */
        headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
    }
    
    /**
     * Go home.
     * @return
     */
    @RequestMapping("/")
    public String home(){
        return "Relax, you are at home";
    }    
    
    @SuppressWarnings("unchecked")
    @RequestMapping("/rider/{name}")
    public Rider getRiderDetails(@PathVariable("name") String name){
        Rider rider = new Rider();
        logger.info("Invoking getRiderDetails service...");
        try {
            /* Validate name */
            if(name == null || name.equals("")){
                throw new Exception("Name is null or empty");
            }
            
            /* Add headers */
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
            ResponseEntity<String> res = restTemplate.exchange(URL + "/rider/" + name, HttpMethod.GET, entity, String.class);
            /* Get HTML */
            if(res.getStatusCodeValue() == HttpStatus.OK.value()){
                /* Parse HTML */
                String html = res.getBody();
                Map<String, Object> details = htmlParser.getDetails(html);
                if(!details.isEmpty()){
                    Map<String, Integer> points = (Map<String, Integer>) details.get("Points");
                    rider = new Rider(name, (String) details.get("Birthdate"), (String) details.get("Nationality"), (String) details.get("Weight"), (String) details.get("Height"), points.get("ODR"), points.get("GC"),
                            points.get("TT"), points.get("Sprint"));
                    logger.debug("\n" + rider);
                }
            }
        } catch(Exception ex){
            ex.printStackTrace();
            logger.error(ex.getMessage());
        }
        
        return rider;
    }    
}
