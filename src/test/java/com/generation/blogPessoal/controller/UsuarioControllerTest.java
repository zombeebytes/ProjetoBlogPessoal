package com.generation.blogPessoal.controller;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.generation.blogPessoal.model.Usuario;
import com.generation.blogPessoal.repository.UsuarioRepository;
import com.generation.blogPessoal.service.UsuarioService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//define a porta utilizada para os testes -> porta aleatória(random.port)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//ciclo de vida do teste definido por classe
public class UsuarioControllerTest {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private TestRestTemplate testRestTemplate;
	// requisições http

	@BeforeAll
	void start() {
		// start -> primeiro a ser executado
		usuarioRepository.deleteAll();
		// deleteAll -> deleta todos os dados da tabela usuario

		usuarioService.cadastrarUsuario(new Usuario(0L, "Root", "root@root.com", "rootroot", ""));

	}

	// nosso teste que cadastra um usuário
	@Test
	@DisplayName("Cadastra um usuário")
	public void deveCriarUmUsuario() {
		HttpEntity<Usuario> corpoRequisicao = new HttpEntity<Usuario>(
				new Usuario(0L, "Thor Odinson", "thor@odinson.com", "mynameisthor", ""));

		ResponseEntity<Usuario> corpoResposta = testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST,
				corpoRequisicao, Usuario.class);

		Assertions.assertEquals(HttpStatus.CREATED, corpoResposta.getStatusCode());

	}

	@Test
	@DisplayName("Não deve permitir duplicação de usuário.")
	public void naoDeveDuplicarUsuario() {

		usuarioService.cadastrarUsuario(new Usuario(0L, "Loki Odinson", "Loki@odinson.com", "mynameisloki", ""));

		HttpEntity<Usuario> corpoRequisicao = new HttpEntity<Usuario>(
				new Usuario(0L, "Loki Odinson", "Loki@odinson.com", "mynameisloki", ""));

		ResponseEntity<Usuario> corpoResposta = testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST,
				corpoRequisicao, Usuario.class);
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, corpoResposta.getStatusCode());
	}

	@Test
	@DisplayName("Atualizar usuário")
	public void deveAtualizarUmUsuario() {

		Optional<Usuario> usuarioCadastrado = usuarioService
				.cadastrarUsuario(new Usuario(0L, "Steve Rogers", "steve_rogers@marvel.com", "steverogers", ""));

		Usuario usuarioUpdate = new Usuario(usuarioCadastrado.get().getId(), "Steve Rogers Carter",
				"steverogerscarter@marvel.com", "steverogers", "");

		HttpEntity<Usuario> corpoRequisicao = new HttpEntity<Usuario>(usuarioUpdate);
		ResponseEntity<Usuario> corpoResposta = testRestTemplate.withBasicAuth("root@root.com", "rootroot")
				.exchange("/usuarios/atualizar", HttpMethod.PUT, corpoRequisicao, Usuario.class);
		Assertions.assertEquals(HttpStatus.OK, corpoResposta.getStatusCode());
	}

	@Test
	@DisplayName("Listar todos os usuários")
	public void deveMostrarTodosUsuario() {

		usuarioService.cadastrarUsuario(new Usuario(0L, "Tony Stark", "ironman@marvel.com", "jarvis123", ""));
		usuarioService.cadastrarUsuario(
				new Usuario(0L, "Carol Susan Jane Denvers", "caroldenvers@marvel.com", "caroldenvers19", ""));

		ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("root@root.com", "rootroot")
				.exchange("/usuarios/all", HttpMethod.GET, null, String.class);
		Assertions.assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
}
