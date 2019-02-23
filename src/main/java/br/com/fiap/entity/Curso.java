package br.com.fiap.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "tb_cursos")
public class Curso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idcurso")
    private int id;
    private String nome;
    private int codigo;
    private String sigla;
    @OneToMany(
            mappedBy = "curso",
            cascade = CascadeType.ALL
    )
    private Set<CursoAluno> cursoAlunos;

    public Curso() {
        super();
    }

    public Curso(String nome, int codigo, String sigla) {
        this.nome = nome;
        this.codigo = codigo;
        this.sigla = sigla;
    }

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

    public Set<CursoAluno> getCursoAlunos() {
        return cursoAlunos;
    }

    public void setCursoAlunos(Set<CursoAluno> cursoAlunos) {
        this.cursoAlunos = cursoAlunos;
    }
}
