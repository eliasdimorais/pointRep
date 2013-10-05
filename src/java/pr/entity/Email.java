/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pr.entity;

/**
 *
 * @author fabinhosano
 */
public class Email {
    
    private int id_user_fk;
    
    private String email;

    public int getUsuario_fk() {
        return id_user_fk;
    }

    public void setUsuario_fk(int id_user_fk) {
        this.id_user_fk = id_user_fk;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
