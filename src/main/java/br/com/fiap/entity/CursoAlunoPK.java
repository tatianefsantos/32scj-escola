package br.com.fiap.entity;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class CursoAlunoPK implements Serializable {

    @Column(name = "alunoId")
    private int alunoId;

    @Column(name = "cursoId")
    private int cursoId;

    public CursoAlunoPK() {
    }

    public CursoAlunoPK(int alunoId, int cursoId) {
        this.alunoId = alunoId;
        this.cursoId = cursoId;
    }

    public int getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(int alunoId) {
        this.alunoId = alunoId;
    }

    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }
}
