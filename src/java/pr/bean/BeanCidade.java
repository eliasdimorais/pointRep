package pr.bean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import pr.bd.Conexao;
import pr.entity.Cidade;
import pr.entity.UF;

@ManagedBean
@RequestScoped
public class BeanCidade {

    private ArrayList<Cidade> cidades = new ArrayList<Cidade>();
    
    public BeanCidade() {
    }

    public Collection<Cidade> getCidades() throws SQLException{
        
        PreparedStatement ps = new Conexao().getConnection().prepareStatement("SELECT * FROM cidade;");
    
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            
            Cidade c = new Cidade();
            
            c.setId(rs.getInt(1));
            
            UF uf = new UF();
            
            uf.setId(rs.getInt(2));
            
            c.setUf(uf);
            
            c.setCidade(rs.getString(3));
        
            cidades.add(c);
        }
    
        return cidades;
    }
}
