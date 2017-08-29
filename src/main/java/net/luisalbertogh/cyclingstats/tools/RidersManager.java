/**
 * Riders management and processor.
 */
package net.luisalbertogh.cyclingstats.tools;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.luisalbertogh.cyclingstats.domain.Rider;

/**
 * @author loga
 *
 */
public class RidersManager {
    /** A logger reference */
    private Logger logger;
    
    /**
     * Default constructor.
     */
    public RidersManager(){
        /* Logger */
        logger = LoggerFactory.getLogger(this.getClass());
    }
    
    /**
     * Order riders list by classification type.
     * @param classif - The classification type
     * @return The ordered list
     */
    public List<Rider> orderRidersBy(List<Rider> riders, String classifier){
        logger.info("Order list by " + classifier);
        boolean reverse = true;
        
        /* Ranking are not reversed order */
        if(classifier.equalsIgnoreCase(RiderClassifier.BEST_UCI.getClassifier()) || classifier.equalsIgnoreCase(RiderClassifier.BEST_PCS.getClassifier())){
            reverse = false;
        }
        
        /* Create comparator and sort */
        if(!reverse) {
            Collections.sort(riders, new RiderComparator(classifier));
        } else {
            Collections.sort(riders, Collections.reverseOrder(new RiderComparator(classifier)));
        }
        
        return riders;
    }
    
    /**
     * Rider comparator
     * @author loga
     *
     */
    class RiderComparator implements Comparator<Rider> {
        /** The classifier */
        protected RiderClassifier classifier;
        
        /**
         * Constructor
         * @param classifier
         */
        public RiderComparator(String classifier){
            if(classifier.equalsIgnoreCase(RiderClassifier.BEST_UCI.getClassifier())){
                this.classifier = RiderClassifier.BEST_UCI;
            }
            else if(classifier.equalsIgnoreCase(RiderClassifier.BEST_PCS.getClassifier())){
                this.classifier = RiderClassifier.BEST_PCS;
            }
            else if(classifier.equalsIgnoreCase(RiderClassifier.ODR.getClassifier())){
                this.classifier = RiderClassifier.ODR;
            }
            else if(classifier.equalsIgnoreCase(RiderClassifier.GC.getClassifier())){
                this.classifier = RiderClassifier.GC;
            }
            else if(classifier.equalsIgnoreCase(RiderClassifier.TT.getClassifier())){
                this.classifier = RiderClassifier.TT;
            }
            else if(classifier.equalsIgnoreCase(RiderClassifier.SPRINT.getClassifier())){
                this.classifier = RiderClassifier.SPRINT;
            }
        }
        
        /**
         * Compare up to classificator.
         */
        @Override
        public int compare(Rider o1, Rider o2) {
            int value1 = 0, value2 = 0;
            
            /* Up to classifier */
            switch(classifier){
                /* UCI ranking */
                case BEST_UCI:{          
                    value1 = o1.getUciRanking();
                    value2 = o2.getUciRanking();
                    break;
                }
                /* PCS ranking */
                case BEST_PCS:{
                    value1 = o1.getPcsRanking();
                    value2 = o2.getPcsRanking();
                    break;
                }
                /* ODR */
                case ODR:{
                    value1 = o1.getOdr();
                    value2 = o2.getOdr();
                    break;
                }
                /* GC */
                case GC:{
                    value1 = o1.getGc();
                    value2 = o2.getGc();
                    break;
                }
                /* TT */
                case TT:{
                    value1 = o1.getTt();
                    value2 = o2.getTt();
                    break;
                }
                /* Sprint */
                case SPRINT:{
                    value1 = o1.getSprint();
                    value2 = o2.getSprint();
                    break;
                }
            }
            
            /* Compare values */
            if(value1 == value2){
                return 0;
            }
            
            return value1 > value2 ? 1 : -1;
        }
        
    }
}
