package pr.bean;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import pr.bd.Conexao;
import pr.entity.Estadia;
import pr.entity.Republica;
import pr.entity.Usuario;

@ManagedBean
@SessionScoped
public class BeanEstadia {

    public Estadia estadia;
    public Usuario usuario;
    public Republica republica;
    public PreparedStatement ps;
    public ResultSet rs;
    private Conexao c = new Conexao("postgres", "postgres");
    private String msg;
    private boolean msgType = false;
    private String valorPesquisa = "Disponivel";
    int id_rep;
    int id_usuario;

    public BeanEstadia() {
        usuario = new Usuario();
        republica = new Republica();
        estadia = new Estadia();
    }

    //Métodos - CRUD
    public String insert() throws SQLException {
        try {

            BeanUsuario BU = (BeanUsuario) FacesContext.getCurrentInstance().getExternalContext().
                    getSessionMap().get("beanUsuario");
            id_usuario = BU.getUsuario().getId();

            BeanRepublica BR = (BeanRepublica) FacesContext.getCurrentInstance().getExternalContext().
                    getSessionMap().get("beanRepublica");
            id_rep = BR.getRepublica().getId();

            ps = c.getConnection().prepareStatement("SELECT i_estadia(?, ?, ?, ?, ?, null, ?)");

            ps.setInt(1, id_usuario);
            ps.setInt(2, id_rep);
            ps.setString(3, estadia.getNome());
            ps.setString(4, estadia.getData_entrada());
            ps.setString(5, estadia.getData_saida());
            ps.setBigDecimal(6, estadia.getValor());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                estadia.setId(rs.getInt(1));

                //return "vaga?face-redirect=true";
            }

            return "vaga.xhtml";

        } catch (Exception e) {
            msg = "Erro! Verifique os dados!";

            msgType = true;

            growlMsg(null);

            return null;
        } finally {
            ps.close();
        }
    }

    public void update() throws SQLException {
        try {
            ps = c.getConnection().prepareStatement("SELECT u_estadia(?,?,?,?,?,?,?,?)");
            ps.setLong(1, estadia.getId());
            ps.setLong(2, usuario.getId());
            ps.setLong(3, republica.getId());
            ps.setString(4, estadia.getNome());
            ps.setString(5, estadia.getData_entrada());
            ps.setString(6, estadia.getData_saida());
            ps.setString(7, estadia.getDesc());
            ps.setBigDecimal(8, estadia.getValor());
            ps.execute();

            msg = "Atualizado com Sucesso!";

            growlMsg(null);
        } catch (Exception e) {
            msg = "Erro! Verifique os dados!";
            msgType = true;
            growlMsg(null);
        } finally {
            ps.close();
        }
    }

    public void delete() {
    }

    public Usuario getCurrentUser() {
        Usuario u = null;
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext externalContext = fc.getExternalContext();
        if (externalContext.getUserPrincipal() == null) {
            System.out.println("current principal is null");
        } else {
            String nome = String.valueOf(externalContext.getUserPrincipal().getName());
            try {
                u = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(usuario.getNome());
            } catch (Exception ex) {
                Logger.getLogger(BeanUsuario.class.getName()).log(Level.SEVERE, null, ex);

            }
            return u;
        }
        return null;

    }

    public List<Estadia> getAll() throws SQLException {

        ArrayList<Estadia> estadias = new ArrayList<Estadia>();

        try {
            ps = c.getConnection().prepareStatement("SELECT * FROM estadia;");

            rs = ps.executeQuery();

            while (rs.next()) {

                Estadia est = new Estadia();

                est.setId(rs.getInt(1));

                int id_rep = rs.getInt(2);

                est.setNome(rs.getString(3));

                est.setData_entrada(rs.getString(4));

                est.setData_saida(rs.getString(5));

                est.setDesc(rs.getString(6));

                est.setValor(rs.getBigDecimal(7));

                ps = c.getConnection().prepareStatement("select count(*) from + "
                        + "estadia inner join vaga on estadia.id = vaga.id_estadia_fk WHERE "
                        + "vaga.disponibilidade = 'disponivel' AND estadia.id = " + id_rep + ";");

                est.setQtd_vagas(rs.getInt(1));

                estadias.add(est);
            }

            msg = "Estadias localizadas";

            growlMsg(null);

        } catch (Exception e) {
            msg = "Erro! Verifique os dados!";

            growlMsg(null);
        } finally {

            ps.close();
        }

        return estadias;
    }

   public void growlMsg(ActionEvent actionEvent) {

        if (msgType) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ação Concluída", msg));
        }
}
    public Estadia getEstadia() {
        return estadia;
    }

    public void setEstadia(Estadia estadia) {
        this.estadia = estadia;
    }

    public void setValorPesquisa(String s) {

        this.valorPesquisa = s;
    }

    public String getValorPesquisa() {

        return valorPesquisa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Republica getRepublica() {
        return republica;
    }

    public void setRepublica(Republica republica) {
        this.republica = republica;
    }
}
