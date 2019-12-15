package com.eoficina.api.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eoficina.api.dto.DtoUserWithoutProfile;
import com.eoficina.api.dto.DtoReturnPaginated;
import com.eoficina.api.model.Usuario;
import com.eoficina.api.service.UsuarioService;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/usuarios")
public class UsuarioController {

	@Autowired
	UsuarioService _usuarioService;

	@RequestMapping(method = RequestMethod.GET)
	public DtoReturnPaginated<DtoUserWithoutProfile> getAll(@RequestParam Map<String, String> parametros) {
		return _usuarioService.getAllPaginated(parametros);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> getById(@PathVariable(value = "id") Integer id) {
		Usuario usuario = _usuarioService.getById(id);
		return ResponseEntity.ok().body(usuario);
	}

	@RequestMapping(method = RequestMethod.POST)
	public void created(@RequestBody Usuario dto) {
		_usuarioService.created(dto);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void updatedById(@RequestBody Usuario dto, @PathVariable("id") Integer id) {
		_usuarioService.updatedById(id, dto);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public boolean deleteById(@PathVariable("id") Integer id) {
		return _usuarioService.deleteById(id);
	}

	@RequestMapping(value = "/alterarSenha/{id}", method = RequestMethod.PUT)
	public boolean updatePassword(@RequestBody String novaSenha, @PathVariable("id") Integer id) {
		return _usuarioService.updatePassword(id, novaSenha);
	}
}
