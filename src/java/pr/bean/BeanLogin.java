/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pr.bean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import pr.bd.Conexao;
import pr.entity.Email;
import pr.entity.Usuario;

/**
 *
 * @author fabinhosano
 */
@ManagedBean
@ApplicationScoped
public class BeanLogin {

    Usuario usuario;
    Email email;
    
    private PreparedStatement ps;
    private Conexao conexao = new Conexao("postgres", "postgres");
    
        
    public BeanLogin() {
        usuario = new Usuario();
        email = new Email();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }
    
    public String login() throws SQLException {
        try {
            ps = conexao.getConnection().prepareStatement("SELECT login(?, ?)");

            ps.setString(1, email.getEmail());
            ps.setString(2, usuario.getSenha());


            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario.setId(rs.getInt(1));
                
                BeanUsuario BU = (BeanUsuario) FacesContext.getCurrentInstance().getExternalContext().
                    getSessionMap().get("beanUsuario");
                BU.carregaUsuario(rs.getInt(1));
               
            }
            
            return "usuario.xhtml";
        } catch (Exception e) {
            System.out.println("Erro ao logar.");
            
            return "home.xhtml";
        } 


    }
}
