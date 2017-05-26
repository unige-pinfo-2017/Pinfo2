FROM jboss/wildfly

ADD db-setup.sh install-db-driver.sh wildfly-setup.sh /opt/jboss/wildfly/

USER root
RUN mkdir -p /opt/jboss/wildfly/standalone/deployments
RUN mkdir -p /opt/newwarstash

RUN ["/opt/jboss/wildfly/wildfly-setup.sh"]
RUN /opt/jboss/wildfly/bin/add-user.sh admin admin
RUN rm -rf  /opt/jboss/wildfly/standalone/configuration/standalone_xml_history
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "--debug", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]

EXPOSE 8080
EXPOSE 8787
EXPOSE 9990

