package com.ifce.br.controller;

//import java.util.ArrayList;
//import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndView;

import com.ifce.br.model.Livro;
import com.ifce.br.service.LivroService;

@Controller
public class LivroController {
	
	@Autowired
	private LivroService livroService;
	
	@GetMapping("/livraria/cadastroLivro")
	public String form(Model model) {
		
		model.addAttribute("livro", new Livro());
		return "CadastroLivro";
	}
	
	@PostMapping("/livraria/salvarLivro")
	public ModelAndView cadastrarLivro( @Validated Livro livro,  BindingResult result, @RequestParam(value="imagem") MultipartFile imagem) {
		
       ModelAndView mv = new ModelAndView("CadastroLivro");
		
		if(result.hasErrors()) {
			return mv;
		}
		
		mv.addObject("mensagem", "Cadastrado com Sucesso!");
		livroService.cadastrarLivro(livro, imagem);
		
		return mv;
		//return "redirect:/livraria/listarLivro";
		
	}
	
	@GetMapping("/livraria/listarLivro")
	public String listarLivro(Model model) {
		// MANDA OS OBJETOS DA TABELA DO BANCO PARA O HTML //
		
		model.addAttribute("livros", livroService.listarLivro());
	
		return "ListagemLivro";
	}
	
	@GetMapping("/livraria/excluirLivro/{codigo}")
	public String excluirLivro(@PathVariable Long codigo) {
		
		// DELETA O CLIENTE ESCOLHIDO //
		// E REDIRECIONA PARA A PAGINA DE LISTAGEM //
		
		livroService.excluir(codigo);
		return "redirect:/livraria/listarLivro";
		
	}
	
	@GetMapping("/livraria/atualizarLivro/{codigo}")
	public String atualizarLivro(@PathVariable Long codigo, Model model) {
		
		// ATUALIZA O LIVRO //
		// E REDIRECIONA PARA A PAGINA DE LISTAGEM //
	   
		Optional<Livro> livroOptional = livroService.retornarPeloCodigo(codigo);
		
		Livro livro = livroOptional.get();
		model.addAttribute("livro", livro);
		return "CadastroLivro";
		
	}
	
//	@GetMapping("/livraria/comprarLivro/{codigo}")
//	public ModelAndView comprarLivro(@PathVariable Long codigo, Model model) {
//		
//		
//		Optional<Livro> livroOptional = livroService.retornarPeloCodigo(codigo);
//		
//		Livro livro = livroOptional.get();
//		
//		List<Livro> livros = new ArrayList<Livro>();
//		livros.add(livro);
//		
//		ModelAndView mv = new ModelAndView("CarrinhoCompra");
//		mv.addObject("listar", livros);
//		
//		double precoTotal = 0;
//		
//		for (Livro livro2 : livros) {
//			precoTotal = precoTotal + livro2.getPreco();
//		}
//		
//		mv.addObject("precoTotal", precoTotal);
//		
//		
//		return mv;
//		
//	}

}
