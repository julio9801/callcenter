package com.itaca.callcenter.web.model;

import com.itaca.callcenter.domain.entities.K_tipo;
import com.itaca.callcenter.services.TipoService;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Operacion
 */
public class TipoLazyDataModel extends LazyDataModel<K_tipo> {
        private static final long serialVersionUID = 1663626999381683893L;
        private transient TipoService tipoService;
    private List<K_tipo> tipos;
    
    public TipoLazyDataModel(TipoService tipoService) {
        this.tipoService = tipoService;
        setRowCount(tipoService.countAll());
    }
    
    @Override
    public K_tipo getRowData(String rowKey) {
        for (K_tipo tipo : tipos) {
            if (tipo.getId().equals(Integer.valueOf(rowKey))) {
                return tipo;
            }
        }
        return null;
    }
    
    @Override
    public Object getRowKey(K_tipo tipo) {
        return tipo.getId();
    }
    
    @Override
    public List<K_tipo> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        tipos = tipoService.getResultList(first, pageSize);
        return tipos;
    }
    
}
