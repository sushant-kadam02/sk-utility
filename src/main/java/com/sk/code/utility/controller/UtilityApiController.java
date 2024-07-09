package com.sk.code.utility.controller;

import com.sk.code.utility.service.ExcelDataLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UtilityApiController {

    @Autowired
    ExcelDataLoader excelDataLoader;

    public static final Logger LOG = LoggerFactory.getLogger(UtilityApiController.class);

    @PostMapping(value="/data")
    public String serviceA(@RequestBody String filePath) {
        LOG.info("Inside controller  file path is : "+filePath);
        excelDataLoader.verifyAndLoadData(filePath);

        return "";
    }
}
