package br.com.alura.forum.controller;

import java.net.URI;
import java.util.Optional;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.alura.forum.controller.dto.ClienteDto;
import br.com.alura.forum.controller.dto.ClienteSimplesDto;
import br.com.alura.forum.controller.dto.DetalheTopicoDto;
import br.com.alura.forum.controller.form.ClienteForm;
import br.com.alura.forum.modelo.Cliente;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.ClienteRepository;

@RestController
@RequestMapping("/cliente")
public class ClientesController {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping
	@Cacheable(value = "listarClientes")
	public Page<ClienteDto> listar(@RequestParam (required = false) String nome, @PageableDefault(sort = "nome", direction = Direction.ASC, page=0, size=200 ) Pageable paginacao) {
		//Para testar o pageable na url, page= size= sort=id,asc || desc
		
		if(nome == null) {
			Page<Cliente> clientes = clienteRepository.findAll(paginacao);
			return ClienteDto.converter(clientes);
		} else {
			Page<Cliente> clientes = clienteRepository.findByNome(nome, paginacao);
			return ClienteDto.converter(clientes);
		}
	}
	
	
	@PostMapping
	@Transactional
	@CacheEvict(value = "listarClientes", allEntries = true)
	public ResponseEntity<ClienteDto> cadastrar(@RequestBody @Valid ClienteForm form, UriComponentsBuilder uriBuilder) {
		Cliente cliente = form.converter();
		clienteRepository.save(cliente);
		
		URI uri = uriBuilder.path("/user/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).body(new ClienteDto(cliente));
	}
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listarClientes", allEntries = true)
	public ResponseEntity<ClienteDto> atualizar(@PathVariable Long id, @RequestBody @Valid ClienteForm form) {
		Optional<Cliente> optional = clienteRepository.findById(id);
		if(optional.isPresent()) {
			Cliente cliente = form.atualizar(id, clienteRepository);
			return ResponseEntity.ok(new ClienteDto(cliente));
		}
		return ResponseEntity.notFound().build();
	}
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listarClientes", allEntries = true)
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Cliente> optional = clienteRepository.findById(id);
		if(optional.isPresent()) {
			clienteRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
