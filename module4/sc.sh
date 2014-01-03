
 rm /var/lib/tomcat6/cobertura.ser 

cd /home/srikanth/gdrive/WorkSpace/empldir

rm /tmp/cobertura/cobertura.ser;
rm /home/srikanth/gdrive/WorkSpace/empldir/cobertura.ser; 
ant all;

#cd /home/srikanth/gdrive/WorkSpace/CrawlJax ;

#export JAVA_HOME=/usr/lib/jvm/java-7-openjdk-amd64/jre/
#export PATH=$JAVA_HOME/bin:$PATH

#mvn install;
#mvn exec:java -Dexec.mainClass=CrawlJax.defaultartifact.app
cd /home/srikanth/gdrive/WorkSpace/wamparser/src/main/java/wamparser/wamparser
javac App.java

cd /home/srikanth/gdrive/WorkSpace/wamparser/src/main/java 
java wamparser.wamparser.App ;

cd /home/srikanth/gdrive/WorkSpace/empldir

cd wget
chmod u+x *.sh 
./url.sh

cd ..

wget http://localhost:8080/empldir/Save.jsp ;

/home/srikanth/install/cobertura/cobertura-merge.sh /var/lib/tomcat6/cobertura.ser /tmp/cobertura/sb-initial-empldir.ser ; 
/home/srikanth/install/cobertura/cobertura-report.sh --destination /home/srikanth/gdrive/WorkSpace/empldir/report1 /home/srikanth/gdrive/WorkSpace/empldir/src






 
 
