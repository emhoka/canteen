
package zw.co.psmi.canteen.butcher.reports.entity;

import java.util.List;
import java.util.Map;



@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class PdfOutputModel {
    private static final long serialVersionUID = 47L;
    private String reportName;
    private Map<String, Object> parameter;
    private List<Map> fileldslist;
    private byte[] template;
    private boolean html=false;
    private boolean contextData=true; 
}
