
package zw.co.psmi.canteen.butcher.reports.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.psmi.canteen.butcher.reports.entity.PdfOutput;


@Repository
public interface PdfOutputDao extends JpaRepository<PdfOutput, Long>{
    PdfOutput findByNameAndActiveStatusTrue(String name);
    List<PdfOutput> findByName(String name);

    public PdfOutput findByActiveStatusTrue();
    
}
