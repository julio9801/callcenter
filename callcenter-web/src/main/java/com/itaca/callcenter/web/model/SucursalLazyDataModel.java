package com.itaca.callcenter.web.model;

import com.itaca.callcenter.domain.entities.K_sucursal;
import com.itaca.callcenter.services.SucursalService;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Operacion
 */
public class SucursalLazyDataModel extends LazyDataModel<K_sucursal> {
        private static final long serialVersionUID = 1663626999381683893L;
        private transient SucursalService sucursalService;
    private List<K_sucursal> sucursals;
    
    public SucursalLazyDataModel(SucursalService sucursalService) {
        this.sucursalService = sucursalService;
        setRowCount(sucursalService.countAll());
    }
    
    @Override
    public K_sucursal getRowData(String rowKey) {
        for (K_sucursal sucursal : sucursals) {
            if (sucursal.getId().equals(Integer.valueOf(rowKey))) {
                return sucursal;
            }
        }
        return null;
    }
    
    @Override
    public Object getRowKey(K_sucursal sucursal) {
        return sucursal.getId();
    }
    
    @Override
    public List<K_sucursal> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        sucursals = sucursalService.getResultList(first, pageSize);
        return sucursals;
    }
    
}
