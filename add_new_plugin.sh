echo "mvn package"
mvn package

echo "copy to standalone"
cp target/procedure-template*.jar  /home/guozixu/github/neo4j-try/packaging/standalone/target/neo4j-community-5.20.0-SNAPSHOT/plugins

echo "copy to home standalone"
cp target/procedure-template*.jar  /home/guozixu/github/neo4j-try/packaging/standalone/target/home/plugins
