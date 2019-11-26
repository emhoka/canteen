/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.psmi.canteen.butcher.stocks.service;

import zw.co.psmi.canteen.basic.BasicService;
import zw.co.psmi.canteen.butcher.stocks.entity.Measurement;

/**
 *
 * @author euny
 */
public interface MeasurementService extends BasicService<Measurement> {
	
	public	Measurement findByMeasurementName(String measurementName);
String delete(long id);
}
