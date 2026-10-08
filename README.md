# First Enterprise Application

A Java enterprise learning project developed with Apache NetBeans, with separate EJB and web modules. The project explores enterprise application structure, database persistence, and deployment using Eclipse GlassFish and Apache Derby.

## Technology Stack

- Java
- Enterprise JavaBeans (EJB)
- Java Persistence API (JPA)
- Eclipse GlassFish
- Apache Derby / Java DB
- Apache NetBeans
- Apache Ant
- Git and GitHub

## Project Structure

```text
firstEnterpriseApplication/
|-- firstEnterpriseApplication-ejb/  # EJB module and persistence configuration
|-- firstEnterpriseApplication-war/  # Web module
|-- nbproject/                       # NetBeans project configuration
|-- src/                             # Enterprise application resources
|-- build.xml                        # Ant build configuration
|-- README.md
```

The EJB module contains backend Java sources and a persistence descriptor at:

```text
firstEnterpriseApplication-ejb/src/conf/persistence.xml
```

The web module includes a starting page at:

```text
firstEnterpriseApplication-war/web/index.html
```

## Prerequisites

- A JDK compatible with your GlassFish installation and project source level
- Apache NetBeans with enterprise Java support
- Eclipse GlassFish registered in NetBeans
- Apache Derby with its network server and client driver available
- Git

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/codeWithLFN/firstEnterpriseApplication.git
cd firstEnterpriseApplication
```

### 2. Open the project

1. Launch NetBeans.
2. Select File > Open Project.
3. Select the root `firstEnterpriseApplication` directory.
4. Ensure the EJB and web modules are available.
5. Select your locally configured GlassFish server in the project settings.

Machine-specific server paths and libraries may need to be configured on your computer.

### 3. Configure the Derby database

The intended local database name is:

```text
FirstEnterpriseApplicationDb
```

In NetBeans, open Services > Databases > Java DB. Start the network server and create the database if it does not already exist. Choose a username and password, and keep them for the GlassFish configuration.

Use a network connection rather than an embedded connection:

```text
jdbc:derby://localhost:1527/FirstEnterpriseApplicationDb
```

Test the connection in NetBeans before configuring GlassFish.

### 4. Configure the GlassFish connection pool

Open the GlassFish Admin Console, then navigate to:

```text
Resources > JDBC > JDBC Connection Pools
```

Create or configure the pool with these settings:

| Setting | Value |
| --- | --- |
| Pool name | `FirstEnterpriseApplicationDbasePool` |
| Resource type | `javax.sql.DataSource` |
| Datasource class | `org.apache.derby.jdbc.ClientDataSource` |

Set the following additional properties:

| Property | Value |
| --- | --- |
| `databaseName` | `FirstEnterpriseApplicationDb` |
| `serverName` | `localhost` |
| `portNumber` | `1527` |
| `user` | Your database username |
| `password` | Your database password |

Save the pool and click Ping to verify connectivity. Adjust the host and port if your Derby server uses different values.

### 5. Configure the JDBC resource

1. Open Resources > JDBC > JDBC Resources.
2. Create or select the application's JDBC resource.
3. Link it to `FirstEnterpriseApplicationDbasePool`.
4. Ensure its JNDI name exactly matches the `<jta-data-source>` value in `persistence.xml`.

The application uses the JDBC resource's JNDI name, not the pool name. Check the persistence descriptor for the project's configured resource name before deployment.

### 6. Build and deploy

1. Start Derby and GlassFish.
2. In NetBeans, right-click the enterprise application.
3. Select Clean and Build.
4. Select Run or Deploy.
5. Open the application URL provided by NetBeans or the GlassFish deployment output.

## Troubleshooting

### Database not found

Confirm that `FirstEnterpriseApplicationDb` exists in the database directory used by the running Derby network server. Verify that the database name in the connection pool matches the actual database.

### Embedded connection selected

For shared access from NetBeans and GlassFish, select the Java DB network driver and use the network JDBC URL shown above.

### Connection pool ping fails

Check the Derby server, database name, server host, port, credentials, and availability of the Derby client driver to GlassFish. Inspect the GlassFish server log for the underlying error.

### Deployment fails

Inspect the newest error in the GlassFish server log. Verify the persistence configuration, JDBC resource mapping, server libraries, and compatibility between the project's enterprise Java APIs and your GlassFish version.

## Security

Do not commit database passwords, access tokens, or other credentials. Configure local credentials in your development environment and GlassFish resources.

## Author

Lufuno Nemudzivhadi  
GitHub: https://github.com/codeWithLFN

## Project Status

Learning project under development. This README documents the module structure and intended local setup; it does not claim production readiness or a verified end-to-end deployment.
