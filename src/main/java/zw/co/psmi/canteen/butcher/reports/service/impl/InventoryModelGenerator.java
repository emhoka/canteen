/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.canteen.butcher.reports.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zw.co.psmi.canteen.butcher.reports.dao.ReportModelGenerator;
import zw.co.psmi.canteen.butcher.reports.entity.CanteenException;
import zw.co.psmi.canteen.butcher.reports.entity.OutputType;
import zw.co.psmi.canteen.butcher.reports.entity.PdfOutputModel;
import zw.co.psmi.canteen.butcher.reports.service.BasePdfOutputService;
import zw.co.psmi.canteen.butcher.stocks.entity.Inventory;
import zw.co.psmi.canteen.butcher.stocks.service.InventoryService;

/**
 *
 * @author com4t
 */
@Service
@Slf4j
public class InventoryModelGenerator implements ReportModelGenerator {

    @Autowired
    private BasePdfOutputService basePdfOutputService;
    @Autowired
    private InventoryService inventoryService;

    @Override
    @PostConstruct
    public void init() {
        basePdfOutputService.addGenerator(OutputType.INVENTORY, this);
    }

    @Override
    public PdfOutputModel findReportModelByLongIds(List<Long> ids) {
        log.info("inventory ids {}", ids);
        PdfOutputModel reportModel = new PdfOutputModel();
        reportModel.setReportName("reports/inventoryreportprintout");
        Map<String, Object> parameterMap = new HashMap<>();
        List<Inventory> inventorys = new ArrayList<>();
        ids.forEach((inventoryId) -> {
            inventorys.add(inventoryService.getByID(inventoryId));
        });
        parameterMap.put("inventoryss", inventorys);
        reportModel.setHtml(true);
        reportModel.setContextData(true);
        reportModel.setParameter(parameterMap);
        log.info("generator:{}", reportModel);
        log.info("parameters:{}", parameterMap);
        return reportModel;
    }

    @Override
    public PdfOutputModel findReportModelById(Long id) {
        throw new CanteenException("Method Not Supported");
    }

}
