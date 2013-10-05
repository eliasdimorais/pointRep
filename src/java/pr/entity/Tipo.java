package pr.entity;

public class Tipo {
    
    private long id;
    
    private Republica rep;
    
    private String nome;
    
    private String desc;
    
    private String data_vencimento;

    private String data_pagamento;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Republica getRep() {
        return rep;
    }

    public void setRep(Republica rep) {
        this.rep = rep;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getData_vencimento() {
        return data_vencimento;
    }

    public void setData_vencimento(String data_vencimento) {
        this.data_vencimento = data_vencimento;
    }

    public String getData_pagamento() {
        return data_pagamento;
    }

    public void setData_pagamento(String data_pagamento) {
        this.data_pagamento = data_pagamento;
    }
    
    
}
