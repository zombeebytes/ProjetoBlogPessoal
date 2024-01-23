package com.generation.blogPessoal.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.generation.blogPessoal.model.Usuario;

public class UserDetailsImpl implements UserDetails {
//usado para falar para a segurança quais são as informações que vamos usar para fazer login

	private static final long serialVersionUID = 1L;
	// como se fosse uma variável local. (identificador da versão da classe)
	private String userName;
	private String password;
	private List<GrantedAuthority> authorities;
	// essa classe consegue passar os niveis de acesso do usuario.

	public UserDetailsImpl(Usuario user) {
		this.userName = user.getUsuario();
		this.password = user.getSenha();
//metodo construtor esta passando o usuario e senha informados para os atributors dessa classe.
	}

	public UserDetailsImpl() {
//metodo construtor vazio só para garantir o funcionamento da api mesmo quando não receber esses dados.
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
