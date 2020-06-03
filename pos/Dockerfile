FROM openjdk:11-jre-slim
ADD build/libs/pos*.jar /opt/epl/epl.jar
ENV JAVA_OPTIONS=""
CMD java $JAVA_OPTIONS -jar /opt/epl/epl.jar
ENTRYPOINT ["java","-jar","/opt/epl/epl.jar"]