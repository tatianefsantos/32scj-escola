package br.com.fiap.entity;

import javax.persistence.*;

@Entity
@Table(name = "tb_cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idcurso")
    private int id;
    private String nome;
    private int codigo;
    private String sigla;

    public Curso() {
        super();
    }

    public Curso(String nome, int codigo, String sigla) {
        this.nome = nome;
        this.codigo = codigo;
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
}
