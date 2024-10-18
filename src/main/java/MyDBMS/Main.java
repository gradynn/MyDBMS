package MyDBMS;

import MyDBMS.dbms.Dbms;

public class Main {
  public static void main(String[] args) {
    Dbms client = Dbms.getInstance();

    client.createDatabase("/Users/gradynnagle/bin/MyDBMS", "prod_db", false);

    client.loadDatabase("/Users/gradynnagle/bin/MyDBMS/prod_db");

    client.createTable("users");

  }
}