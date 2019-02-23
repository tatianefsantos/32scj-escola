package br.com.fiap.helper;

public enum MenuEnum {
    ADDALUNO("Incluir aluno"),
    ADDCURSO("Incluir curso"),
    MATRICULA("Matricular aluno"),
    ADDNOTA("Incluir nota"),
    LISTALUNO("Listar alunos"),
    LISTCURSO("Listar cursos"),
    LISTALUNOSCURSO("Listar alunos por curso"),
    FINDNOTASALUNO("Buscar notas do aluno");

    private String descricao;

    MenuEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static MenuEnum getEnumByString(String code){
        for(MenuEnum e : MenuEnum.values()){
            if(code == e.descricao) return e;
        }
        return null;
    }
}
