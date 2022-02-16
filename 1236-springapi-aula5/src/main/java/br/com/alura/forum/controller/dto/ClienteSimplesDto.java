package br.com.alura.forum.controller.dto;

import org.springframework.data.domain.Page;

import br.com.alura.forum.modelo.Cliente;

public class ClienteSimplesDto {

	private String nome;
	private String palpite;

	public ClienteSimplesDto(Cliente cliente) {
		this.nome = cliente.getNome();
		this.palpite = cliente.getPalpite();

	}

	public String getNome() {
		return nome;
	}

	public String getPalpite() {
		return palpite;
	}

	public static Page<ClienteSimplesDto> converter(Page<Cliente> clientes) {
		return clientes.map(ClienteSimplesDto::new);
	}

}
