package com.eoficina.api.daoImpl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.eoficina.api.dao.PerfilDAO;
import com.eoficina.api.model.Perfil;

@Repository
@Transactional
public class PerfilDAOImpl extends AbstractJpaDAOImpl<Perfil> implements PerfilDAO {

}