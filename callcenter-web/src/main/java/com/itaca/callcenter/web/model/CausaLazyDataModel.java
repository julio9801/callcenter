package com.itaca.callcenter.web.model;

import com.itaca.callcenter.domain.entities.K_causa;
import com.itaca.callcenter.services.CausaService;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Operacion
 */
public class CausaLazyDataModel extends LazyDataModel<K_causa> {
        private static final long serialVersionUID = 1663626999381683893L;
        private transient CausaService causaService;
    private List<K_causa> causas;
    
    public CausaLazyDataModel(CausaService causaService) {
        this.causaService = causaService;
        setRowCount(causaService.countAll());
    }
    
    @Override
    public K_causa getRowData(String rowKey) {
        for (K_causa causa : causas) {
            if (causa.getId().equals(Integer.valueOf(rowKey))) {
                return causa;
            }
        }
        return null;
    }
    
    @Override
    public Object getRowKey(K_causa causa) {
        return causa.getId();
    }
    
    @Override
    public List<K_causa> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        causas = causaService.getResultList(first, pageSize);
        return causas;
    }
    
}
