package com.ifce.br.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.ifce.br.model.Funcionario;
import com.ifce.br.service.FuncionarioService;

@Controller
public class FuncionarioController {

	
	@Autowired
	private FuncionarioService funcionarioService;
	
	
	
	@GetMapping("/livraria/cadastroFuncionario")
	public String form(Model model) {
		
		model.addAttribute("funcionario", new Funcionario());
		return "CadastroFuncionario";
	}
	
	@PostMapping("/livraria/salvarFuncionario")
	public ModelAndView cadastrarFuncionario( @Validated Funcionario funcionario,  BindingResult result,  @RequestParam(value="imagem") MultipartFile imagem) {
		
		
       ModelAndView mv = new ModelAndView("CadastroFuncionario");
		
		if(result.hasErrors()) {
			return mv;
		}
		
		mv.addObject("mensagem", "Cadastrado com Sucesso!");
		funcionarioService.cadastrarfuncionario(funcionario, imagem);
		
		return mv;
		//return "redirect:/livraria/listarFuncionario";
		
	}
	
	@GetMapping("/livraria/listarFuncionario")
	public String listarFuncionario(Model model) {
		// MANDA OS OBJETOS DA TABELA DO BANCO PARA O HTML //
		
		model.addAttribute("funcionarios", funcionarioService.listarfuncionario());
		
		return "ListagemFuncionario";
	}
	
	@GetMapping("/livraria/excluirFuncionario/{codigo}")
	public String excluirFuncionario(@PathVariable Long codigo) {
		
		// DELETA O FUNCIONARIO ESCOLHIDO //
		// E REDIRECIONA PARA A PAGINA DE LISTAGEM //
		
		funcionarioService.excluir(codigo);
		return "redirect:/livraria/listarFuncionario";
		
	}
	
	@GetMapping("/livraria/atualizarFuncionario/{codigo}")
	public String atualizarFuncionario(@PathVariable Long codigo, Model model) {
		
		// ATUALIZA O FUNCIONARIO //
		// E REDIRECIONA PARA A PAGINA DE LISTAGEM //
	   
		Optional<Funcionario> funcionarioOptional = funcionarioService.retornarPeloCodigo(codigo);
		
		Funcionario funcionario = funcionarioOptional.get();
		model.addAttribute("funcionario", funcionario);
		return "CadastroFuncionario";
		
	}
	
	
}
