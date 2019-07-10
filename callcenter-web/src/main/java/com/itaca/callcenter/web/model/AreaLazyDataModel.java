package com.itaca.callcenter.web.model;

import com.itaca.callcenter.domain.entities.K_area;
import com.itaca.callcenter.services.AreaService;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Operacion
 */
public class AreaLazyDataModel extends LazyDataModel<K_area> {
        private static final long serialVersionUID = 1663626999381683893L;
        private transient AreaService areaService;
    private List<K_area> areas;
    
    public AreaLazyDataModel(AreaService areaService) {
        this.areaService = areaService;
        setRowCount(areaService.countAll());
    }
    
    @Override
    public K_area getRowData(String rowKey) {
        for (K_area area : areas) {
            if (area.getId().equals(Integer.valueOf(rowKey))) {
                return area;
            }
        }
        return null;
    }
    
    @Override
    public Object getRowKey(K_area area) {
        return area.getId();
    }
    
    @Override
    public List<K_area> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        areas = areaService.getResultList(first, pageSize);
        return areas;
    }
    
}
