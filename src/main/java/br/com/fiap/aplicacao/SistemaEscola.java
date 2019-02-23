package br.com.fiap.aplicacao;

import br.com.fiap.dao.AlunoDao;
import br.com.fiap.dao.CursoDao;
import br.com.fiap.entity.Aluno;
import br.com.fiap.entity.Curso;
import br.com.fiap.entity.CursoAluno;
import br.com.fiap.entity.CursoAlunoPK;
import br.com.fiap.helper.MenuEnum;

import javax.swing.*;
import java.util.HashSet;
import java.util.List;

public class SistemaEscola {

	private static AlunoDao alunoDao = new AlunoDao(Aluno.class);
	private static CursoDao cursoDao = new CursoDao(Curso.class);
	private static String[] options = { "Sim", "Não" };

	public static void main(String[] args) {
		MenuEnum menuEnum;
		String[] choices = { "Incluir aluno", "Incluir curso",  "Matricular aluno", "Incluir nota", "Listar alunos", "Listar cursos", "Listar alunos por curso", "Buscar notas do aluno"};

		do {
			String input = (String) JOptionPane.showInputDialog(null, "O que deseja fazer?", "Menu",
					JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);

			menuEnum = MenuEnum.getEnumByString(input);

			if(menuEnum == null) System.exit(1);

			switch (menuEnum) {
				case ADDALUNO:
					incluirAluno();
					break;
				case ADDCURSO:
					incluirCurso();
					break;

				case MATRICULA:
					matricularAluno();
					break;

				case ADDNOTA:
					incluirNota();
					break;

				case LISTALUNO:
					listarAlunos();
					break;

				case LISTCURSO:
					listarCursos();
					break;

				case LISTALUNOSCURSO:
					listarAlunosCurso();
					break;

				case FINDNOTASALUNO:
					listarCursosAluno();
					break;

				default:
					break;
			}
		} while (menuEnum != null);

		System.exit(1);
	}

	private static void listarAlunosCurso() {
		Curso curso = recuperarCurso();
		String mensagem = "";
		String titulo = "Curso ";
		try {
			titulo += curso.getId();
			mensagem += "Nome: " + curso.getNome() + " Código: " + curso.getCodigo();
			if (curso.getCursoAlunos() != null && curso.getCursoAlunos().size() > 0) {
				mensagem += "\n Aluno(s) do curso: ";
				for (CursoAluno c : curso.getCursoAlunos()) {
					mensagem += "\n Nome " + c.getAluno().getNome() + " Matrícula: " + c.getAluno().getMatricula();
				}
			}

		} catch (Exception e) {
			mensagem = "Curso não encontrado.";
		}
		JOptionPane.showMessageDialog(null, mensagem, titulo, JOptionPane.PLAIN_MESSAGE);
	}

	private static void listarCursosAluno() {
		Aluno aluno = recuperarAluno();
		String mensagem = "";
		String titulo = "Curso ";
		try {
			titulo += aluno.getId();
			mensagem += "Nome: " + aluno.getNome() + " Matrícula: " + aluno.getMatricula();
			if (aluno.getCursoAlunos() != null && aluno.getCursoAlunos().size() > 0) {
				mensagem += "\n Aluno(s) do aluno: ";
				for (CursoAluno c : aluno.getCursoAlunos()) {
					mensagem += "\n Nome " + c.getCurso().getNome() + " Nota: " + c.getNota();
				}
			}

		} catch (Exception e) {
			mensagem = "Aluno não encontrado.";
		}
		JOptionPane.showMessageDialog(null, mensagem, titulo, JOptionPane.PLAIN_MESSAGE);
	}

	private static void incluirAluno() {

		try {
			Aluno aluno = new Aluno();
			aluno.setNome(JOptionPane.showInputDialog("Informe o nome do aluno:"));
			aluno.setMatricula(JOptionPane.showInputDialog("Informe a matrícula do aluno:"));
			alunoDao.adicionar(aluno);
			JOptionPane.showMessageDialog(null, "Inclusão efetuada com sucesso!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Houve um erro ao incluir o aluno, tente novamente.");
			System.out.println(e.getMessage());
		}
	}

	private static void incluirCurso() {

		try {
			Curso curso = new Curso();
			curso.setNome(JOptionPane.showInputDialog("Informe o nome do curso:"));
			curso.setSigla(JOptionPane.showInputDialog("Informe a sigla do curso:"));
			curso.setCodigo(Integer.parseInt(JOptionPane.showInputDialog("Informe o codigo do curso:")));
			cursoDao.adicionar(curso);
			JOptionPane.showMessageDialog(null, "Inclusão efetuada com sucesso!");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Houve um erro ao incluir o aluno, tente novamente.");
			System.out.println(e.getMessage());
		}
	}

	private static Aluno buscarAlunoPorMatricula(String matricula) {
		return alunoDao.buscarPorMatricula(matricula);
	}

	private static Curso buscarCursoPorCodigo(int codigo) {
		return cursoDao.buscarPorCodigo(codigo);
	}

	private static void matricularAluno(){

		try {
			Curso curso = recuperarCurso();
			Aluno aluno = recuperarAluno();
			if(curso == null || aluno == null) {
				return;
			}
			if (adicionarCursoAluno(curso,aluno)) {
				JOptionPane.showMessageDialog(null, "Matrícula efetuada com sucesso!");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Houve um erro ao matricular o aluno, tente novamente.");
			System.out.println(e.getMessage());
		}

	}

	private static Aluno recuperarAluno() {
		Aluno aluno;
		int tentarNovamente = 1;
		do {
			String matricula = JOptionPane.showInputDialog("Informe a matrícula do aluno:");
			aluno = buscarAlunoPorMatricula(matricula);
			if(aluno == null) {
				tentarNovamente = JOptionPane.showOptionDialog(null, "Aluno não encontrado, deseja tentar novamente?", "Aluno",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			} else if(aluno == null && tentarNovamente == JOptionPane.NO_OPTION){
				return null;
			}
		} while (aluno == null && tentarNovamente == JOptionPane.YES_OPTION);
		return aluno;
	}

	private static boolean adicionarCursoAluno(Curso curso, Aluno aluno){
		//CursoAlunoPK pk = new CursoAlunoPK(aluno.getId(), curso.getId());
		CursoAluno cursoAluno = new CursoAluno(aluno, curso);
		if(aluno.getCursoAlunos() != null){
			for (CursoAluno c : aluno.getCursoAlunos()) {
				if (c.equals(cursoAluno)){
					JOptionPane.showMessageDialog(null, "Aluno já matriculado neste curso!");
					return false;
				}
			}
			aluno.getCursoAlunos().add(cursoAluno);
		} else {
			curso.setCursoAlunos(new HashSet<CursoAluno>());
			curso.getCursoAlunos().add(cursoAluno);
		}
		if (curso.getCursoAlunos() != null) {
			curso.getCursoAlunos().add(cursoAluno);
		} else {
			curso.setCursoAlunos(new HashSet<CursoAluno>());
			curso.getCursoAlunos().add(cursoAluno);
		}
		alunoDao.atualizar(aluno);
		cursoDao.atualizar(curso);
		return true;
	}

	private static Curso recuperarCurso() {
		Curso curso;
			int tentarNovamente = 1;
		do {
			String codigo = JOptionPane.showInputDialog("Informe o código do curso:");
			curso = buscarCursoPorCodigo(Integer.parseInt(codigo));
			if(curso == null) {
				tentarNovamente = JOptionPane.showOptionDialog(null, "Curso não encontrado, deseja tentar novamente?", "Curso",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			} else if(curso == null && tentarNovamente == JOptionPane.NO_OPTION){
				return null;
			}
		} while (curso == null && tentarNovamente == JOptionPane.YES_OPTION);
		return curso;
	}

	private static void incluirNota(){
		try {
			Curso curso = recuperarCurso();
			Aluno aluno = recuperarAluno();
			boolean notaAdicionada = false;
			for(CursoAluno c : aluno.getCursoAlunos()){
				if(c.getCurso().getId() == curso.getId()){
					String nota = JOptionPane.showInputDialog("Informe a nota do " + aluno.getNome());
					c.setNota(Long.parseLong(nota));
					alunoDao.atualizar(aluno);
					notaAdicionada = true;
					JOptionPane.showMessageDialog(null, "Nota adicionada com sucesso!");
					break;
				}
			}
			if(!notaAdicionada) {
				JOptionPane.showMessageDialog(null, "Aluno não matriculado no curso informado.");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Houve um erro ao matricular o aluno, tente novamente.");
			System.out.println(e.getMessage());
		}
	}

	private static void listarAlunos() {
		String mensagem = "";
		List<Aluno> alunos = alunoDao.listar();
		if (alunos.size() > 0) {
			mensagem += "Foram encontrados " + alunos.size() + " aluno(s)";
			for (Aluno aluno : alunos) {
				mensagem += "\n Aluno " + aluno.getId() + " Nome: " + aluno.getNome() + " Matrícula: "
						+ aluno.getMatricula();
			}
		} else {
			mensagem += "\n Não foram encontrados alunos.";
		}
		JOptionPane.showMessageDialog(null, mensagem, "Lista de alunos", JOptionPane.PLAIN_MESSAGE);
	}

	private static void listarCursos() {
		String mensagem = "";
		List<Curso> cursos = cursoDao.listar();
		if (cursos.size() > 0) {
			mensagem += "Foram encontrados " + cursos.size() + " curso(s)";
			for (Curso curso : cursos) {
				mensagem += "\n Curso " + curso.getId() + " Nome: " + curso.getNome() + " Código: "
						+ curso.getCodigo() + " Sigla: " + curso.getSigla();
			}
		} else {
			mensagem += "\n Não foram encontrados cursos.";
		}
		JOptionPane.showMessageDialog(null, mensagem, "Lista de cursos", JOptionPane.PLAIN_MESSAGE);
	}
}