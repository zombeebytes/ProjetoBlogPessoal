package com.generation.blogPessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.blogPessoal.model.Postagem;
//PDA dá o poder para o repository se comunicar com o banco de dados
@Repository
public interface PostagemRepository extends JpaRepository <Postagem, Long> {
		List<Postagem> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);
		
}
