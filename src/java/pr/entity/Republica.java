/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pr.entity;

/**
 *
 * @author fabinhosano
 */
public class Republica {
    private int id;
    
    private UF estado;
    
    private int cidade;
    
    private Usuario resp;
    
    private String nome;
    
    private String endereco;
    
    private String bairro;
    
    private String cep;
    
    private String ponto_ref;
    
    private String descricao;

    private int limite_vagas;
    
    private String coordenadas;
    
    private boolean ativo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UF getEstado() {
        return estado;
    }

    public void setEstado(UF estado) {
        this.estado = estado;
    }

    public int getCidade() {
        return cidade;
    }

    public void setCidade(int cidade) {
        this.cidade = cidade;
    }

    public Usuario getResp() {
        return resp;
    }

    public void setResp(Usuario resp) {
        this.resp = resp;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getPonto_ref() {
        return ponto_ref;
    }

    public void setPonto_ref(String ponto_ref) {
        this.ponto_ref = ponto_ref;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getLimite_vagas() {
        return limite_vagas;
    }

    public void setLimite_vagas(int limite_vagas) {
        this.limite_vagas = limite_vagas;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    
}
