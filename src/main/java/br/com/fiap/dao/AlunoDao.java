package br.com.fiap.dao;

import br.com.fiap.entity.Aluno;

public class AlunoDao extends GenericDao<Aluno> {
    public AlunoDao(Class<Aluno> classe) {
        super(classe);
    }
}
