package com.itaca.callcenter.web.model;

import com.itaca.callcenter.domain.entities.Usuario;
import com.itaca.callcenter.services.UsuarioService;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;


public class UsuarioLazyDataModel extends LazyDataModel<Usuario> {

	private static final long serialVersionUID = 1663626999381683893L;
	private transient UsuarioService usuarioService;
	private List<Usuario> usuarios;

	public UsuarioLazyDataModel(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
		setRowCount(usuarioService.countAll());
	}

	@Override
	public Usuario getRowData(String rowKey) {
		for (Usuario usuario : usuarios) {
			if (usuario.getId().equals(Long.valueOf(rowKey))) {
				return usuario;
			}
		}
		return null;
	}

	
	@Override
	public Object getRowKey(Usuario usuario) {
		return usuario.getId();
	}
	
	@Override
	public List<Usuario> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		usuarios = usuarioService.getResultList(first, pageSize);
		return usuarios;
	}
        
}
