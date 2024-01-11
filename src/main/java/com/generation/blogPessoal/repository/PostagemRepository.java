package com.generation.blogPessoal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.blogPessoal.model.Postagem;
//PDA dá o poder para o repository se comunicar com o banco de dados
public interface PostagemRepository extends JpaRepository <Postagem, Long> {

}
