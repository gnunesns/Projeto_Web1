package com.ifce.br.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.ifce.br.model.Livro;
import com.ifce.br.service.LivroService;

@Controller
public class CarrinhoController {
	
	@Autowired
	private LivroService livroService;
	
	
	List<Livro> livros = new ArrayList<Livro>();
	
	@GetMapping("/livraria/adicionarCarrinho/{codigo}")
	public ModelAndView comprarLivro(@PathVariable Long codigo, Model model) {
		
		
		Optional<Livro> livroOptional = livroService.retornarPeloCodigo(codigo);
		
		Livro livro = livroOptional.get();
		
		
		livros.add(livro);
		System.out.println("Show:" + livros.get(0).getTitulo());
		
		ModelAndView mv = new ModelAndView("CarrinhoCompra");
		mv.addObject("listar", livros);
		
		double precoTotal = 0;
		
		
		for (Livro livro2 : livros) {
			precoTotal = precoTotal + livro2.getPreco();
		}
		
		mv.addObject("precoTotal", precoTotal);
		
		
		return mv;
		
	}
	


}
