package com.eoficina.api.daoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.eoficina.api.dao.UsuarioDao;
import com.eoficina.api.dto.DtoUserWithoutProfile;
import com.eoficina.api.dto.DtoReturnPaginated;
import com.eoficina.api.model.Perfil;
import com.eoficina.api.model.Usuario;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;

@Repository
@Transactional
public class UsuarioDAOImpl extends AbstractJpaDAOImpl<Usuario> implements UsuarioDao {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DtoReturnPaginated<DtoUserWithoutProfile> getAllPaginated(Map<String, String> parametros) {
		DtoReturnPaginated retorno = new DtoReturnPaginated();
		createCriteria();
		List<Predicate> filtros = new ArrayList();

		/* FILTROS */
		if (parametros.get("nome") != null && parametros.get("nome") != "") {
			filtros.add(cb.equal(root.get("nome"), parametros.get("nome")));
		}
		if (parametros.get("login") != null && parametros.get("login") != "") {
			filtros.add(cb.equal(root.get("login"), parametros.get("login")));
		}
		if (parametros.get("email") != null && parametros.get("email") != "") {
			filtros.add(cb.equal(root.get("email"), parametros.get("email")));
		}

		/* ORDER BY */
		query.select(root).where(filtros.toArray(new Predicate[filtros.size()]));
		List<Order> orderList = new ArrayList();
		if (parametros.get("tipoOrdenacao").equals("asc")) {
			orderList.add(cb.asc(root.get(parametros.get("coluna"))));
		} else {
			orderList.add(cb.desc(root.get(parametros.get("coluna"))));
		}
		query.orderBy(orderList);

		/* RETORNO */
		List<DtoUserWithoutProfile> listaDto = new ArrayList<DtoUserWithoutProfile>();
		for (Usuario item : getResultListPagging(Integer.parseInt(parametros.get("offset")),
				Integer.parseInt(parametros.get("offset")) + Integer.parseInt(parametros.get("limit")))) {
			DtoUserWithoutProfile obj = new DtoUserWithoutProfile();
			obj.setNome(item.getNome());
			obj.setEmail(item.getEmail());
			obj.setId(item.getId());
			obj.setDthInclusao(item.getDthInclusao());
			obj.setLogin(item.getLogin());
			listaDto.add(obj);
		}
		retorno.setLista(listaDto);
		retorno.setRecordsTotal(getResultList().size());
		return retorno;
	}

	/*
	public Usuario getById(Integer Id) {
		createCriteria();
		List<Predicate> filtros = new ArrayList();
		Join<Usuario, Perfil> perfil = root.join("perfis");
		filtros.add(cb.equal(root.get("id"), Id));
		query.select(root).where(filtros.toArray(new Predicate[filtros.size()]));
		List<Usuario> lista = getResultList();
		return (lista.size() > 0) ? lista.get(0) : null;
	}*/
}