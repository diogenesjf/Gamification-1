package ch.heigvd.gamification.to;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * This is a genreic TO when you want to pass through JSON/XML a simple ID
 * and not the whole representation
 * 
 * @author Gaël Jobin
 */
@XmlRootElement
public class GenericOnlyIDTO {
    private long id;
    
    public GenericOnlyIDTO() {
    }
    
    public GenericOnlyIDTO(long id) {
        this.id = id;
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
}
