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
@XmlRootElement
public class PublicActionTypeTO {
    private long id;
    
    private String name;
    
    public PublicActionTypeTO() {
    }
    
    public PublicActionTypeTO(long id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
