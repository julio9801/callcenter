package com.itaca.callcenter.web.model;

import com.itaca.callcenter.domain.entities.K_reportador;
import com.itaca.callcenter.services.ReportadorService;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Operacion
 */
public class ReportadorLazyDataModel extends LazyDataModel<K_reportador> {
        private static final long serialVersionUID = 1663626999381683893L;
        private transient ReportadorService reportadorService;
    private List<K_reportador> reportadores;
    
    public ReportadorLazyDataModel(ReportadorService reportadorService) {
        this.reportadorService = reportadorService;
        setRowCount(reportadorService.countAll());
    }
    
    @Override
    public K_reportador getRowData(String rowKey) {
        for (K_reportador reportador : reportadores) {
            if (reportador.getId().equals(Integer.valueOf(rowKey))) {
                return reportador;
            }
        }
        return null;
    }
    
    @Override
    public Object getRowKey(K_reportador reportador) {
        return reportador.getId();
    }
    
    @Override
    public List<K_reportador> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        reportadores = reportadorService.getResultList(first, pageSize);
        return reportadores;
    }
    
}
