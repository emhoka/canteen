
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.canteen.butcher.reports.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//import com.lowagie.text.DocumentException;
import lombok.extern.slf4j.Slf4j;
import com.itextpdf.text.DocumentException;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
//import org.thymeleaf.context.IWebContext;
import org.thymeleaf.spring4.messageresolver.SpringMessageResolver;
import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.resource.FSEntityResolver;
import zw.co.psmi.canteen.butcher.reports.dao.PdfOutputDao;
import zw.co.psmi.canteen.butcher.reports.dao.ReportModelGenerator;
import zw.co.psmi.canteen.butcher.reports.entity.OutputType;
import zw.co.psmi.canteen.butcher.reports.entity.PdfOutput;
import zw.co.psmi.canteen.butcher.reports.entity.PdfOutputModel;
import zw.co.psmi.canteen.butcher.reports.entity.StaticTemplateExecutor;

/**
 *
 * @author com4t
 */
@Service
@Slf4j
public class BasePdfOutputService {

    protected PdfOutputDao getPdfOutputDao() {
        return reportDao;
    }

//    @Autowired
//    private SpringMessageResolver messageResolver;
    
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Autowired
    private TemplateEngine templateEngine;

    
    @Autowired
    private PdfOutputDao reportDao;

    private Map<OutputType, ReportModelGenerator> generators;

    private final ReportModelGenerator getGenerator(OutputType outputType) {
        return generators.get(outputType);
    }

     public void addGenerator(OutputType outputType, ReportModelGenerator generator) {
        if (generators == null) {
            generators = new HashMap<>();
        }
        log.info("adding outputModelGenerator for:{}, bean:{}", outputType, generator);
        generators.put(outputType, generator);
    }

    //@Override
    public byte[] outputReport(Long id, OutputType outputType) {
        log.info(".............................outputReport");
        log.info(".............................getGenerator(outputType) {}", getGenerator(outputType));
        return outputReport(getGenerator(outputType).findReportModelById(id));
    }

    public byte[] outputReportFromList(List<Long> id, OutputType outputType) {
        log.info(".............................outputReport");
        log.info(".............................getGenerator(outputType) {}", getGenerator(outputType));
        return outputReport(getGenerator(outputType).findReportModelByLongIds(id));
    }

    //@Override
    public byte[] outputReport(String id, OutputType outputType) {
        log.info(".............................outputReport");
        log.info(".............................getGenerator(outputType) {}", getGenerator(outputType));
        return outputReport(getGenerator(outputType).findReportModelByStringId(id));
    }

    //@Override
    public byte[] outputReport(List<String> ids, OutputType outputType) {
        log.info(".............................outputReport");
        log.info(".............................getGenerator(outputType) {}", getGenerator(outputType));
        return outputReport(getGenerator(outputType).findReportModelByStringIds(ids));
    }

  

    public String getJsonData(long id, OutputType outputType) {
        log.info(".............outputjson {}", outputType);
        PdfOutputModel data = getGenerator(outputType).findReportModelById(id);
        if (data == null || data.getFileldslist() == null || data.getFileldslist().isEmpty()) {
            return null;
        }

        //  log.info(".............data {}", data);
        return getJsonData(data);
    }

    public String getJsonData(String id, OutputType outputType) {
        log.info(".............outputjson {}", outputType);
        PdfOutputModel data = getGenerator(outputType).findReportModelByStringId(id);
        if (data == null || data.getFileldslist() == null || data.getFileldslist().isEmpty()) {
            return null;
        }

        //  log.info(".............data {}", data);
        return getJsonData(data);
    }

    private String getJsonData(PdfOutputModel element) {
        return gson.toJson(element);
    }

    protected final byte[] outputReport(PdfOutputModel reportModel) {
        if (reportModel == null || reportModel.getReportName() == null || reportModel.getReportName().isEmpty()) {
            return "".getBytes();
        }
        if (reportModel.isHtml()) {
            //log.info("render report:{}",reportModel);
            return renderFromHtml(reportModel);
        }
        
        return new byte[1];
    }

    private byte[] renderFromHtml(PdfOutputModel reportModel) {
        Context context = new Context();
        reportModel.getParameter().keySet().forEach(a -> {
            context.setVariable(a, reportModel.getParameter().get(a));
        });
        String html = "";
        if (reportModel.isContextData()) {
            html = templateEngine.process(reportModel.getReportName(), context);
        } else {
            html = processHtmlFromDb(reportModel.getReportName(), context);
        }
       // log.info(".............html:{}",html);
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ITextRenderer renderer = new ITextRenderer();
            //  renderer.setDocumentFromString(getXHTMLFromHTML(html));
            renderer.setDocument(getDocFromHTML(html), null);
            renderer.layout();
            renderer.createPDF(outputStream);
            renderer.finishPDF();
            return outputStream.toByteArray();
        } catch (IOException | DocumentException ex) {
            log.error("Error:", ex);
        }
        return "".getBytes();
    }

    public static String getXHTMLFromHTML(String inputHtml) {

        try (ByteArrayOutputStream fos = new ByteArrayOutputStream();
                ByteArrayInputStream is = new ByteArrayInputStream(inputHtml.getBytes());) {
            Tidy tidy = new Tidy();
            tidy.setXHTML(true);
            tidy.parse(is, fos);
            return fos.toString();
        } catch (IOException ex) {
            log.error("Error:", ex);
        }
        return null;
    }

    private String processHtmlFromDb(String reportName, Context context) {
        PdfOutput report = getPdfOutputDao().findByNameAndActiveStatusTrue(reportName);
        String template = new String(report.getJasperData());
        StaticTemplateExecutor executor = new StaticTemplateExecutor(context, new SpringMessageResolver(), "HTML5");
        return executor.processTemplateCode(template);
    }

    private static Document getDocFromHTML(String inputHtml) {
        try {
            inputHtml = inputHtml.replaceAll("&nbsp;", " ");
            inputHtml = inputHtml.replaceAll("&", "and");
            inputHtml = inputHtml.replaceAll("<br>", "<br></br>");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            builder.setEntityResolver(FSEntityResolver.instance());
            ByteArrayInputStream encXML = new ByteArrayInputStream(inputHtml.getBytes("UTF8"));
            Document doc = builder.parse(encXML);
            return doc;
        } catch (Exception e) {
            log.error("DOM Error:",e);
        }
        return null;
    }

    public String getHtmlData(long id, OutputType outputType) {
        PdfOutputModel reportModel = getGenerator(outputType).findReportModelById(id);
        Context context = new Context();
        reportModel.getParameter().keySet().forEach(a -> {
            context.setVariable(a, reportModel.getParameter().get(a));
        });
        String html = "";
        if (reportModel.isContextData()) {
            html = templateEngine.process(reportModel.getReportName(), context);
        } else {
            html = processHtmlFromDb(reportModel.getReportName(), context);
        }
        return html;
    }

    public String getJsonData(java.util.List<String> samples, OutputType outputType) {
        log.info(".............outputjson {}", outputType);
        PdfOutputModel data = getGenerator(outputType).findReportModelByStringIds(samples);
        if (data == null || data.getFileldslist() == null || data.getFileldslist().isEmpty()) {
            return null;
        }

        //  log.info(".............data {}", data);
        return getJsonData(data);
    }
}
