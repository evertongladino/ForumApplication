package br.com.alura.forum.controller.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.alura.forum.modelo.Cliente;

public class ClienteDto {

	private Long id;
	private String nome;
	private String palpite;
	private LocalDateTime dataCriacao;

	public ClienteDto(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.palpite = cliente.getPalpite();
		this.dataCriacao = cliente.getDataCriacao();

	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getPalpite() {
		return palpite;
	}
	
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public static Page<ClienteDto> converter(Page<Cliente> clientes) {
		return clientes.map(ClienteDto::new);
	}

}
