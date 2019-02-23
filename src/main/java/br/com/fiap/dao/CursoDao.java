package br.com.fiap.dao;

import br.com.fiap.entity.Curso;
import br.com.fiap.helper.JpaUtil;

import javax.persistence.Query;

public class CursoDao extends GenericDao<Curso> {
    public CursoDao(Class<Curso> classe) {
        super(classe);
    }

    public Curso buscarPorCodigo(int codigo) {
        em = JpaUtil.getEntityManager();
            Query query = em.createQuery("select c from Curso c where codigo = :codigo");
            query.setParameter("codigo", codigo);
            Curso c = (Curso) query.getSingleResult();
            return c;
    }
}
