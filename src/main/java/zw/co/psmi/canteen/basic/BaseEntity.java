package zw.co.psmi.canteen.basic;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Data
public class BaseEntity implements Serializable{
	
	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	   @XmlTransient
	   public String getAuditDetails(){
	       return toString();
	   }
	    
           protected boolean activeStatus = true;
            
	    private static final DateTimeFormatter cdate = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
	    @Column(name = "creation_date", updatable = false)
	    private String creationDate;
	      
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    protected long id;
	    

	    @Column(name = "creation_time", updatable = false)
	    private String creationTime;
	    
	    @PrePersist
	    public void prePersist() {
	        this.creationTime = dtf.format(LocalDateTime.now());
	        this.creationDate = cdate.format(LocalDateTime.now());
	        }

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getCreationTime() {
			return creationTime;
		}

		public void setCreationTime(String creationTime) {
			this.creationTime = creationTime;
		}

		public static DateTimeFormatter getDtf() {
			return dtf;
		}

   
}
