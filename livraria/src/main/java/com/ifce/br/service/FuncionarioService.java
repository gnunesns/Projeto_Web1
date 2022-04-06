package com.ifce.br.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ifce.br.data.FuncionarioRepository;
import com.ifce.br.model.Funcionario;
import com.ifce.br.ultil.ImagemFileUtils;

@Service
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepository funReposy;
	
	// CADASTRA FUNCIONARIO //
	public void cadastrarfuncionario(Funcionario funcionario, MultipartFile imagem) {
		
		String caminho = "images/" + funcionario.getNome()+".png";
 		ImagemFileUtils.salvarImagem(caminho, imagem);
		
 		funReposy.save(funcionario);
	}
	
	// LISTA FUNCIONARIO //
	public Iterable<Funcionario> listarfuncionario(){
		return funReposy.findAll();
	}
	
	// DELETA FUNCIONARIO PELO ID //
	public void excluir(Long id) {
		funReposy.deleteById(id);
	}
	
	// RETORNA O FUNCIONARIO PELO ID //
	public Optional<Funcionario> retornarPeloCodigo(Long codigo){
		return funReposy.findById(codigo);
	}

}
