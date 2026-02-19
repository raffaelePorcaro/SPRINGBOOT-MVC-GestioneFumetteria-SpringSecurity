package com.example.demo.model;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Manga {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String nome;
	private int prezzo;
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "fumetteria_manga",
			   joinColumns = @JoinColumn(name = "manga_id"), 
			   inverseJoinColumns = @JoinColumn(name = "fumetteria_id"))
	private List<Fumetteria> fumetterie;


	public Manga() {}


	public Manga(String nome, int prezzo) {
		this.nome = nome;
		this.prezzo = prezzo;
	}

	
	public Manga(String nome, int prezzo, List<Fumetteria> fumetterie) {
		this.nome = nome;
		this.prezzo = prezzo;
		this.fumetterie = fumetterie;
	}

	
	public Manga(int id, String nome, int prezzo, List<Fumetteria> fumetterie) {
		this.id = id;
		this.nome = nome;
		this.prezzo = prezzo;
		this.fumetterie = fumetterie;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public int getPrezzo() {
		return prezzo;
	}


	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}
	

	public List<Fumetteria> getFumetterie() {
		return fumetterie;
	}


	public void setFumetterie(List<Fumetteria> fumetterie) {
		this.fumetterie = fumetterie;
	}


	@Override
	public String toString() {
		return "Manga [nome=" + nome + ", prezzo=" + prezzo + "]";
	}

}
