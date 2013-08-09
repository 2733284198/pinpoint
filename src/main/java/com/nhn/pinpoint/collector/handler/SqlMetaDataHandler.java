package com.nhn.pinpoint.collector.handler;

import java.net.DatagramPacket;

import org.apache.thrift.TBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.nhn.pinpoint.common.dto2.thrift.SqlMetaData;
import com.nhn.pinpoint.collector.dao.SqlMetaDataDao;

/**
 *
 */
public class SqlMetaDataHandler implements SimpleHandler {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SqlMetaDataDao sqlMetaDataDao;

	@Override
	public void handler(TBase<?, ?> tbase) {
		if (!(tbase instanceof SqlMetaData)) {
			logger.warn("invalid tbase:{}", tbase);
			return;
		}
		
		SqlMetaData sqlMetaData = (SqlMetaData) tbase;
		
		if (logger.isInfoEnabled()) {
			logger.info("Received SqlMetaData:{}", sqlMetaData);
		}
		
		sqlMetaDataDao.insert(sqlMetaData);
	}
}
