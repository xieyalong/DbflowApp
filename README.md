# DbflowApp
One of the issues with existing SQL object relational mapping (ORM) libraries is that they rely on Java reflection to define database models, table schemas, and column relationships

Setup

The section below describes how to setup using DBFlow v3. If you are upgrading from an older version of DBFlow, read this migration guide. One of the major changes is the library used to generate Java code from annotation now relies on JavaPoet. The generated database and table classes now use '_' instead of '$' as the separator, which may require small adjustments to your code when upgrading.

Gradle configuration

Because DBFlow4 is still not officially released, you need also add https://jitpack.io to your allprojects -> repositories dependency list:

allprojects {
    
    
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
    
 
 }
}

Next, within your app/build.gradle, add DBFlow to your dependency list. We create a separate variable to store the version number to make it easier to change later:

def dbflow_version = "4.0.0-beta5"

dependencies {
    
    
    // annotationProcessor now supported in Android Gradle plugin 2.2
    // See https://bitbucket.org/hvisser/android-apt/wiki/Migration
    annotationProcessor "com.github.Raizlabs.DBFlow:dbflow-processor:${dbflow_version}"
    compile "com.github.Raizlabs.DBFlow:dbflow-core:${dbflow_version}"
    compile "com.github.Raizlabs.DBFlow:dbflow:${dbflow_version}"

    // sql-cipher database encryption (optional)
    // compile "com.github.Raizlabs.DBFlow:dbflow-sqlcipher:${dbflow_version}"
  
  
  }


#### Creating the database

Create a MyDatabase.java file and annotate your class with the @Database decorator to declare your database. It should contain both the name to be used for creating the table, as well as the version number. Note: if you decide to change the schema for any tables you create later, you will need to bump the version number. The version number should always be incremented (and never downgraded) to avoid conflicts with older database versions.

```
@Database(name = MyDatabase.NAME, version = MyDatabase.VERSION)
public class MyDatabase {
    public static final String NAME = "MyDataBase";

    public static final int VERSION = 1;
}
)

}
```
#### Defining your Tables
```
The Java model objects need to extend from the BaseModel class as shown below for an Organization model:

import com.raizlabs.android.dbflow.structure.BaseModel;

// **Note:** Your class must extend from BaseModel
@Table(database = MyDatabase.class)
public class Organization extends BaseModel {

  @Column
  @PrimaryKey
  int id;

  @Column
  String name;
}
```
#### Basic CRUD operations

Basic creation, read, update, and delete (CRUD) statements are fairly straightforward to do. DBFlow generates a Table class for each your annotated models (i.e. User_Table, Organization_Table), and each field is defined as a Property object and ensures type-safety when evaluating it against in a SELECT statement or a raw value

```
// Create organization
Organization organization = new Organization();
organization.setId(1);
organization.setName("CodePath");
organization.save();

// Create user
User user = new User();
user.setName("John Doe");
user.setOrganization(organization);
user.save();

```
