	package com.example.demo.controller;
	import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.ModelAttribute;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestMethod;
	import com.example.demo.EsercitazioneSpringboot1Application;
	import com.example.demo.model.Fumetteria;
	import com.example.demo.model.Manga;
	import com.example.demo.service.InterfacciaService;
		
		@Controller
		public class FumetteriaController {
	
			private static final Logger logger = LoggerFactory.getLogger(EsercitazioneSpringboot1Application.class);
		
			@Autowired
			private InterfacciaService<Fumetteria> fumetteriaService;
			
			@Autowired
			private InterfacciaService<Manga> mangaService;
	
	
			
			@RequestMapping("/")
			public String homePage(Model model) {
			    logger.info("Homepage caricata");
			    model.addAttribute("listaManga", mangaService.getAllObjects());
			    model.addAttribute("listaFumetterie", fumetteriaService.getAllObjects());
			    return "index";
			}
		
		
			
		    @GetMapping("/fumetteriaForm")
		    public String fumetteriaForm(Model model) {
		        logger.info("Pagina Form fumetteria caricata");
		        Fumetteria fumetteria = new Fumetteria();
		        model.addAttribute("fumetteria", fumetteria);
		        return "formFumetteria"; 
		    }
	
		    
		    @RequestMapping(value = "/fumetteriaSalvata", method = RequestMethod.POST)
		    public String salvaFumetteria(@ModelAttribute("fumetteria") Fumetteria fumetteria) {
		        fumetteriaService.saveObject(fumetteria);
		        logger.info("Fumetteria con id " + fumetteria.getId() + " salvata con successo");
		        return "redirect:/";
		    }
	
	
		    @GetMapping("/eliminaFumetteria/{id}")
		    public String eliminaFumetteria(@PathVariable(value = "id") int id) {
		        this.fumetteriaService.deleteObjectById(id);
		        logger.info("Fumetteria con id " + id + " eliminata");
		        return "redirect:/";
		    }
		   
		    
		    @GetMapping("/aggiornaFumetteria/{id}")
		    public String aggiornaFumetteria(@PathVariable(value = "id") int id, Model model) {
		        Fumetteria fumetteria = fumetteriaService.getObjectById(id);
		        model.addAttribute("fumetteria", fumetteria);
		        return "aggiornamentoFumetteria"; 
		    }
		
	}
