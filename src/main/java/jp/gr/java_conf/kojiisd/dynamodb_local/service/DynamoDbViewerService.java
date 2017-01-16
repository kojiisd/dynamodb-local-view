package jp.gr.java_conf.kojiisd.dynamodb_local.service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kojiisd
 */
@Service
public class DynamoDbViewerService {


    @Autowired
    private AmazonDynamoDB dynamoDB;

    public List<String> getAllTables() {
        ListTablesResult listResult = this.dynamoDB.listTables();
        List<String> tableNameList = new ArrayList<String>();

        for (String tableName : listResult.getTableNames()) {
            tableNameList.add(tableName);
        }

        return tableNameList;

    }

    public List<Map<String, String>> scanTableByTableName(String tableName) {
        List<Map<String, String>> resultMapList = new ArrayList<Map<String, String>>();

        ScanResult scanResult = this.dynamoDB.scan(new ScanRequest(tableName));
        Map<String, String> resultMap = new LinkedHashMap<String, String>();
        for (Map<String, AttributeValue> valueMap : scanResult.getItems()) {
            for (Map.Entry<String, AttributeValue> valueMapEntry : valueMap.entrySet()) {
                resultMap.put(valueMapEntry.getKey(), valueMapEntry.getValue().toString());
            }
        }

        return resultMapList;
    }
}
