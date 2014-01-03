
cd /home/srikanth/gdrive/WorkSpace/bookstore

#rm /tmp/cobertura/cobertura.ser;
rm /home/srikanth/gdrive/WorkSpace/selenium/cobertura.ser; 
ant all;

cd /home/srikanth/gdrive/WorkSpace/selenium ;

export JAVA_HOME=/usr/lib/jvm/java-7-openjdk-amd64/jre/
export PATH=$JAVA_HOME/bin:$PATH

mvn test -Dtest=thirdTest
#mvn install;
#mvn exec:java -Dexec.mainClass=CrawlJax.defaultartifact.app

wget http://localhost:8080/bookstore/Save.jsp ;

/home/srikanth/install/cobertura/cobertura-merge.sh /tmp/cobertura/cobertura.ser /tmp/cobertura/sb-initial-bookstore.ser ; 
/home/srikanth/install/cobertura/cobertura-report.sh --destination /home/srikanth/gdrive/WorkSpace/bookstore/report3 /home/srikanth/gdrive/WorkSpace/bookstore/src






 
 
