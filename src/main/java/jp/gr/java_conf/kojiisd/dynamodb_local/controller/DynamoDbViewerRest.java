package jp.gr.java_conf.kojiisd.dynamodb_local.controller;

import jp.gr.java_conf.kojiisd.dynamodb_local.dto.DynamoDbTableDto;
import jp.gr.java_conf.kojiisd.dynamodb_local.service.DynamoDbViewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * Just testing later will be deleted.
     */
    @RequestMapping(value = "list-tables-test")
    public List<DynamoDbTableDto> testListTables() {
        List<DynamoDbTableDto> tableList = new ArrayList<DynamoDbTableDto>();
        DynamoDbTableDto dto1 = new DynamoDbTableDto();
        dto1.tableName = "sample table1";
        dto1.status = "Running";

        DynamoDbTableDto dto2 = new DynamoDbTableDto();
        dto2.tableName = "sample table2";
        dto2.status = "Stopped";

        tableList.add(dto1);
        tableList.add(dto2);

        return tableList;
    }
}
