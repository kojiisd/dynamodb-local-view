package jp.gr.java_conf.kojiisd.dynamodb_local.service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Viewer service for DynamoDB.
 * @author kojiisd
 */
@Service
public class DynamoDbViewerService {

    /**
     * DynamoDB value separator
     */
    private static final String VALUE_SEPARATOR = ",";

    /**
     * DynamoDB client
     */
    @Autowired
    private AmazonDynamoDB dynamoDB;

    /**
     * Get all table names as list.
     *
     * @return table name list
     */
    public List<String> getAllTables() {
        ListTablesResult listResult = this.dynamoDB.listTables();
        List<String> tableNameList = new ArrayList<String>();

        for (String tableName : listResult.getTableNames()) {
            tableNameList.add(tableName);
        }

        return tableNameList;

    }

    /**
     * Scan target table.
     *
     * @param tableName table name
     * @return scan result
     */
    public List<Map<String, String>> scanTableByTableName(String tableName) {
        List<Map<String, String>> resultMapList = new ArrayList<Map<String, String>>();

        ScanResult scanResult = this.dynamoDB.scan(new ScanRequest(tableName));

        // For display easily, format will be List<Map<String, String>>
        // To view screen without layout broken, once put all columns in Map.
        for (Map<String, AttributeValue> valueMap : scanResult.getItems()) {
            Map<String, String> columnMap = createEmptyColumnMap(scanResult);

            for (Map.Entry<String, AttributeValue> valueMapEntry : valueMap.entrySet()) {
                columnMap.put(valueMapEntry.getKey(), extractColumnValue(valueMapEntry.getValue()));
            }

            resultMapList.add(columnMap);
        }

        return resultMapList;
    }

    /**
     * Create Empty column map for display all columns in screen.
     *
     * @param scanResult scan result
     * @return Map value
     */
    private Map<String, String> createEmptyColumnMap(ScanResult scanResult) {

        Map<String, String> columnMap = new LinkedHashMap<String, String>();
        for (Map<String, AttributeValue> valueMap : scanResult.getItems()) {
            for (Map.Entry<String, AttributeValue> valueMapEntry : valueMap.entrySet()) {
                columnMap.put(valueMapEntry.getKey(), StringUtils.EMPTY);
            }
        }

        return columnMap;
    }

    /**
     * Extract column value from DynamoDB with following types.
     * - bOOL, b, n, s -> String type extract
     * - sS, nS, bS, m -> String with "," combine
     *
     * @param value AttributeValue
     * @return result of extract
     */
    private String extractColumnValue(AttributeValue value) {
        String result = StringUtils.EMPTY;

        if (value == null) {
            return result;
        }

        if (value.getBOOL() != null) {
            result = value.getBOOL().toString();
        } else if (value.getB() != null) {
            result = value.getB().toString();
        } else if (value.getN() != null) {
            result = value.getN();
        } else if (value.getS() != null) {
            result = value.getS();
        } else if (value.getM() != null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                result = mapper.writeValueAsString(value.getM());
            } catch (JsonProcessingException ex) {
                // TODO Exception handling
                ex.printStackTrace();
                result = StringUtils.EMPTY;
            }
        } else if (value.getSS() != null) {
            result = String.join(VALUE_SEPARATOR, value.getSS().toArray(new String[0]));
        } else if (value.getNS() != null) {
            result = String.join(VALUE_SEPARATOR, value.getNS().toArray(new String[0]));
        } else if (value.getBS() != null) {
            result = String.join(VALUE_SEPARATOR, value.getBS().toArray(new String[0]));
        }

        return result;
    }
}
