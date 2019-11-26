
package zw.co.psmi.canteen.butcher.reports.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import lombok.Data;
import zw.co.psmi.canteen.basic.BaseEntity;


@Entity
@Data
@Table(name="pdf_output")
public class PdfOutput extends BaseEntity {
    @Column(unique = true,updatable = false,nullable = false)
    private String name;
    @Lob
    private byte[] jasperData;
    
}
