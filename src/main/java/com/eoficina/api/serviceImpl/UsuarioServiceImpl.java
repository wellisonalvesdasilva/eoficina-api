package com.eoficina.api.serviceImpl;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.eoficina.api.dao.UsuarioDao;
import com.eoficina.api.dto.DtoUserWithoutProfile;
import com.eoficina.api.dto.DtoReturnPaginated;
import com.eoficina.api.model.Usuario;
import com.eoficina.api.service.UsuarioService;

@Service
public class UsuarioServiceImpl extends AbstractServiceImpl<Usuario> implements UsuarioService {

	@Autowired
	UsuarioDao _usuarioDao;

	public DtoReturnPaginated<DtoUserWithoutProfile> getAllPaginated(Map<String, String> objetoPesquisa) {
		return _usuarioDao.getAllPaginated(objetoPesquisa);
	}
/*
	public Usuario getById(Integer id) {
		return _usuarioDao.getById(id);
	}*/

	@Transactional
	public boolean updatePassword(Integer idUsuario, String novaSenha) {
		Usuario obj = _usuarioDao.loadById(idUsuario);
		if (obj != null) {
			obj.setSenha(novaSenha);
			_usuarioDao.merge(obj);
			// TODO: GRAVAR EM MD5
			System.out.println("Senha alterada.");
			return true;
		}
		return false;
	}

}
