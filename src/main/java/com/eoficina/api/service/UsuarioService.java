package com.eoficina.api.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.eoficina.api.dto.DtoUserWithoutProfile;
import com.eoficina.api.dto.DtoReturnPaginated;
import com.eoficina.api.model.Usuario;

@Service
public interface UsuarioService extends GenericService<Usuario> {

	DtoReturnPaginated<DtoUserWithoutProfile> getAllPaginated(Map<String, String> objetoPesquisa);

	//Usuario getById(Integer id);

	boolean updatePassword(Integer idUsuario, String novaSenha);

}