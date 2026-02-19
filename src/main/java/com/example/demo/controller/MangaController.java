package com.example.demo.controller;
		
import java.util.List;
	
	import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.ModelAttribute;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
	import com.example.demo.EsercitazioneSpringboot1Application;
	import com.example.demo.model.Manga;
	import com.example.demo.service.InterfacciaService;
	
	
@Controller
public class MangaController {
	
	private static final Logger log = LoggerFactory.getLogger(EsercitazioneSpringboot1Application.class);
	
		
		@Autowired
		private InterfacciaService<Manga> mangaService;
		

		 	@GetMapping("/findAllManga")
		    public ModelAndView findAllManga() {
		        ModelAndView modelAndView = new ModelAndView();
		        log.info("Recupero la lista dei manga");
		        List<Manga> listaManga = mangaService.getAllObjects();
		        modelAndView.addObject("listaManga", listaManga);
		        modelAndView.setViewName("listaManga"); 
		        return modelAndView;
		    }
	
		 	
		    @GetMapping("/mangaForm")
		    public String mangaForm(Model model) {
		        log.info("Pagina Form manga caricata");
		        Manga manga = new Manga();
		        model.addAttribute("manga", manga);
		        return "formManga"; 
		    }
	
	
		    @RequestMapping(value = "/mangaSalvato", method = RequestMethod.POST)
		    public String salvaManga(@ModelAttribute("manga") Manga manga) {
		        mangaService.saveObject(manga);
		        log.info("Manga con id " + manga.getId() + " salvato con successo");
		        return "redirect:/";
		    }
	
	
		    @GetMapping("/eliminaManga/{id}")
		    public String eliminaManga(@PathVariable(value = "id") int id) {
		        mangaService.deleteObjectById(id);
		        log.info("Manga con id " + id + " eliminato");
		        return "redirect:/";
		    }
	
	
		    @GetMapping("/aggiornaManga/{id}")
		    public String aggiornaManga(@PathVariable(value = "id") int id, Model model) {
		        Manga manga = mangaService.getObjectById(id);
		        model.addAttribute("manga", manga);
		        return "aggiornamentoManga"; 
		    }
		
	
		}
		

	
