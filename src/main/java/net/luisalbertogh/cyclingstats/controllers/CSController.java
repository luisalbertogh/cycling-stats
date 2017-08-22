/**
 * Cycling stats controller.
 */
package net.luisalbertogh.cyclingstats.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
        headers.add("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:54.0) Gecko/20100101 Firefox/54.0");
        headers.add("Cookie", "__cfduid=d3cd04ce6692ca5ffa7d0884a879ef3671502963235; PHPSESSID=tc07vh9jll5oa8mc07rhqlu9e3; _ga=GA1.2.1146337387.1502963237; _gid=GA1.2.569996841.1502963237; __gads=ID=527d3fb82a7f7d9b:T=1502963236:S=ALNI_MbHEPQzvbWDOzo9diHX9JHMFvXqmA; procyclingstats_cookie_consent=yes");
    }
        
    /**
     * Get single rider details.
     * @param name The rider name
     * @return The rider details
     */
    @RequestMapping(value = "/rider/{name}", method = RequestMethod.GET, produces = "application/json")
    public Rider getRiderDetails(@PathVariable("name") String name){
        Rider rider = new Rider();
        logger.info("Invoking getRiderDetails service...");
        try {
            rider = getRider(name);
        } catch(Exception ex){
            ex.printStackTrace();
            logger.error(ex.getMessage());
        }
        
        return rider;
    }
    
    /**
     * Get riders list.
     * @param list - The query parameter list
     * @return The list of riders
     */
    @RequestMapping(value = "/rider/list", method = RequestMethod.GET, produces = "application/json")
    public List<Rider> getRidersList(@RequestParam("list") String list){
        List<Rider> riders = new ArrayList<>();
        logger.info("Invoking getRidersList service...");
        try {
            /* List not empty */
            if(list != null && !list.equals("")){
                String[] names = list.split(",");
                for(String name:names){
                    Rider rider = getRider(name.trim());
                    if(rider != null) {
                        riders.add(rider);
                    }
                }
            }
        } catch(Exception ex){
            ex.printStackTrace();
            logger.error(ex.getMessage());
        }
        
        return riders;
    }
    
    /**
     * Get rider instance
     * @param name - The rider name
     * @return The rider or null
     */
    @SuppressWarnings("unchecked")
    protected Rider getRider(String name){
        Rider rider = null;
        try {
            /* Validate name */
            if(name == null || name.equals("")){
                throw new Exception("Name is null or empty");
            }
            
            /* Add headers */
            HttpEntity<String> entity = new HttpEntity<String>(headers);
            ResponseEntity<String> res = restTemplate.exchange(URL + "/rider/" + name, HttpMethod.GET, entity, String.class);
            /* Get HTML */
            if(res.getStatusCodeValue() == HttpStatus.OK.value()){
                /* Parse HTML */
                String html = res.getBody();
                Map<String, Object> details = htmlParser.getDetails(html);
                if(!details.isEmpty()){
                    Map<String, Integer> points = (Map<String, Integer>) details.get("Points");
                    rider = new Rider(name, (String) details.get("Birthdate"), (String) details.get("Nationality"), (String) details.get("Weight"), (String) details.get("Height"), points.get("ODR"), points.get("GC"),
                            points.get("TT"), points.get("Sprint"), Integer.parseInt((String) details.get("pcsRanking")), Integer.parseInt((String) details.get("uciRanking")));
                    logger.debug("\n" + rider);
                }
            }
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
        
        return rider;
    } 
}
