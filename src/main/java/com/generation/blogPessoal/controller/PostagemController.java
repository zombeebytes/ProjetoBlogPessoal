package com.generation.blogPessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogPessoal.model.Postagem;
import com.generation.blogPessoal.repository.PostagemRepository;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders="*")//informa toda e qualquer origem pode acessar essa rota
public class PostagemController {
	@Autowired //injeção de dependências; automatiçzacão 
private PostagemRepository postagemRepository; //chama o banco de dados

	@GetMapping
public ResponseEntity<List<Postagem>> getAll(){
		//respostas no formato http
return ResponseEntity.ok(postagemRepository.findAll());
//responseentity permite que eu escreva o código
//findAll = select * from 

}
	@GetMapping("/exemplo")
	public ResponseEntity<String> exemplo(){
		String ola = "Olá mundo";
		return ResponseEntity.ok(ola);
	}
}
