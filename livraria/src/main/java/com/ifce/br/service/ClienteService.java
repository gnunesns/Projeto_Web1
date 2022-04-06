package com.ifce.br.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ifce.br.data.ClienteRepository;
import com.ifce.br.model.Cliente;
import com.ifce.br.ultil.ImagemFileUtils;


@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteReposy;
	
	// CADASTRA O CLIENTE //
	public void cadastrarCliente(Cliente cliente, MultipartFile imagem) {
		
		String caminho = "images/" + cliente.getNome()+".png";
 		ImagemFileUtils.salvarImagem(caminho, imagem);
		
 		clienteReposy.save(cliente);
		
	}
	
	// LISTA O CLIENTE //
	public Iterable<Cliente> listarCliente(){
		return clienteReposy.findAll();
		
	}
	
	// EXCLUI O CLIENTE //
	public void excluirCliente(Long id) {
		clienteReposy.deleteById(id);
	}
	
	// RETORNA O CLIENTE PELO O CODIGO //
	public Optional<Cliente> retornarPeloCodigo(Long codigo){
		return clienteReposy.findById(codigo);
		
	}
}
