package ch.heigvd.gamification.to;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gaël Jobin
 */
@XmlRootElement
public class PublicRuleTO {
    private long ruleId;

	private String name;
	private String description;
	private int acquiredPoints;
        
   public PublicRuleTO() {
   }
   
   public PublicRuleTO(long ruleId, String name, String description, int acquiredPoints) {
       this.ruleId = ruleId;
       this.name = name;
       this.description = description;
       this.acquiredPoints = acquiredPoints;
   }
   
   public long getId() {
		return ruleId;
	}

	public void setId(long ruleId) {
		this.ruleId = ruleId;
	}
        
        public String getName(){
            return this.name;
        }
        
        public void setName(String name) {
            this.name = name;
        }
        
        public String getDescription() {
            return this.description;
        }
        
        public void setDescription(String description)
        {
            this.description = description;
        }
        
        public int getAcquiredPoints() {
            return this.acquiredPoints;
        }
        
        public void setAcquiredPoints(int acquiredPoints)
        {
            this.acquiredPoints = acquiredPoints;
        }
}
