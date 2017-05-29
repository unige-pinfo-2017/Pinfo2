batch

# Add mariadb module
module add \
  --name=com.mariadb \
  --resources=${JDB_CONNECTOR_PATH} \
  --dependencies=javax.api,javax.transaction.api

/subsystem=datasources/jdbc-driver=mariadb:add( \
  driver-name=mariadb, \
  driver-module-name=com.mariadb, \
  driver-xa-datasource-class-name=com.mariadb.jdbc.jdbc2.optional.MysqlXADataSource \
)

# Add it as a driver
data-source add \
  --name=${DB_TABLE_NAME} \
  --driver-name=mariadb \
  --jndi-name=java:/${DB_TABLE_NAME} \
  --connection-url=${DB_URL}/${DB_TABLE_NAME}?useUnicode=true&amp;characterEncoding=UTF-8 \
  --user-name=${DB_USER} \
  --password=${DB_PASS} \
  --use-ccm=false \
  --max-pool-size=25 \
  --blocking-timeout-wait-millis=5000

/subsystem=security/security-domain=pinfo2-realm:add(cache-type=default)
/subsystem=security/security-domain=pinfo2-realm/authentication=classic:add( \
  login-modules=[{\
    "code"=>"Database", \
    "flag"=>"required", \
    "module-options"=>{ \
      "dsJndiName" => "java:/${DB_TABLE_NAME}", \
      "principalsQuery" => \
        "select password from ${DB_TABLE_NAME}.RegularUser where username=?", \
      "rolesQuery" => \
        "select role, 'Roles' from ${DB_TABLE_NAME}.RegularUser where username=?", \
      "hashAlgorithm" => "SHA-256", \
      "hashEncoding" => "hex" \
    } \
  }] \
)

run-batch

