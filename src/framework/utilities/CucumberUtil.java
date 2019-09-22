package framework.utilities;

import io.cucumber.datatable.DataTable;

import java.util.ArrayList;
import java.util.List;

public class CucumberUtil {

    /*private static Dictionary<String, DataCollection>
            _dataCollection = new Hashtable<>();*/

    private static List<DataCollection> _dataCollection = new ArrayList<DataCollection>();

    public static List<DataCollection> ConvertDataTableToDict (DataTable table) {

        _dataCollection.clear();
        List<List<String>> data = table.asLists();
        //List<List<String>> data-driven = table.raw();
        int rowNumber = 0;

        for (List<String> col : data) {
            for (int colIndex = 0; colIndex < col.size(); colIndex++) {
                _dataCollection.add(rowNumber, new
                        DataCollection(data.get(0).get(colIndex), col.get(colIndex), rowNumber));
            }
            rowNumber++;
        }
        return _dataCollection;
    }

    //Get all the rows
    public static String GetCellValueWithRowIndex(String columnName, int rowName){
        final String[] columnValue = {null};

        _dataCollection.forEach(x -> {
            if(x.ColumnName.equals(columnName) && x.RowNumber == rowName){
                columnValue[0] = x.ColumnValue;
            }
        });
        return columnValue[0];
    }

    private static class DataCollection{
        private String ColumnName;
        private String ColumnValue;
        private int RowNumber;

        public DataCollection(String columnName, String columnValue, int rowNumber) {
            ColumnName = columnName;
            ColumnValue = columnValue;
            RowNumber = rowNumber;
        }
    }
}
