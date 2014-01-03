cd  /home/srikanth/gdrive/WorkSpace/bookstore

rm cobertura.ser
ant all;

cd /home/srikanth/gdrive/WorkSpace/selenium
res1=$(date +%s.%N)
#DIFF=$(echo "$END - $START" | bc)

for i in `seq 1 9`
	do
		   echo 'ICA8'$i'Test'
		   mvn test -Dtest='ICA8'$i'Test'
	done 

res2=$(date +%s.%N)
time1=$(echo "$res2-$res1" | bc)
dt=$(echo "$res2 - $res1" | bc)
dd=$(echo "$dt/86400" | bc)
dt2=$(echo "$dt-86400*$dd" | bc)
dh=$(echo "$dt2/3600" | bc)
dt3=$(echo "$dt2-3600*$dh" | bc)
dm=$(echo "$dt3/60" | bc)
ds=$(echo "$dt3-60*$dm" | bc)

echo "Total runtime for intial suite : %d:%02d:%02d:%02.4f\n" $dd $dh $dm $ds
~                                                          
DIFF=$(echo "$END - $START" | bc)
echo $runtime
#echo "total time for running inital test suite is "$starttime-$endtime

cd /home/srikanth/gdrive/WorkSpace/bookstore
wget http://localhost:8080/bookstore/Save.jsp 

/home/srikanth/install/cobertura/cobertura-merge.sh /var/lib/tomcat6/cobertura.ser /tmp/cobertura/sb-initial-bookstore.ser ;

/home/srikanth/install/cobertura/cobertura-report.sh --format html --destination /home/srikanth/gdrive/WorkSpace/bookstore/report  /home/srikanth/gdrive/WorkSpace/bookstore/src

cd /home/srikanth/gdrive/WorkSpace/ICA8

mvn install 
mvn exec:java -Dexec.mainClass=com.ica8.lineTestMapper
res1=$(date +%s.%N)

cd /home/srikanth/gdrive/WorkSpace/selenium

/home/srikanth/gdrive/WorkSpace/ICA8/script.sh

res2=$(date +%s.%N)
time2=$(echo "$res2 - $res1" | bc )
dt=$(echo "$res2 - $res1" | bc)
dd=$(echo "$dt/86400" | bc)
dt2=$(echo "$dt-86400*$dd" | bc)
dh=$(echo "$dt2/3600" | bc)
dt3=$(echo "$dt2-3600*$dh" | bc)
dm=$(echo "$dt3/60" | bc)
ds=$(echo "$dt3-60*$dm" | bc)

echo "Total runtime for selected suite  : %d:%02d:%02d:%02.4f\n" $dd $dh $dm $ds
                                   

dt=$(echo "$time1 - $time2" | bc )
dd=$(echo "$dt/86400" | bc)
dt2=$(echo "$dt-86400*$dd" | bc)
dh=$(echo "$dt2/3600" | bc)
dt3=$(echo "$dt2-3600*$dh" | bc)
dm=$(echo "$dt3/60" | bc)
ds=$(echo "$dt3-60*$dm" | bc)

echo "Total savings through regression   : %d:%02d:%02d:%02.4f\n" $dd $dh $dm $ds                       
#echo "total savings" $runtime2-$runtime


 
 
