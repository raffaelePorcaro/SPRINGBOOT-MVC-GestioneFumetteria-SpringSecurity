package com.example.demo.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Fumetteria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String nome;
	private String citta;
	
	
	@ManyToMany(mappedBy= "fumetterie",fetch = FetchType.EAGER)
	List<Manga> listaManga;


	public Fumetteria() {}

	
	
	public Fumetteria(String nome, String citta, List<Manga> listaManga) {
		this.nome = nome;
		this.citta = citta;
		this.listaManga = listaManga;
	}
	
	
	public Fumetteria(String nome, String citta) {
		this.nome = nome;
		this.citta = citta;
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



	public String getCitta() {
		return citta;
	}



	public void setCitta(String citta) {
		this.citta = citta;
	}



	public List<Manga> getListaManga() {
		return listaManga;
	}



	public void setListaManga(List<Manga> listaManga) {
		this.listaManga = listaManga;
	}



	@Override
	public String toString() {
		return "Fumetteria [nome=" + nome + ", citta=" + citta + "]";
	}

}
