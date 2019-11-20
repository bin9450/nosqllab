package com.nosqllab.service;

import com.nosqllab.result.Result;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @Author: Pan
 * @Date: 2019/11/17 21:14
 * @Description:
 **/

public interface LabService {
    Result<Object> findAllData(int page, int pageSize, int moudleCode);
    <T> Result<Object> insertAllData(T data);

}
