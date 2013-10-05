/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pr.entity;

import java.math.BigDecimal;

/**
 *
 * @author fabinhosano
 */
public class Vaga {
    
    private int id;
    
    private int id_estadia;
    
    private int id_usuario;
    
    private int id_rep;
    
    private String nome;
    
    private String vaga_genero;
    
    private String descricao;
    
    private String prazo;
    
    private BigDecimal valor;
    
    private String disponibilidade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_estadia() {
        return id_estadia;
    }

    public void setId_estadia(int id_estadia) {
        this.id_estadia = id_estadia;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_rep() {
        return id_rep;
    }

    public void setId_rep(int id_rep) {
        this.id_rep = id_rep;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getVaga_genero() {
        return vaga_genero;
    }

    public void setVaga_genero(String vaga_genero) {
        this.vaga_genero = vaga_genero;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPrazo() {
        return prazo;
    }

    public void setPrazo(String prazo) {
        this.prazo = prazo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(String disponibilidade) {
        this.disponibilidade = disponibilidade;
    }
    
    
    
}
