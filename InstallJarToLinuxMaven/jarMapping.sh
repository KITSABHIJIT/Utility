export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_181.jdk/Contents/Home
export M2_HOME=//Users/royab001/apache-maven-3.5.4
export M2=$M2_HOME/bin
export PATH=$M2:$PATH
mvn install:install-file -Dfile=certj.jar -DgroupId=com.staples.wst -DartifactId=certj -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=cryptoj.jar -DgroupId=com.staples.wst -DartifactId=cryptoj -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=fpeclient.jar -DgroupId=com.staples.wst -DartifactId=fpeclient -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=kmcwrapper.jar -DgroupId=com.staples.wst -DartifactId=kmcwrapper -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=kmsclient.jar -DgroupId=com.staples.wst -DartifactId=kmsclient -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=sslj.jar -DgroupId=com.staples.wst -DartifactId=sslj -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=MQService-0.0.1.jar -DgroupId=com.staples.mqService -DartifactId=MQService -Dversion=0.0.1 -Dpackaging=jar
mvn install:install-file -Dfile=CybersourceClient.jar -DgroupId=com.staples.cybersource -DartifactId=cybersourceClient -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=CyberSourceComm.jar -DgroupId=com.staples.cybersource -DartifactId=cyberSourceComm -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=rt.jar -DgroupId=com.sun -DartifactId=rt -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=connector.jar -DgroupId=javax.resource -DartifactId=connector -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=com.ibm.mq.jar -DgroupId=com.ibm -DartifactId=mq -Dversion=1.0 -Dpackaging=jar -DpomFile=mq-1.0.pom
mvn install:install-file -Dfile=com.ibm.mq.commonservices.jar -DgroupId=com.ibm.mq -DartifactId=commonservices -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=com.ibm.mq.headers.jar -DgroupId=com.ibm.mq -DartifactId=headers -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=com.ibm.mq.jmqi.jar -DgroupId=com.ibm.mq -DartifactId=jmqi -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=com.ibm.mq.pcf.jar -DgroupId=com.ibm.mq -DartifactId=pcf -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=com.ibm.mqjms.jar -DgroupId=com.ibm -DartifactId=mqjms -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=jt400Native.jar -DgroupId=com.ibm -DartifactId=jt400Native -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=jt400.jar -DgroupId=com.ibm -DartifactId=jt400 -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=org.apache.httpcomponents.httpcore-4.2.1.jar -DgroupId=org.apache.httpcomponents -DartifactId=httpcore -Dversion=4.2.1 -Dpackaging=jar
mvn install:install-file -Dfile=wss4j-2.1.jar -DgroupId=com.staples -DartifactId=wss4j -Dversion=2.1 -Dpackaging=jar
mvn install:install-file -Dfile=common-2.5.0.jar -DgroupId=com.staples -DartifactId=common -Dversion=2.5.0 -Dpackaging=jar
mvn install:install-file -Dfile=accountservice.jar -DgroupId=com.staples -DartifactId=accountservice -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=DPSAdapter.jar -DgroupId=com.staples -DartifactId=DPSAdapter -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=DPSClient.jar -DgroupId=com.staples -DartifactId=DPSClient -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=EDUconcurrent.jar -DgroupId=com.staples -DartifactId=EDUconcurrent -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=FDRapidConnect.jar -DgroupId=com.staples -DartifactId=FDRapidConnect -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=GPASSClient.jar -DgroupId=com.staples -DartifactId=GPASSClient -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=jcm.jar -DgroupId=com.staples.wst -DartifactId=jcm -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=POWClient.jar -DgroupId=com.staples -DartifactId=POWClient -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=RapidConnect.jar -DgroupId=com.staples -DartifactId=RapidConnect -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=SecurityFramework.jar -DgroupId=com.staples -DartifactId=SecurityFramework -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=tools.jar -DgroupId=com.staples -DartifactId=tools -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=sun.misc.BASE64Decoder.jar -DgroupId=sun.misc -DartifactId=BASE64Decoder -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=gpas-commons-final.jar -DgroupId=com.staples -DartifactId=gpas-commons-final -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=gpas-commons-final-1.0.jar -DgroupId=com.staples -DartifactId=gpas-commons-final -Dversion=1.1 -Dpackaging=jar
mvn install:install-file -Dfile=gpas-commons-0.0.2.jar -DgroupId=com.staples -DartifactId=gpas-commons -Dversion=0.0.2 -Dpackaging=jar
mvn install:install-file -Dfile=gpas-commons-0.0.2.v1.jar -DgroupId=com.staples -DartifactId=gpas-commons -Dversion=0.0.2.v1 -Dpackaging=jar
mvn install:install-file -Dfile=gpas-commons-0.0.2.v2.jar -DgroupId=com.staples -DartifactId=gpas-commons -Dversion=0.0.2.v2 -Dpackaging=jar
mvn install:install-file -Dfile=gpas-commons-0.0.2.v3.jar -DgroupId=com.staples -DartifactId=gpas-commons -Dversion=0.0.2.v3 -Dpackaging=jar
mvn install:install-file -Dfile=gpas-commons-token-1.0.jar -DgroupId=com.staples -DartifactId=gpas-commons-token -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=gpas-commons-token-cnp-1.0.jar -DgroupId=com.staples -DartifactId=gpas-commons-cnp-token -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=gpas-commons-token-rtl-1.0.jar -DgroupId=com.staples -DartifactId=gpas-commons-rtl-token -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=fps-common.jar -DgroupId=com.staples -DartifactId=fps-commons -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=EmailSend.jar -DgroupId=com.staples -DartifactId=EmailSend -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=JavaAPI.jar -DgroupId=com.staples -DartifactId=JavaAPI -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=cryptojce.jar -DgroupId=com.staples.wst -DartifactId=cryptojce -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=cryptojcommon.jar -DgroupId=com.staples.wst -DartifactId=cryptojcommon -Dversion=1.0 -Dpackaging=jar -DpomFile=cryptojcommon-1.0.pom
mvn install:install-file -Dfile=kmsclient-3.5.jar -DgroupId=com.staples.wst -DartifactId=kmsclient -Dversion=3.5 -Dpackaging=jar -DpomFile=kmsclient-3.5.pom
mvn install:install-file -Dfile=JavaAPI-2.5.9.jar -DgroupId=com.moneris -DartifactId=JavaAPI -Dversion=2.5.9 -Dpackaging=jar
mvn install:install-file -Dfile=RapidConnect-7.0.jar -DgroupId=com.staples -DartifactId=RapidConnect -Dversion=7.0 -Dpackaging=jar
mvn install:install-file -Dfile=com.ibm.ws.webservices.thinclient_6.1.0.jar -DgroupId=com.ibm.ws.webservices -DartifactId=thinclient -Dversion=6.1.0 -Dpackaging=jar
mvn install:install-file -Dfile=com.ibm.ws.webservices.thinclient_8.5.0.jar -DgroupId=com.ibm.ws.webservices -DartifactId=thinclient -Dversion=8.5.0 -Dpackaging=jar
mvn install:install-file -Dfile=settleReport_0.0.1.jar -DgroupId=com.staples -DartifactId=settleReport -Dversion=0.0.1 -Dpackaging=jar
mvn install:install-file -Dfile=firstapi-client-0.0.1.jar -DgroupId=com.staples -DartifactId=firstapi-client -Dversion=0.0.1 -Dpackaging=jar
mvn install:install-file -Dfile=settlementcommon_0.0.1.jar -DgroupId=com.staples -DartifactId=settlementcommon -Dversion=0.0.1 -Dpackaging=jar
mvn install:install-file -Dfile=ojdbc6.jar -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0.3 -Dpackaging=jar
mvn install:install-file -Dfile=ojdbc7.jar -DgroupId=com.github.noraui -DartifactId=ojdbc7 -Dversion=12.1.0.2 -Dpackaging=jar
mvn install:install-file -Dfile=RapidConnect-9.0.jar -DgroupId=com.staples -DartifactId=RapidConnect -Dversion=9.0 -Dpackaging=jar
mvn install:install-file -Dfile=fps-common-1.1.jar -DgroupId=com.staples -DartifactId=fps-commons -Dversion=1.1 -Dpackaging=jar
mvn install:install-file -Dfile=kmcwrapper-1.0.jar -DgroupId=com.staples.kmc -DartifactId=kmcwrapper -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=cardIDDomain-1.0.jar -DgroupId=com.staples.dpsws -DartifactId=cardIDDomain -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=cardIDservice-1.0.jar -DgroupId=com.staples.dpsws -DartifactId=cardIDservice -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=ojdbc7-12.1.0.2.jar -DgroupId=com.oracle.jdbc -DartifactId=ojdbc7 -Dversion=12.1.0.2 -Dpackaging=jar
mvn install:install-file -Dfile=certj-3.1.jar -DgroupId=com.rsa -DartifactId=certj -Dversion=3.1 -Dpackaging=jar
mvn install:install-file -Dfile=jcm-6.1.jar -DgroupId=com.rsa -DartifactId=jcm -Dversion=6.1 -Dpackaging=jar
mvn install:install-file -Dfile=jsafe-4.02.jar -DgroupId=com.rsa -DartifactId=jsafe -Dversion=4.02 -Dpackaging=jar
mvn install:install-file -Dfile=kmsclient-3.5.jar -DgroupId=com.rsa -DartifactId=kmsclient -Dversion=3.5 -Dpackaging=jar
mvn install:install-file -Dfile=sslj-5.1.1.jar -DgroupId=com.rsa -DartifactId=sslj -Dversion=5.1.1 -Dpackaging=jar
mvn install:install-file -Dfile=fpeclient-1.0.jar -DgroupId=com.rsa -DartifactId=fpeclient -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=cryptoj-5.0.1.jar -DgroupId=com.rsa -DartifactId=cryptoj -Dversion=5.0.1 -Dpackaging=jar
mvn install:install-file -Dfile=stpay-authclient-0.0.1-SNAPSHOT.jar -DgroupId=com.staples.pay -DartifactId=stpay-authclient -Dversion=0.0.1-SNAPSHOT -Dpackaging=jar
mvn install:install-file -Dfile=stpayclient-1.0.jar -DgroupId=com.staples.pay -DartifactId=stpayclient -Dversion=1.0 -Dpackaging=jar
mvn install:install-file -Dfile=STPayCommons-0.0.1-SNAPSHOT.jar -DgroupId=com.staples.pay -DartifactId=staplespay -Dversion=0.0.1-SNAPSHOT -Dpackaging=jar
mvn install:install-file -Dfile=nds-2.2.139389.jar -DgroupId=nds.sdk.java -DartifactId=nds -Dversion=2.2.139389 -Dpackaging=jar
rm -r certj.jar

rm -r cryptoj.jar

rm -r fpeclient.jar

rm -r kmcwrapper.jar

rm -r kmsclient.jar

rm -r sslj.jar

rm -r MQService-0.0.1.jar

rm -r CybersourceClient.jar

rm -r CyberSourceComm.jar

rm -r rt.jar

rm -r connector.jar

rm -r com.ibm.mq.jar
rm -r mq-1.0.pom
rm -r com.ibm.mq.commonservices.jar

rm -r com.ibm.mq.headers.jar

rm -r com.ibm.mq.jmqi.jar

rm -r com.ibm.mq.pcf.jar

rm -r com.ibm.mqjms.jar

rm -r jt400Native.jar

rm -r jt400.jar

rm -r org.apache.httpcomponents.httpcore-4.2.1.jar

rm -r wss4j-2.1.jar

rm -r common-2.5.0.jar

rm -r accountservice.jar

rm -r DPSAdapter.jar

rm -r DPSClient.jar

rm -r EDUconcurrent.jar

rm -r FDRapidConnect.jar

rm -r GPASSClient.jar

rm -r jcm.jar

rm -r POWClient.jar

rm -r RapidConnect.jar

rm -r SecurityFramework.jar

rm -r tools.jar

rm -r sun.misc.BASE64Decoder.jar

rm -r gpas-commons-final.jar

rm -r gpas-commons-final-1.0.jar

rm -r gpas-commons-0.0.2.jar

rm -r gpas-commons-0.0.2.v1.jar

rm -r gpas-commons-0.0.2.v2.jar

rm -r gpas-commons-0.0.2.v3.jar

rm -r gpas-commons-token-1.0.jar
rm -r 
rm -r gpas-commons-token-cnp-1.0.jar
rm -r 
rm -r gpas-commons-token-rtl-1.0.jar
rm -r 
rm -r fps-common.jar

rm -r EmailSend.jar

rm -r JavaAPI.jar

rm -r cryptojce.jar

rm -r cryptojcommon.jar
rm -r cryptojcommon-1.0.pom
rm -r kmsclient-3.5.jar
rm -r kmsclient-3.5.pom
rm -r JavaAPI-2.5.9.jar

rm -r RapidConnect-7.0.jar

rm -r com.ibm.ws.webservices.thinclient_6.1.0.jar

rm -r com.ibm.ws.webservices.thinclient_8.5.0.jar

rm -r settleReport_0.0.1.jar

rm -r firstapi-client-0.0.1.jar

rm -r settlementcommon_0.0.1.jar

rm -r ojdbc6.jar

rm -r ojdbc7.jar

rm -r RapidConnect-9.0.jar

rm -r fps-common-1.1.jar

rm -r kmcwrapper-1.0.jar

rm -r cardIDDomain-1.0.jar

rm -r cardIDservice-1.0.jar

rm -r ojdbc7-12.1.0.2.jar

rm -r certj-3.1.jar

rm -r jcm-6.1.jar

rm -r jsafe-4.02.jar

rm -r kmsclient-3.5.jar

rm -r sslj-5.1.1.jar

rm -r fpeclient-1.0.jar

rm -r cryptoj-5.0.1.jar

rm -r stpay-authclient-0.0.1-SNAPSHOT.jar

rm -r stpayclient-1.0.jar

rm -r STPayCommons-0.0.1-SNAPSHOT.jar

rm -r nds-2.2.139389.jar
