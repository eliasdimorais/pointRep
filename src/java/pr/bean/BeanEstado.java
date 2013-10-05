/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pr.bean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import pr.bd.Conexao;
import pr.entity.UF;

/**
 *
 * @author p206
 */
@ManagedBean
@RequestScoped
public class BeanEstado {

    /**
     * Creates a new instance of BeanEstado
     */
     private ArrayList<UF> ufs = new ArrayList<UF>();

    

    
    
    public BeanEstado() {
    }

    public Collection<UF> getUFs() throws SQLException{
        
        PreparedStatement ps = new Conexao().getConnection().prepareStatement("SELECT * FROM estado;");
    
        ResultSet rs = ps.executeQuery();
        
       while (rs.next()) {

            UF uf = new UF();

            uf.setId(rs.getInt(1));

            uf.setPais(rs.getInt(2));

            uf.setNome(rs.getString(3));

            ufs.add(uf);
        }

        return ufs;
    }
    
    public void setUfs(ArrayList<UF> ufs) {
        this.ufs = ufs;
    }
    
    public ArrayList<UF> getUfs() {
        return ufs;
    }
}
