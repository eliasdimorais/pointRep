package pr.bd;

import java.sql.Connection;
import java.sql.DriverManager;


public class Conexao {
 
    private final String driver = "org.postgresql.Driver";
    
    private final String url = "jdbc:postgresql://localhost/pointrep";
    
    private String usuario = "postgres";
    
    private String senha = "postgres";

    //Construtor
    public Conexao(){
    }
    
    public Conexao(String usuario, String senha){
    
        this.usuario = usuario;
        this.senha = senha;
    }
    
  
    //Métodos    
    public Connection getConnection() {
        
        try {
            
            Class.forName(driver);
            return DriverManager.getConnection(url, usuario, senha);
                    
        } catch (Exception e){
            
            e.printStackTrace();
        }
        
        return null;
    }
    
    //Getters and Setters
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}