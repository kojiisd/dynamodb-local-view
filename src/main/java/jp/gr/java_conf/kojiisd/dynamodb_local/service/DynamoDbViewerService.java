package jp.gr.java_conf.kojiisd.dynamodb_local.service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.ListTablesRequest;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import jp.gr.java_conf.kojiisd.dynamodb_local.dto.DynamoDbTableDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kojiisd
 */
@Service
public class DynamoDbViewerService {


    @Autowired
    private AmazonDynamoDB dynamoDB;

    public List<String> getAllTables() {
        ListTablesResult listResult = dynamoDB.listTables();
        List<String> tableNameList = new ArrayList<String>();

        for (String tableName : listResult.getTableNames()) {
            tableNameList.add(tableName);
        }

        return tableNameList;

    }
}
