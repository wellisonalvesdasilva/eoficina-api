package com.eoficina.api.dao;

import java.util.Map;

import com.eoficina.api.dto.DtoUserWithoutProfile;
import com.eoficina.api.dto.DtoReturnPaginated;
import com.eoficina.api.model.Usuario;

public interface UsuarioDao extends GenericDAO<Usuario> {

	DtoReturnPaginated<DtoUserWithoutProfile> getAllPaginated(Map<String, String> parametros);

	//Usuario getById(Integer Id);

}
