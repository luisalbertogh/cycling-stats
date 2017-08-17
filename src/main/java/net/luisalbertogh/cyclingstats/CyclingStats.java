/**
 * Main class.
 */
package net.luisalbertogh.cyclingstats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import net.luisalbertogh.cyclingstats.tools.HtmlParser;

/**
 * @author loga
 *
 */
@SpringBootApplication
public class CyclingStats {

	/**
	 * Main method.
	 * @param args
	 */
	public static void main(String[] args) {
	    /* Set configuration file */
	    System.setProperty("spring.config.name", "cycling-stats");
	    SpringApplication.run(CyclingStats.class, args);
	}

	/**
     * REST template.
     * @return
     */
    @Bean
    protected RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
    /**
     * HTML parser.
     * @return
     */
    @Bean
    protected HtmlParser htmlParser() {
        return new HtmlParser();
    }
}
