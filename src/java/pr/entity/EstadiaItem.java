package pr.entity;

public class EstadiaItem {

    private int id;
    
    private Estadia est;
    
    private Item item;
    
    private int qtd;
    
    private String desc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Estadia getEst() {
        return est;
    }

    public void setEst(Estadia est) {
        this.est = est;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
}
