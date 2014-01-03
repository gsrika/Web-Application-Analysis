import subprocess 
import os


os.chdir('/home/srikanth/gdrive/WorkSpace/bookstore')
subprocess.Popen(' rm /tmp/cobertura/cobertura.ser; rm cobertura.ser; ant all;wget http://localhost:8080/bookstore/Default.jsp; wget http://localhost:8080/bookstore/Registration.jsp;wget http://localhost:8080/bookstore/Books.jsp;wget  ;wget http://localhost:8080/bookstore/Save.jsp ; /home/srikanth/install/cobertura/cobertura-merge.sh /tmp/cobertura/cobertura.ser /tmp/cobertura/sb-initial-bookstore.ser ; /home/srikanth/install/cobertura/cobertura-report.sh --destination /home/srikanth/gdrive/WorkSpace/bookstore/report1 /home/srikanth/gdrive/WorkSpace/bookstore/src; rm /tmp/cobertura/cobertura.ser; rm cobertura.ser; ant all;wget http://localhost:8080/bookstore/Login.jsp;wget  ;wget http://localhost:8080/bookstore/Save.jsp ; /home/srikanth/install/cobertura/cobertura-merge.sh /tmp/cobertura/cobertura.ser /tmp/cobertura/sb-initial-bookstore.ser ; /home/srikanth/install/cobertura/cobertura-report.sh --destination /home/srikanth/gdrive/WorkSpace/bookstore/report2 /home/srikanth/gdrive/WorkSpace/bookstore/src; rm /tmp/cobertura/cobertura.ser; rm cobertura.ser; ant all;/home/srikanth/install/cobertura/cobertura-merge.sh /tmp/cobertura/cobertura.ser /tmp/cobertura/sb-initial-bookstore.ser ; /home/srikanth/install/cobertura/cobertura-report.sh --destination /home/srikanth/gdrive/WorkSpace/bookstore/report3 /home/srikanth/gdrive/WorkSpace/bookstore/src',shell=True)


 

