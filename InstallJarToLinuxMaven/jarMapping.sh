export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.151-5.b12.el7_4.x86_64
export M2_HOME=/var/lib/jenkins/tools/hudson.tasks.Maven_MavenInstallation/Maven_3.5.3
export M2=$M2_HOME/bin
export PATH=$M2:$PATH
mvn install:install-file -Dfile=certj.jar -DgroupId=com.staples.wst -DartifactId=certj -Dversion=1.0  -Dpackaging=jar | rm -r certj.jar
mvn install:install-file -Dfile=cryptoj.jar -DgroupId=com.staples.wst -DartifactId=cryptoj -Dversion=1.0  -Dpackaging=jar | rm -r cryptoj.jar
mvn install:install-file -Dfile=fpeclient.jar -DgroupId=com.staples.wst -DartifactId=fpeclient -Dversion=1.0  -Dpackaging=jar | rm -r fpeclient.jar
mvn install:install-file -Dfile=kmcwrapper.jar -DgroupId=com.staples.wst -DartifactId=kmcwrapper -Dversion=1.0  -Dpackaging=jar | rm -r kmcwrapper.jar
mvn install:install-file -Dfile=kmsclient.jar -DgroupId=com.staples.wst -DartifactId=kmsclient -Dversion=1.0  -Dpackaging=jar | rm -r kmsclient.jar
mvn install:install-file -Dfile=sslj.jar -DgroupId=com.staples.wst -DartifactId=sslj -Dversion=1.0  -Dpackaging=jar | rm -r sslj.jar
mvn install:install-file -Dfile=MQService-0.0.1.jar -DgroupId=com.staples.mqService -DartifactId=MQService -Dversion=0.0.1  -Dpackaging=jar | rm -r MQService-0.0.1.jar
mvn install:install-file -Dfile=CybersourceClient.jar -DgroupId=com.staples.cybersource -DartifactId=cybersourceClient -Dversion=1.0  -Dpackaging=jar | rm -r CybersourceClient.jar
mvn install:install-file -Dfile=CyberSourceComm.jar -DgroupId=com.staples.cybersource -DartifactId=cyberSourceComm -Dversion=1.0  -Dpackaging=jar | rm -r CyberSourceComm.jar
mvn install:install-file -Dfile=rt.jar -DgroupId=com.sun -DartifactId=rt -Dversion=1.0  -Dpackaging=jar | rm -r rt.jar
mvn install:install-file -Dfile=connector.jar -DgroupId=javax.resource -DartifactId=connector -Dversion=1.0  -Dpackaging=jar | rm -r connector.jar
mvn install:install-file -Dfile=com.ibm.mq.jar -DgroupId=com.ibm -DartifactId=mq -Dversion=1.0  -Dpackaging=jar | rm -r com.ibm.mq.jar
mvn install:install-file -Dfile=com.ibm.mq.commonservices.jar -DgroupId=com.ibm.mq -DartifactId=commonservices -Dversion=1.0 -Dpackaging=jar | rm -r com.ibm.mq.commonservices.jar
mvn install:install-file -Dfile=com.ibm.mq.headers.jar -DgroupId=com.ibm.mq -DartifactId=headers -Dversion=1.0  -Dpackaging=jar | rm -r com.ibm.mq.headers.jar
mvn install:install-file -Dfile=com.ibm.mq.jmqi.jar -DgroupId=com.ibm.mq -DartifactId=jmqi -Dversion=1.0 -Dpackaging=jar | rm -r com.ibm.mq.jmqi.jar
mvn install:install-file -Dfile=com.ibm.mq.pcf.jar -DgroupId=com.ibm.mq -DartifactId=pcf -Dversion=1.0  -Dpackaging=jar | rm -r com.ibm.mq.pcf.jar
mvn install:install-file -Dfile=com.ibm.mqjms.jar -DgroupId=com.ibm -DartifactId=mqjms -Dversion=1.0 -Dpackaging=jar | rm -r com.ibm.mqjms.jar
mvn install:install-file -Dfile=jt400Native.jar -DgroupId=com.ibm -DartifactId=jt400Native -Dversion=1.0 -Dpackaging=jar | rm -r jt400Native.jar
mvn install:install-file -Dfile=jt400.jar -DgroupId=com.ibm -DartifactId=jt400 -Dversion=1.0 -Dpackaging=jar | rm -r jt400.jar
mvn install:install-file -Dfile=org.apache.httpcomponents.httpcore-4.2.1.jar -DgroupId=org.apache.httpcomponents -DartifactId=httpcore -Dversion=4.2.1 -Dpackaging=jar | rm -r org.apache.httpcomponents.httpcore-4.2.1.jar
mvn install:install-file -Dfile=wss4j-2.1.jar -DgroupId=com.staples -DartifactId=wss4j -Dversion=2.1 -Dpackaging=jar | rm -r wss4j-2.1.jar
mvn install:install-file -Dfile=common-2.5.0.jar -DgroupId=com.staples -DartifactId=common -Dversion=2.5.0 -Dpackaging=jar | rm -r common-2.5.0.jar
mvn install:install-file -Dfile=accountservice.jar -DgroupId=com.staples -DartifactId=accountservice -Dversion=1.0 -Dpackaging=jar | rm -r accountservice.jar
mvn install:install-file -Dfile=cryptojce.jar -DgroupId=com.staples -DartifactId=cryptojce -Dversion=1.0 -Dpackaging=jar | rm -r cryptojce.jar
mvn install:install-file -Dfile=cryptojcommon.jar -DgroupId=com.staples -DartifactId=cryptojcommon -Dversion=1.0 -Dpackaging=jar | rm -r cryptojcommon.jar
mvn install:install-file -Dfile=DPSAdapter.jar -DgroupId=com.staples -DartifactId=DPSAdapter -Dversion=1.0 -Dpackaging=jar | rm -r DPSAdapter.jar
mvn install:install-file -Dfile=DPSClient.jar -DgroupId=com.staples -DartifactId=DPSClient -Dversion=1.0 -Dpackaging=jar | rm -r DPSClient.jar
mvn install:install-file -Dfile=EDUconcurrent.jar -DgroupId=com.staples -DartifactId=EDUconcurrent -Dversion=1.0 -Dpackaging=jar | rm -r EDUconcurrent.jar
mvn install:install-file -Dfile=FDRapidConnect.jar -DgroupId=com.staples -DartifactId=FDRapidConnect -Dversion=1.0 -Dpackaging=jar | rm -r FDRapidConnect.jar
mvn install:install-file -Dfile=gpas-commons-final.jar -DgroupId=com.staples -DartifactId=gpas-commons-final -Dversion=1.0 -Dpackaging=jar | rm -r gpas-commons-final.jar
mvn install:install-file -Dfile=GPASSClient.jar -DgroupId=com.staples -DartifactId=GPASSClient -Dversion=1.0 -Dpackaging=jar | rm -r GPASSClient.jar
mvn install:install-file -Dfile=jcm.jar -DgroupId=com.staples -DartifactId=jcm -Dversion=1.0 -Dpackaging=jar | rm -r jcm.jar
mvn install:install-file -Dfile=POWClient.jar -DgroupId=com.staples -DartifactId=POWClient -Dversion=1.0 -Dpackaging=jar | rm -r POWClient.jar
mvn install:install-file -Dfile=RapidConnect.jar -DgroupId=com.staples -DartifactId=RapidConnect -Dversion=1.0 -Dpackaging=jar | rm -r RapidConnect.jar
mvn install:install-file -Dfile=SecurityFramework.jar -DgroupId=com.staples -DartifactId=SecurityFramework -Dversion=1.0 -Dpackaging=jar | rm -r SecurityFramework.jar
mvn install:install-file -Dfile=tools.jar -DgroupId=com.staples -DartifactId=tools -Dversion=1.0 -Dpackaging=jar | rm -r tools.jar
mvn install:install-file -Dfile=sun.misc.BASE64Decoder.jar -DgroupId=sun.misc -DartifactId=BASE64Decoder -Dversion=1.0 -Dpackaging=jar | rm -r sun.misc.BASE64Decoder.jar