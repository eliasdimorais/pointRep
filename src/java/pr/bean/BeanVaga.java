package pr.bean;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import pr.bd.Conexao;
import pr.entity.Estadia;
import pr.entity.Republica;
import pr.entity.Usuario;
import pr.entity.Vaga;

@ManagedBean
@SessionScoped
public class BeanVaga {

    public Vaga vaga;
    public Estadia estadia;
    public Usuario usuario;
    public Republica republica;
    public PreparedStatement ps;
    private Conexao c = new Conexao("postgres", "postgres");
    private String msg;
    private boolean msgType = false;

    public BeanVaga() {
        vaga = new Vaga();
        estadia = new Estadia();
        usuario = new Usuario();
        republica = new Republica();
    }

    public void insert() throws SQLException {
        try {
            ps = c.getConnection().prepareStatement("SELECT i_vaga(?,?,?,?,?,?,?,?,?)");
            ps.setLong(1, estadia.getId());
            ps.setLong(2, republica.getId());
            ps.setLong(3, usuario.getId());
            ps.setString(4, vaga.getNome());
            ps.setString(5, vaga.getVaga_genero());
            ps.setString(6, vaga.getDescricao());
            ps.setString(7, vaga.getPrazo());
            ps.setBigDecimal(8, vaga.getValor());
            ps.setString(9, vaga.getDisponibilidade());

        } catch (Exception e) {
            msg = "Erro! Verifique os dados!";
            msgType = true;
            growlMsg(null);
        } finally {
            ps.close();
        }
    }

    public void update() throws SQLException {
        try {
            ps = c.getConnection().prepareStatement("SELECT u_estadia(?,?,?,?,?,?,?,?)");
           
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

    private void growlMsg(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public Estadia getEstadia() {
        return estadia;
    }

    public void setEstadia(Estadia estadia) {
        this.estadia = estadia;
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
