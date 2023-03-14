package com.epsoft.msg.support.dao;

import com.epsoft.msg.support.domain.SmsRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SmsRecordDAO extends CrudRepository<SmsRecord,Long> {

    /**
     * 通过日期和手机号查找短信发送记录
     * @param phone
     * @param sendDate
     * @return
     */
    List<SmsRecord> findByPhoneAndSendDate(Long phone, Integer sendDate);

}
