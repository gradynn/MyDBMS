package MyDBMS.dbms;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Database {
  private Path path;

  Database(Path absPath) {
    path = absPath;
  }

  public void createTable(String name) {
    Path tablePath = path.resolve(name + ".tabl");
    try {
      Files.createFile(tablePath);
    } catch (FileAlreadyExistsException e) {
      System.err.println("Table already exists in database...");
    } catch (Exception e) {
      System.err.println("Failed to create table " + name);
    }
  }
}
