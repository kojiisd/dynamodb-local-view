package jp.gr.java_conf.kojiisd.dynamodb_local.controller;

import jp.gr.java_conf.kojiisd.dynamodb_local.dto.DynamoDbTableDto;
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

    @RequestMapping(value = "list-tables")
    public List<DynamoDbTableDto> listTables() {
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
