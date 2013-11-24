/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.gamification.to;

/**
 *
 * @author thomasmoegli
 */
public class PublicApplicationTO {
    
  private Long id;
  private String name;

  public PublicApplicationTO() {
  }

  public PublicApplicationTO(Long id, String name) {
    this.id = id;
    this.name = name;
  }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
  
}
