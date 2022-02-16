package br.com.alura.forum.controller.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.alura.forum.modelo.Cliente;
import br.com.alura.forum.repository.ClienteRepository;

public class ClienteForm {
	
	@NotNull @NotEmpty
	private String nome;
	private String palpite;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPalpite() {
		return palpite;
	}
	public void setPalpite(String palpite) {
		this.palpite = palpite;
	}
	
	public Cliente converter() {
		return new Cliente(nome, palpite);
	}
	
	public Cliente atualizar(Long id, ClienteRepository clienteRepository) {
		Cliente cliente = clienteRepository.getOne(id);
		cliente.setNome(this.nome);
		cliente.setPalpite(this.palpite);
		return cliente;
	}

}
