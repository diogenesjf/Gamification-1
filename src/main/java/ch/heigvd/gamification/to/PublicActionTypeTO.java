/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.heigvd.gamification.to;

import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Gaël Jobin
 */
@XmlRootElement(name="actionType")
public class PublicActionTypeTO {
    private long id;
    
    private String title;
    private int points;
    private String description;
    
    public PublicActionTypeTO() {
    }
    
    public PublicActionTypeTO(long id, String title, int points, String description) {
        this.id = id;
        this.title = title;
        this.points = points;
        this.description = description;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle(){
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    public int getPoints(){
        return this.points;
    }
    
    public void setPoints(int points) {
        this.points = points;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}
