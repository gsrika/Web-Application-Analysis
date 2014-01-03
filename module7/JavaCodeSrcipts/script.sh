for i in `seq 1 9`;
        do
                echo $i
                cd /home/srikanth/gdrive/WorkSpace/bookstore

                #rm /var/lib/tomcat6/cobertura.ser;
                rm cobertura.ser; 
                ant all;


                cd /home/srikanth/gdrive/WorkSpace/selenium
		echo 'ICA8'$i'Test'
                mvn test -Dtest='ICA8'$i'Test'

                cd /home/srikanth/gdrive/WorkSpace/bookstore

                wget http://localhost:8080/bookstore/Save.jsp ;

                /home/srikanth/install/cobertura/cobertura-merge.sh /var/lib/tomcat6/cobertura.ser /tmp/cobertura/sb-initial-bookstore.ser ; 
                /home/srikanth/install/cobertura/cobertura-report.sh --format xml --destination /home/srikanth/gdrive/WorkSpace/bookstore/report$i  /home/srikanth/gdrive/WorkSpace/bookstore/src
        done    

:'
cd /home/srikanth/gdrive/WorkSpace/bookstore

#rm /var/lib/tomcat6/cobertura.ser;
rm cobertura.ser; 
ant all;


cd /home/srikanth/gdrive/WorkSpace/selenium

mvn test -Dtest=ICA81Test

cd /home/srikanth/gdrive/WorkSpace/bookstore

wget http://localhost:8080/bookstore/Save.jsp ;

/home/srikanth/install/cobertura/cobertura-merge.sh /var/lib/tomcat6/cobertura.ser /tmp/cobertura/sb-initial-bookstore.ser ; 
/home/srikanth/install/cobertura/cobertura-report.sh --format xml --destination /home/srikanth/gdrive/WorkSpace/bookstore/report1 /home/srikanth/gdrive/WorkSpace/bookstore/src



cd /home/srikanth/gdrive/WorkSpace/bookstore

#rm /var/lib/tomcat6/cobertura.ser;
rm cobertura.ser; 
ant all;


cd /home/srikanth/gdrive/WorkSpace/selenium


mvn test -Dtest=ICA82Test
#mvn test -Dtest=ICA83Test 
cd /home/srikanth/gdrive/WorkSpace/bookstore

wget http://localhost:8080/bookstore/Save.jsp ;





/home/srikanth/install/cobertura/cobertura-merge.sh /var/lib/tomcat6/cobertura.ser /tmp/cobertura/sb-initial-bookstore.ser ; 
/home/srikanth/install/cobertura/cobertura-report.sh --format xml --destination /home/srikanth/gdrive/WorkSpace/bookstore/report2 /home/srikanth/gdrive/WorkSpace/bookstore/src



cd /home/srikanth/gdrive/WorkSpace/bookstore

#rm /var/lib/tomcat6/cobertura.ser;
rm cobertura.ser; 
ant all;


cd /home/srikanth/gdrive/WorkSpace/selenium



mvn test -Dtest=ICA83Test 
cd /home/srikanth/gdrive/WorkSpace/bookstore

wget http://localhost:8080/bookstore/Save.jsp ;

/home/srikanth/install/cobertura/cobertura-merge.sh /var/lib/tomcat6/cobertura.ser /tmp/cobertura/sb-initial-bookstore.ser ; 
/home/srikanth/install/cobertura/cobertura-report.sh --format xml --destination /home/srikanth/gdrive/WorkSpace/bookstore/report3 /home/srikanth/gdrive/WorkSpace/bookstore/src

'






 
 
