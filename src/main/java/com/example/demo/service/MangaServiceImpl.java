package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Manga;
import com.example.demo.repository.MangaRepository;

@Service
public class MangaServiceImpl implements InterfacciaService<Manga>{
	
	@Autowired
	private MangaRepository mangaRepository;

	
	@Override
	public List<Manga> getAllObjects() {
		List<Manga> listaManga = mangaRepository.findAll();
		return listaManga;
	}

	
	@Override
	public Manga getObjectById(int id) {
		Manga manga = mangaRepository.findById(id).orElse(null);
		return manga;
	}

	
	@Override
	public void saveObject(Manga object) {
		mangaRepository.save(object);		
	}

	
	@Override
	public void deleteObjectById(int id) {
		mangaRepository.deleteById(id);		
	}
	
	
	public Manga findByNome(String nome) {
		Manga libro = mangaRepository.findByNome(nome);
		return libro;
	}
	
}
