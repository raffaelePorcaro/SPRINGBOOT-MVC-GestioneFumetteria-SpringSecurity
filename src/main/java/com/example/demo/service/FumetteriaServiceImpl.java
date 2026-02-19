package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Fumetteria;
import com.example.demo.repository.FumetteriaRepository;


@Service
public class FumetteriaServiceImpl implements InterfacciaService<Fumetteria>{

	@Autowired
	private FumetteriaRepository fumetteriaRepository;

	
	@Override
	public List<Fumetteria> getAllObjects() {
		List<Fumetteria> listaFumetteria = fumetteriaRepository.findAll();
		return listaFumetteria;
	}

	
	@Override
	public Fumetteria getObjectById(int id) {
		Fumetteria fumetteria = fumetteriaRepository.findById(id).orElse(null);
		return fumetteria;
	}

	@Override
	public void saveObject(Fumetteria object) {
		fumetteriaRepository.save(object);	
		
	}

	@Override
	public void deleteObjectById(int id) {
		fumetteriaRepository.deleteById(id);	
		
	}


}
