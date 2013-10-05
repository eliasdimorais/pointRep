package pr.entity;

/**
 *
 * @author jhon
 */
public class Dub {

    private int id;
    private String nome;
    private String kh;
    
    public Dub(int id, String nome, String kh){
    
        this.id = id;
        this.nome= nome;
        this.kh = kh;
    }

    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getKh() {
        return kh;
    }

    public void setKh(String kh) {
        this.kh = kh;
    }
}
