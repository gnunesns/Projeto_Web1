package com.ifce.br.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ifce.br.data.LivroRepository;
import com.ifce.br.model.Livro;
import com.ifce.br.ultil.ImagemFileUtils;


@Service
public class LivroService {
	
	@Autowired
	private LivroRepository livroReposy;
	
	// CADASTRA E SALVA O LIVRO //
	public void cadastrarLivro(Livro livro,  MultipartFile imagem) {
		
		String caminho = "images/" + livro.getTitulo()+".png";
 		ImagemFileUtils.salvarImagem(caminho, imagem);
		
 		livroReposy.save(livro);
			
	}
	
	
	// LISTA O LIVRO //
	public Iterable<Livro> listarLivro(){
		return livroReposy.findAll();
			
	}
	
	// EXCLUI O LIVRO //
	public void excluir(Long id) {
		livroReposy.deleteById(id);
	}
		
	// RETORNA O LIVRO PELO O CODIGO //
	public Optional<Livro> retornarPeloCodigo(Long codigo){
		return livroReposy.findById(codigo);
			
	}

	

}
