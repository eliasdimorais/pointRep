/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pr.bean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import pr.bd.Conexao;
import pr.entity.Email;
import pr.entity.Usuario;

/**
 *
 * @author fabinhosano
 */
@ManagedBean
@SessionScoped
public class BeanUsuario {

    Usuario usuario;
    Email email;
    
    private boolean msgType = false;
    private String msg;
    
    private PreparedStatement ps;
    
    private Conexao conexao = new Conexao("postgres", "postgres");
    
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
    
   
    public BeanUsuario() {
        usuario = new Usuario();
        email = new Email();
    }
    
    public String insert() throws SQLException{
        try {
            ps = conexao.getConnection().prepareStatement("SELECT i_usuario(?, ?, ?, ?, null, null, null, null, ?, true)");

            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSobrenome());
            ps.setString(3, usuario.getData_nasc());
            ps.setString(4, usuario.getGenero());
            ps.setString(5, usuario.getSenha());

            ResultSet rs = ps.executeQuery();
            
            if (rs.next()){
                usuario.setId(rs.getInt(1));
                
                boolean email_inserido = insertEmail();
                if(email_inserido){
                    msg = "Cadastro Efetuado!";
                    growlMsg(null);
            
                    //BeanLogin bl = (BeanLogin) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("beanLogin");
            
                    return "usuario.xhtml";
                }
            }
            return "index.xhtml";
            
        } catch (Exception e) {
            msg = "Erro! Verifique os dados!";

            msgType = true;
            
            growlMsg(null);
            
            return null;
        } finally{
            ps.close();
        }   
    }
    
    public String update() throws SQLException{
        try {
            ps = conexao.getConnection().prepareStatement("SELECT u_usuario(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, true)");
            
            ps.setInt(1, usuario.getId());
            ps.setString(2, usuario.getNome());
            ps.setString(3, usuario.getSobrenome());
            ps.setString(4, usuario.getData_nasc());
            ps.setString(5, usuario.getGenero());
            ps.setString(6, usuario.getApelido());
            ps.setString(7, usuario.getSkype());
            ps.setString(8, usuario.getFacebook());
            ps.setString(9, usuario.getTwitter());
            ps.setString(10, usuario.getSenha());
            
            ps.executeQuery();
                                   
            msg = "Cadastro Atualizado!";

            growlMsg(null);

            return "usuario.xhtml";
            
        } catch (Exception e) {
            msg = "Erro! Verifique os dados!";

            msgType = true;
            
            growlMsg(null);
            
            return null;
        }finally {
            ps.close();
        }
    }
    
    public String delete() throws SQLException{
        try{    
            ps = conexao.getConnection().prepareStatement("SELECT d_usuario(?)");
            
            ps.setLong(1, usuario.getId());
            
            ps.executeQuery();
            
            msg = "Usuario inativo.";
            
            //growlMsg(null);
            
            return "index.xhtml";
        } catch (Exception e) {
            msg = "Erro! Não é possível inativar o usuário!";

            msgType = true;

            //growlMsg(null);

            return null;
        }finally {
            
            ps.close();

        }
    }
    
    public void carregaUsuario(int id_usuario) throws SQLException{
    try {
            ps = conexao.getConnection().prepareStatement("SELECT * FROM usuario WHERE id = ?");

            ps.setInt(1, id_usuario);
            
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                usuario.setId(rs.getInt(1));

                usuario.setNome(rs.getString(2));
                
                usuario.setSobrenome(rs.getString(3));
                
                usuario.setData_nasc(rs.getString(4));
                
                usuario.setGenero(rs.getString(5));
                
                usuario.setApelido(rs.getString(6));
                
                usuario.setSkype(rs.getString(7));
                
                usuario.setFacebook(rs.getString(8));
                
                usuario.setTwitter(rs.getString(9));
            }

            msg = "Usuario Logado " + usuario.getNome();

            growlMsg(null);

        } catch (Exception e) {
            msg = "Erro ao Logar! Verifique os dados!";

            growlMsg(null);
        } finally {

            ps.close();
        }

    }

    
    public void growlMsg(ActionEvent actionEvent) {
        if (msgType) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ação Concluída", msg));
            return;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ação Concluída", msg));
    }
 
    public boolean insertEmail() throws SQLException{
        try {
            ps = conexao.getConnection().prepareStatement("SELECT i_email(?, ?)");
        
            ps.setInt(1, usuario.getId());
            ps.setString(2, email.getEmail());
            
            ps.executeQuery();
            
            msg = "Email cadastrado com sucesso!";

            //growlMsg(null);
            
            return true;
        }catch (Exception e){
            msg = "Erro! Email invalido!";

            msgType = true;
            
            //growlMsg(null);
            
            return false;
        }finally{
            ps.close();
        }
              
    }
    
}
