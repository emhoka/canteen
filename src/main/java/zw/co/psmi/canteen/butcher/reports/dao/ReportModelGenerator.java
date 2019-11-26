/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.canteen.butcher.reports.dao;

import java.util.List;
import javax.annotation.PostConstruct;
import zw.co.psmi.canteen.butcher.reports.entity.CanteenException;
import zw.co.psmi.canteen.butcher.reports.entity.PdfOutputModel;

/**
 *
 * @author com4t
 */
public interface ReportModelGenerator {    
    public PdfOutputModel findReportModelById(Long id);
    public default PdfOutputModel findReportModelByStringId(String id){
        throw new CanteenException("String Id not defined for Model");
    }
    public default PdfOutputModel findReportModelByStringIds(List<String> ids){
        throw new CanteenException("String Id List not defined for Model");
    }
    public default PdfOutputModel findReportModelByLongIds(List<Long> ids){
        throw new CanteenException("Long Id not defined for Model");
    }
    @PostConstruct
    public void init();
}
