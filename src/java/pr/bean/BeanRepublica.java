/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pr.bean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import pr.bd.Conexao;
import pr.entity.Republica;

/**
 *
 * @author fabinhosano e elias
 */
@ManagedBean
@SessionScoped
public class BeanRepublica {

    private Republica republica;
    private String msg;
    private boolean msgType = false;
    private PreparedStatement ps;
    private Conexao conexao = new Conexao("postgres", "postgres");
    int id_usuario;

    public BeanRepublica() {
        republica = new Republica();
    }

    public Republica getRepublica() {
        return republica;
    }

    public void setRepublica(Republica republica) {
        this.republica = republica;
    }

    public String insert() throws SQLException {
        try {
            BeanUsuario BU = (BeanUsuario) FacesContext.getCurrentInstance().getExternalContext().
                    getSessionMap().get("beanUsuario");
            BU.getUsuario();
            id_usuario = BU.usuario.getId();

            ps = conexao.getConnection().prepareStatement("SELECT i_republica(?, ?, ?, ?, ?, null, null, ?, null, true)");

            ps.setInt(1, republica.getCidade());
            ps.setString(2, republica.getNome());
            ps.setString(3, republica.getEndereco());
            ps.setString(4, republica.getBairro());
            ps.setString(5, republica.getCep());
            ps.setInt(6, republica.getLimite_vagas());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                republica.setId(rs.getInt(1));

                boolean email_inserido = insertUserRepADM();
                if (email_inserido) {
                    msg = "Usuário definido como Administrador da Republica!";
                    growlMsg(null);

                }
            }

            return "republica?face-redirect=true";

        } catch (Exception e) {
            msg = "Erro! Verifique os dados!";

            msgType = true;

            growlMsg(null);

            return null;
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

    public boolean insertUserRepADM() throws SQLException {
        try {
            ps = conexao.getConnection().prepareStatement("SELECT i_user_rep(?, ?, true, ?, null, ?)");

            ps.setInt(1, republica.getId());
            ps.setInt(2, id_usuario);
            //ps.setBoolean(3, true);
            ps.setString(3, "18/09/2013");
            ps.setString(4, "permanente");

            ps.executeQuery();

            return true;
            
        } catch (Exception e) {
            msg = "Erro ao adcionar o usuário como administrador!";

            msgType = true;

            //growlMsg(null);

            return false;
        } finally {
            ps.close();
        }

    }
}
