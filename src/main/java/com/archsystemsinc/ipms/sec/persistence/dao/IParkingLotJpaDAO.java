package com.archsystemsinc.ipms.sec.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.archsystemsinc.ipms.sec.model.MeetingAgendaItem;
import com.archsystemsinc.ipms.sec.model.ParkingLot;


public interface IParkingLotJpaDAO extends
JpaRepository<ParkingLot, Long>,
JpaSpecificationExecutor<ParkingLot> {



}
