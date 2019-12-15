package com.eoficina.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eoficina.api.model.Perfil;
import com.eoficina.api.service.PerfilService;


@RestController
@CrossOrigin
@RequestMapping(value = "/api/perfis")
public class PerfilController {

	@Autowired
	PerfilService _perfilService;

	@RequestMapping(method = RequestMethod.GET)
	public List<Perfil> getAll() {
		return _perfilService.getAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Perfil> getById(@PathVariable(value = "id") Integer id) {
		Perfil perfil = _perfilService.getById(id);
		return ResponseEntity.ok().body(perfil);
	}

	@RequestMapping(method = RequestMethod.POST)
	public void created(@RequestBody Perfil dto) {
		_perfilService.created(dto);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void updatedById(@RequestBody Perfil dto, @PathVariable("id") Integer id) {
		_perfilService.updatedById(id, dto);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public boolean deleteById(@PathVariable("id") Integer id) {
		return _perfilService.deleteById(id);
	}

}
