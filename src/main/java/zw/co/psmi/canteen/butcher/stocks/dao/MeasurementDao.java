/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.canteen.butcher.stocks.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.psmi.canteen.butcher.stocks.entity.Measurement;

/**
 *
 * @author euny
 */
public interface MeasurementDao extends JpaRepository<Measurement, Long> {
	public	Measurement findByMeasurementName(String measurementName);

}
