package jp.gr.java_conf.kojiisd.dynamodb_local.controller;

import jp.gr.java_conf.kojiisd.dynamodb_local.service.DynamoDbViewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author kojiisd
 */
@RestController
@RequestMapping(value = "/api")
public class DynamoDbViewerRest {

    @Autowired
    private DynamoDbViewerService viewerService;

    @RequestMapping(value = "list-tables")
    public List<String> listTables() {
        return this.viewerService.getAllTables();
    }

    @RequestMapping(value = "inquiry-table/{tableName}")
    public List<Map<String, String>> inquiryTable(@PathVariable("tableName") String tableName) {
        return viewerService.scanTableByTableName(tableName);
    }
}
