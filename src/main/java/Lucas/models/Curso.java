package Lucas.models;

public class Curso {

	private String nome;
	
	private String autor;
	
	private Integer cargaHoraria;

	public Curso(String nome, String autor, Integer cargaHoraria) {
		this.nome = nome;
		this.autor = autor;
		this.cargaHoraria = cargaHoraria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Integer getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(Integer cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	
	
	
}
