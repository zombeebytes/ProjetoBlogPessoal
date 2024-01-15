package com.generation.blogPessoal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.blogPessoal.model.Postagem;
import com.generation.blogPessoal.repository.PostagemRepository;
import com.generation.blogPessoal.repository.TemaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders="*")//informa toda e qualquer origem pode acessar essa rota
public class PostagemController {
	@Autowired //injeção de dependências; automatiçzacão 
private PostagemRepository postagemRepository; //chama o banco de dados
	@Autowired
	private TemaRepository temaRepository;

	@GetMapping
public ResponseEntity<List<Postagem>> getAll(){
		//respostas no formato http
return ResponseEntity.ok(postagemRepository.findAll());
//responseentity permite que eu escreva o código
//findAll = select * from 

}
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getById(@PathVariable Long id){
		return postagemRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
	}
	@PostMapping //indicando que é um método post
	public ResponseEntity<Postagem> post(@Valid @RequestBody Postagem postagem){ 
		if(temaRepository.existsById(postagem.getTema().getId()))
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(postagemRepository.save(postagem));
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tema não existe", null);
	}
	@PutMapping 
	public ResponseEntity<Postagem> put(@Valid @RequestBody Postagem postagem){
		if(postagemRepository.existsById(postagem.getId())) {
			if(temaRepository.existsById(postagem.getTema().getId()))
		return ResponseEntity.status(HttpStatus.OK)
						.body(postagemRepository.save(postagem));
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tema não existe", null);

	}
	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();					
	}
				@ResponseStatus(HttpStatus.NO_CONTENT)
				@DeleteMapping("/{id}")
				public void delete(@PathVariable Long id) {
					Optional<Postagem> postagem = postagemRepository.findById(id);
					
					if(postagem.isEmpty()) 
						throw new ResponseStatusException(HttpStatus.NOT_FOUND);					
						postagemRepository.deleteById(id);
					
				}
	}

