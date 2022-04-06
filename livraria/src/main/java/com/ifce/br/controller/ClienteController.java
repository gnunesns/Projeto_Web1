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

import com.ifce.br.model.Cliente;
import com.ifce.br.service.ClienteService;


@Controller
public class ClienteController {
	
	
	@Autowired
	private ClienteService clienteService;

	
	@GetMapping("/livraria/cadastroCliente")
	public String form(Model model) {
		
		model.addAttribute("cliente", new Cliente());
		return "CadastroCliente";
	}
	
	@PostMapping("/livraria/salvarCliente")
	public ModelAndView cadastrarCliente(@Validated  Cliente cliente, BindingResult result,  @RequestParam(value="imagem") MultipartFile imagem) {
		
		ModelAndView mv = new ModelAndView("CadastroCliente");
		
		if(result.hasErrors()) {
			return mv;
		}
		
		
		mv.addObject("mensagem", "Cadastrado com Sucesso!");
		clienteService.cadastrarCliente(cliente, imagem);
		
		
		return mv;
		//return "redirect:/livraria/listarCliente";
		
	}
	
	@GetMapping("/livraria/listarCliente")
	public String listarCliente(Model model) {
		// MANDA OS OBJETOS DA TABELA DO BANCO PARA O HTML //
		
		model.addAttribute("clientes", clienteService.listarCliente());
	
		return "ListagemCliente";
	}
	
	@GetMapping("/livraria/excluirCliente/{codigo}")
	public String excluirCliente(@PathVariable Long codigo) {
		
		// DELETA O CLIENTE ESCOLHIDO //
		// E REDIRECIONA PARA A PAGINA DE LISTAGEM //
		
		clienteService.excluirCliente(codigo);
		return "redirect:/livraria/listarCliente";
		
	}
	
	@GetMapping("/livraria/atualizarCliente/{codigo}")
	public String atualizarCliente(@PathVariable Long codigo, Model model) {
		
		// ATUALIZA O CLIENTE //
		// E REDIRECIONA PARA A PAGINA DE LISTAGEM //
	   
		Optional<Cliente> clienteOptional = clienteService.retornarPeloCodigo(codigo);
		
		Cliente cliente = clienteOptional.get();
		model.addAttribute("cliente", cliente);
		return "CadastroCliente";
		
	}

}
