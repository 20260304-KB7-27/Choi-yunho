package org.scoula.sample.service;

import org.springframework.stereotype.Service;

@Service
public class SampleServiceImpl implements SampleService {

    @Override
    public Integer doAdd(String str1, String str2) throws Exception {

        log.info("Target 메소드 동작합니다.........")
        return Integer.parseInt(str1) + Integer.parseInt(str2);
    }
}
