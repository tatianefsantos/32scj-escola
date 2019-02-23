package br.com.fiap.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "tb_alunos")
public class Aluno implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idaluno")
    private int id;
    private String nome;
    private String matricula;
    @OneToMany(
            mappedBy = "aluno",
            cascade = CascadeType.ALL
    )
    private Set<CursoAluno> cursoAlunos;

    public Aluno (){
         super();
    }

    public Aluno(String nome, String matricula) {
        this.nome = nome;
        this.matricula = matricula;
    }

    public Aluno(int id, String nome, String matricula) {
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
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

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Set<CursoAluno> getCursoAlunos() {
        return cursoAlunos;
    }

    public void setCursoAlunos(Set<CursoAluno> cursoAlunos) {
        this.cursoAlunos = cursoAlunos;
    }
}
