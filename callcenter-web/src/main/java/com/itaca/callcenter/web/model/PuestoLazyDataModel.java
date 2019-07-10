package com.itaca.callcenter.web.model;

import com.itaca.callcenter.domain.entities.K_puesto;
import com.itaca.callcenter.services.PuestoService;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Operacion
 */
public class PuestoLazyDataModel extends LazyDataModel<K_puesto> {
        private static final long serialVersionUID = 1663626999381683893L;
        private transient PuestoService puestoService;
    private List<K_puesto> puestos;
    
    public PuestoLazyDataModel(PuestoService puestoService) {
        this.puestoService = puestoService;
        setRowCount(puestoService.countAll());
    }
    
    @Override
    public K_puesto getRowData(String rowKey) {
        for (K_puesto puesto : puestos) {
            if (puesto.getId().equals(Integer.valueOf(rowKey))) {
                return puesto;
            }
        }
        return null;
    }
    
    @Override
    public Object getRowKey(K_puesto puesto) {
        return puesto.getId();
    }
    
    @Override
    public List<K_puesto> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        puestos = puestoService.getResultList(first, pageSize);
        return puestos;
    }
    
}
