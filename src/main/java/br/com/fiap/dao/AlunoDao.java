package br.com.fiap.dao;

import br.com.fiap.entity.Aluno;
import br.com.fiap.helper.JpaUtil;

import javax.persistence.Query;

public class AlunoDao extends GenericDao<Aluno> {
    public AlunoDao(Class<Aluno> classe) {
        super(classe);
    }

    public Aluno buscarPorMatricula(String matricula) {
        em = JpaUtil.getEntityManager();
        Query query = em.createQuery("select a from Aluno a where matricula = :matricula");
        query.setParameter("matricula", matricula);
        Aluno a = (Aluno) query.getSingleResult();
        return a;
    }
}
