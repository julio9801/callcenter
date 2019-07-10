package com.itaca.callcenter.web.model;

import com.itaca.callcenter.domain.entities.K_correo;
import com.itaca.callcenter.services.CorreoService;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Operacion
 */
public class CorreoLazyDataModel extends LazyDataModel<K_correo> {
        private static final long serialVersionUID = 1663626999381683893L;
        private transient CorreoService correoService;
    private List<K_correo> correos;
    
    public CorreoLazyDataModel(CorreoService correoService) {
        this.correoService = correoService;
        setRowCount(correoService.countAll());
    }
    
    @Override
    public K_correo getRowData(String rowKey) {
        for (K_correo correo : correos) {
            if (correo.getId().equals(Integer.valueOf(rowKey))) {
                return correo;
            }
        }
        return null;
    }
    
    @Override
    public Object getRowKey(K_correo correo) {
        return correo.getId();
    }
    
    @Override
    public List<K_correo> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        correos = correoService.getResultList(first, pageSize);
        return correos;
    }
    
}
