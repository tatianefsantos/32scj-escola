package br.com.fiap.aplicacao;

import br.com.fiap.dao.AlunoDao;
import br.com.fiap.entity.Aluno;

public class SistemaEscola {

	public static void main(String[] args) {
		incluirAluno();

		System.exit(1);
	}

	private static void incluirAluno() {
		AlunoDao alunoDao = new AlunoDao(Aluno.class);

		Aluno aluno = new Aluno("Tatiane", "RM44463");

		try {
			alunoDao.adicionar(aluno);
			System.out.println("Aluno incluído com sucesso.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}