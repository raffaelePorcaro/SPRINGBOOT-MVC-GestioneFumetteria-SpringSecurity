package com.example.demo.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.model.Fumetteria;
import com.example.demo.model.Manga; 

@Configuration
public class LoadDataBase {

	 private static final Logger log = LoggerFactory.getLogger(LoadDataBase.class);

	    @Bean
	    CommandLineRunner initDataBase(FumetteriaRepository fumetteriaRepository, MangaRepository mangaRepository) {
	        return args -> {
	            try {
	                Fumetteria fumetteria1 = fumetteriaRepository.save(new Fumetteria("Fumetteria Centrale", "Roma"));
	                Fumetteria fumetteria2 = fumetteriaRepository.save(new Fumetteria("Manga Mania", "Milano"));
	                Fumetteria fumetteria3 = fumetteriaRepository.save(new Fumetteria("Otaku Shop", "Torino"));
	                
	                
	                log.info("Fumetterie caricate: " + fumetteria1 + ", " + fumetteria2 + ", " + fumetteria3);

	                Manga manga1 = new Manga("One Piece", 7, List.of(fumetteria1, fumetteria2));
	                Manga manga2 = new Manga("Naruto", 6, List.of(fumetteria2));
	                Manga manga3 = new Manga("Dragon Ball", 5, List.of(fumetteria2, fumetteria3));

	                mangaRepository.save(manga1);
	                mangaRepository.save(manga2);
	                mangaRepository.save(manga3);
	                
	                log.info("manga caricati: " + manga1 + ", " + manga2 + ", " + manga3);
	             
	                
	                fumetteria1.setListaManga(List.of(manga1));
	                fumetteria2.setListaManga(List.of(manga1, manga2, manga3));
	                fumetteria3.setListaManga(List.of(manga3));

	                //salvo le fumetterie aggiornate con i manga associati
	                fumetteriaRepository.save(fumetteria1);
	                fumetteriaRepository.save(fumetteria2);
	                fumetteriaRepository.save(fumetteria3);
	                

	            
	            } catch (Exception e) {
	                e.printStackTrace();
	                log.info("Caricamento non riuscito: " + e.getClass().getSimpleName());
	            }
	            
	           /* String titolo = "One Piece";
	            log.info("Trovo il manga con titolo " + titolo + ": " + mangaRepository.findByNome(titolo)); */
	        };
	    }
	}

