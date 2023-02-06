package database;

public interface dbInterface  {
    void getName(String TableName);

    String createTable(String fields);

    String selectAllFromtable();

    String insertIntoTable(String rows);

    String updateInfile();
}
