package br.com.fiap.dao;

import br.com.fiap.entity.Curso;

public class CursoDao extends GenericDao<Curso> {
    public CursoDao(Class<Curso> classe) {
        super(classe);
    }
}
