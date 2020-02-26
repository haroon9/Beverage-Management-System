# Configuration

# PostgreSQL Database

1. Setup *Postgres Database driver*:
    1. Get the correct JDBC driver JAR for your PostgreSQL version from https://jdbc.postgresql.org/.  
    2. Copy `resources/postgresql-*.*.****.jar` inside `[GlassFish directory]/glassfish/domains/[domain dir]/lib/`
2. Setup of Postgres Database:
    1. Run `[PostGres directory]/pgAdmin4/bin/pgAdmin4.exe` (default password: `admin`).  
    2. Add new login role (you have to use a password as GlassFish JDBC Resources will require one) [Name: `tester`, Password: `tester`, Privileges: `can_login: true`]
3. Add new database with [Name: `BeverageStore`, Owner: `tester`]
4. In the file `persistence.xml` all file tables and columns from entities are created automatically using `<property name="eclipselink.ddl-generation" value="create-tables"/>`, thus no additional sql script is necessary.

# Build project
1. Check the settings in `gradle.properties`, especially the path to the glassfish folder
2. In the projects root folder:
    1. Run target `gradlew startGlassfish`
    2. Run target `gradlew initServer`
    3. Run target `gradlew build`
    4. Run target `gradlew deploy`
3. For subsequent builds, the Server does not have to be initialized again!


Please see manual glassfish configuration if there are problems with one of the before mentioned gradle tasks (`startGlassfish`, `initServer`, `deploy`).

# Runtime

The front end with links to all pages is at: http://localhost:8080/frontend/, from this webpage all different tasks can be reached.


## Notes

* In the *Beverage Management Window*, an error icon is shown if the quantity is zero. Because the beverage still exists in the database, new quantities can be added by increasing it in the edit page, no "new" beverage has to get created. 
* ``NamedQueries`` are used used in `orm.xml`
* Validation tasks happen both in the frontend (using `required` as well as `max` and `min` for numbers) and in the backend, where an Error is thrown and gets Logged in the corresponding Servlet.