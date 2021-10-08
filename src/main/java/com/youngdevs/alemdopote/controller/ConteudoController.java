package com.youngdevs.alemdopote.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.youngdevs.alemdopote.model.Conteudo;
import com.youngdevs.alemdopote.repository.ConteudoRepository;


@Controller
public class ConteudoController {

	@Autowired
	private ConteudoRepository repository;
	
	
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("conteudos");
		List<Conteudo> conteudos = repository.findAll();
		modelAndView.addObject("conteudos", conteudos);
		return modelAndView;
	}
	
	
	@GetMapping("/conteudo/new")
	public String create(Conteudo user) {
		return "conteudo-form";
	}
	
	@PostMapping("/conteudo")
	public String save(@Valid Conteudo conteudo, BindingResult result) {
		if(result.hasErrors()) {
			return "conteudo-form";
		}
		
		repository.save(conteudo);
		return "redirect:/";
	}
	
	@PostMapping("/conteudo/delete/{id}")
	public String delete(@PathVariable Long id){
		repository.deleteById(id);
		return "redirect:/";
	}
	
	@PostMapping("/conteudo/edit") 
	public String edit(@Valid Conteudo conteudo, BindingResult result){
		Optional<Conteudo> conteudoFromDb = repository.findById(conteudo.getId());
	    
		if(result.hasErrors()) {
			return "conteudo-form-edit";
		}
		
		if(conteudoFromDb.isPresent()) {
			Conteudo conteudoDb = conteudoFromDb.get();
			conteudoDb.setQuantIdealDiaria(conteudo.getQuantIdealDiaria());
			conteudoDb.setNome(conteudo.getNome());
		    repository.save(conteudoDb);
		}
		
		return "redirect:/";
	}
	
	
	@GetMapping("/conteudo/{id}")
	public ModelAndView show(@PathVariable Long id, Conteudo conteudo){
		ModelAndView modelAndView = new ModelAndView("conteudo-form-edit");
		Optional<Conteudo> conteudo1 = repository.findById(id);
		if(conteudo1.isPresent()) {
			modelAndView.addObject((Conteudo)conteudo1.get());
		}
		
		return modelAndView;
	}
	
}
