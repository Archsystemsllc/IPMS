package com.archsystemsinc.ipms.sec.persistence.service;

import java.util.List;

import com.archsystemsinc.ipms.persistence.service.IService;
import com.archsystemsinc.ipms.sec.model.MeetingAgendaItem;
import com.archsystemsinc.ipms.sec.model.ParkingLot;
import com.archsystemsinc.ipms.sec.model.Principal;

public interface IParkingLotService extends IService<ParkingLot> {

	ParkingLot findByName(final String name);

	void add(ParkingLot parkinglot);
	
	ParkingLot findOne(final ParkingLot topic);
	
}