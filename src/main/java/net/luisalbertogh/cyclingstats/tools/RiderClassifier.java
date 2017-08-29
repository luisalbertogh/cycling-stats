/**
 * Rider classifier.
 */
package net.luisalbertogh.cyclingstats.tools;

/**
 * @author loga
 *
 */
public enum RiderClassifier {
    BEST_UCI("bestuci", "UCI rank."), BEST_PCS("bestpcs", "PCS rank."), ODR("odr", "One day race"), GC("gc", "General classification"), TT("TT", "Time trial"), SPRINT("Sprint", "Sprint");
    
    /** The classifier */
    private String classifier;
    /** The label */
    private String label;

    /**
     * Constructor.
     * @param classifier
     */
    RiderClassifier(String classifier, String label){
        this.classifier = classifier;
        this.label = label;
    }
    
    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @return the classifier
     */
    public String getClassifier() {
        return classifier;
    }    
}
