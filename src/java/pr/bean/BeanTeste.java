package pr.bean;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import pr.bd.Conexao;
import pr.entity.Dub;

/**
 *
 * @author jhon
 */
@ManagedBean
@RequestScoped
public class BeanTeste {
   
    private List<Dub> dubs;
    
    public BeanTeste() {
    
        dubs = new ArrayList<Dub>();
        
        loadDubs();
    }

    public void loadDubs(){        
   
        try{
            
            Connection connection = new Conexao("usuario", "senha").getConnection();
            
            CallableStatement cs = connection.prepareCall("{call dubs()}");

            ResultSet rs = cs.executeQuery();
            
            while(rs.next()){
                
                dubs.add(new Dub(rs.getInt("id"), rs.getString("nome"), rs.getString("kh")));
            }
            
        }catch(Exception e){
        
            e.printStackTrace();
        }        
    }

    //Getters and Setters
    public List<Dub> getDubList(){
        
        return dubs;
    }

    public void setDubList(List<Dub> lista){
        
        dubs = lista;
    }
}

