package br.com.fiap.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "curso_aluno")
public class CursoAluno {

    @EmbeddedId
    private CursoAlunoPK id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("alunoId")
    private Aluno aluno;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("cursoId")
    private Curso curso;

    @Column(name = "nota")
    private Long nota;

    public CursoAluno() {
    }

    public CursoAluno(Aluno aluno, Curso curso) {
        this.aluno = aluno;
        this.curso = curso;
        this.id = new CursoAlunoPK(aluno.getId(), curso.getId());
    }

    public CursoAlunoPK getId() {
        return id;
    }

    public void setId(CursoAlunoPK id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Long getNota() {
        return nota;
    }

    public void setNota(Long nota) {
        this.nota = nota;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        CursoAluno that = (CursoAluno) o;
        return Objects.equals(aluno.getId(), that.aluno.getId()) &&
                Objects.equals(curso.getId(), that.curso.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(aluno, curso);
    }
}
