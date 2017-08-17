/**
 * HTML parser.
 */
package net.luisalbertogh.cyclingstats.tools;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author loga
 *
 */
public class HtmlParser {
    /**
     * Parse HTML input to obtain rider details into a map.
     * @param html - The HTML page
     * @return Map with rider details
     */
    public Map<String, Object> getDetails(String html){
        Map<String, Object> details = new HashMap<>();
        
        /* Get the DOM */
        Document doc = Jsoup.parse(html);
        
        /* Birth date */
        String birthDate = getDateOfBirth(doc);
        details.put("Birthdate", birthDate);
        
        /* Nationality */
        String nationality = getNationality(doc);
        details.put("Nationality", nationality);
        
        /* Weight */
        String weight = getWeight(doc);
        details.put("Weight", weight);
        
        /* Height */
        String height = getHeight(doc);
        details.put("Height", height);
        
        /* Points */
        Map<String, Integer> points = getPoints(doc);
        details.put("Points", points);
        
        return details;
    }
    
    /**
     * Get points by speciality.
     * @param doc - The HTML DOM
     * @return A map with the speciality points
     */
    private Map<String, Integer> getPoints(Document doc){
        Map<String, Integer> points = new HashMap<>();
        /* One day races */
        Element div = doc.select("div.green").first();
        String npoints = div.parentNode().nextSibling().childNode(0).outerHtml().trim();
        points.put("ODR", Integer.parseInt(npoints));
        /* GC */
        div = doc.select("div.red").first();
        npoints = div.parentNode().nextSibling().childNode(0).outerHtml().trim();
        points.put("GC", Integer.parseInt(npoints));
        /* TIMETRIAL */
        div = doc.select("div.blue").first();
        npoints = div.parentNode().nextSibling().childNode(0).outerHtml().trim();
        points.put("TT", Integer.parseInt(npoints));
        /* Sprint */
        div = doc.select("div.orange").first();
        npoints = div.parentNode().nextSibling().childNode(0).outerHtml().trim();
        points.put("Sprint", Integer.parseInt(npoints));
        
        return points;
    }
    
    /**
     * Get weight.
     * @param doc - The HTML DOM
     * @return A string with the birth date
     */
    private String getHeight(Document doc){
        String height = "";
        Elements spans = doc.select("b:contains(Height)");
        for(Element span : spans){
            height += span.nextSibling().outerHtml();
        }
        
        return height;
    }
    
    /**
     * Get weight.
     * @param doc - The HTML DOM
     * @return A string with the birth date
     */
    private String getWeight(Document doc){
        String weight = "";
        Elements spans = doc.select("b:contains(Weight)");
        for(Element span : spans){
            weight += span.nextSibling().outerHtml();
        }
        
        return weight;
    }
    
    /**
     * Get nationality.
     * @param doc - The HTML DOM
     * @return A string with the nationality
     */
    private String getNationality(Document doc){
        String nationality = "";
        Elements spans = doc.select("b:contains(Nationality)");
        for(Element span : spans){
            nationality += span.nextSibling().nextSibling().nextSibling().childNode(0).outerHtml();
        }
        
        return nationality;
    }
    
    /**
     * Get date of birth 
     * @param doc - The HTML DOM
     * @return A string with the birth date
     */
    private String getDateOfBirth(Document doc){
        String dateOfBirth = "";
        Elements spans = doc.select("b:contains(Date of birth)");
        for(Element span : spans){
            /* Day */
            dateOfBirth += span.nextSibling().outerHtml();
            /* Month and year plus age */
            dateOfBirth += span.nextSibling().nextSibling().nextSibling().outerHtml();
        }
        
        return dateOfBirth;
    }
    
    /**
     * Print map content.
     * @param details
     */
    public String printPoints(Map<String, Integer> details){
        String ret = "";
        Set<String> keys = details.keySet();
        for(String key:keys){
            ret += key + ": " + details.get(key) + "\n";
        }
        
        return ret;
    }
    
    /**
     * Print map content.
     * @param details
     */
    @SuppressWarnings("unchecked")
    public String printDetails(Map<String, Object> details){
        String ret = "";
        Set<String> keys = details.keySet();
        for(String key:keys){
            Object obj = details.get(key);
            /* Strings */
            if(obj instanceof String){
                ret += key + ": " + ((String) obj) + "\n";
            } else {
                ret += printPoints((Map<String, Integer>)obj);
            }
        }
        
        return ret;
    }
}
