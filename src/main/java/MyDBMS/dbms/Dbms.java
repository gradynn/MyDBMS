package MyDBMS.dbms;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class Dbms {
  private static Dbms instance = null;
  private static Database db = null;

  private Dbms() {
    // Client initialization code
  }

  public static Dbms getInstance() {
    if (instance == null) {
      instance = new Dbms();
    }
    return instance;
  }

  /**
   * Created a new Database Instance Folder at a specific location.
   * @param absPath Destination folder for new database
   * @param name Name of database
   * @param connect Specifies whether database should automatically be connected to on creation
   */
  public void createDatabase(String absPath, String name, boolean connect) {
    try {
      // create directory
      Path path = Paths.get(absPath, name);
      Files.createDirectories(path);

      // create marker file to designate this as a db directory
      Path configFile = path.resolve(".database");
      Files.createFile(configFile);

      System.out.println("Database " + name +  " created at: " + absPath);

      // Conditionally initiate connection to db
      if (connect) {
        loadDatabase(path.toString());
      }
    } catch (IOException e) {
      System.err.println("Failed to create database folder at: " + e.toString());
    }
  }


  public void loadDatabase(String dbPath) {
    Path path = Paths.get(dbPath);

    // verify directory exists
    if (!Files.exists(path) || !Files.isDirectory(path)) {
      System.err.println("No valid database found at path: " + path.toString());
    }

    // verify directory is initialized database
    Path markerFile = path.resolve(".database");
    if (Files.exists(markerFile)) {
      db = new Database(path);
      System.out.println("Database connection established for: " + path.toString());
    } else {
      System.err.println("No valid database at path: " + path.toString());
    }
  }

  public void createTable(String name) {
    db.createTable(name);
    System.out.println("Table " + name + " created in database");
  }
}
