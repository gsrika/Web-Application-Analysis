
cd /home/srikanth/gdrive/WorkSpace/bookstore

rm /var/lib/tomcat6/cobertura.ser;
rm /home/srikanth/gdrive/WorkSpace/cukes/cobertura.ser; 
ant all;

cd /home/srikanth/gdrive/WorkSpace/cukes;
cucumber;
 
wget http://localhost:8080/bookstore/Save.jsp ;

/home/srikanth/install/cobertura/cobertura-merge.sh /var/lib/tomcat6/cobertura.ser /tmp/cobertura/sb-initial-bookstore.ser ; 
/home/srikanth/install/cobertura/cobertura-report.sh --destination /home/srikanth/gdrive/WorkSpace/cukes/report1 /home/srikanth/gdrive/WorkSpace/bookstore/src






 
 
