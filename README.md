# Camel AMQP+SSL application publishing messages in an EnMasse Broker in Openshift 

This example shows how to connect camel application to EnMasse (MaaS) message broker inside Openshift.

Doc: enmasse.io

## Requirements

EnMasse installed on Openshift cluster (http://enmasse.io/documentation/0.15.3/#installing-openshift).

All pods should be up & Running:

```
$ oc get pods
NAME                                        READY     STATUS      RESTARTS   AGE
address-controller-2825298802-g440s         1/1       Running     1          6d
admin-1573428571-sbwm3                      4/4       Running     4          6d
mqtt-gateway-3745435576-zxslb               2/2       Running     2          6d
mqtt-lwt-2604437113-blz6r                   1/1       Running     5          6d
none-authservice-95437037-wq7b1             1/1       Running     20         6d
qdrouterd-4021847281-5sl6k                  2/2       Running     3          6d
subserv-4223738148-cmwfn                    1/1       Running     1          6d
```

    mvn clean install

## Setup

### EnMasse Setup

1. Connect to EnMasse Console (you can get the url by running "oc get route"), in Addresses menu, click on create new address in order to create a Persisted Queue "QueuePersisted"

![alt text](https://raw.githubusercontent.com/abouchama/camel-enmasse-amqps/master/images/Address_enmasse.png)

2. Running oc get pods, you will see a new pod created with the name of the queue:

e.g;
```
queuepersisted-3024989586-sn9qp             1/1       Running     1          6d
```

3. Getting TLS certificates to use in the client application:

```
cd src/main/deployments
oc extract secret/external-certs-messaging --to=amqp-certs -n $NAMESPACE    
```

The certificate keys in the deployments/amqp-certs directory are copied to the container in the /deployments/amqp-certs path.

After, run the following to convert tls.cert to keystore format

```
keytool -v -import -file tls.crt -alias somecrt -keystore my-cacerts
```

4. Now you have just to configure some properties in application.properties:
```
activemq.route:messaging-myproject.192.168.42.241.nip.io
activemq.broker.parameters=transport.trustStoreLocation=/deployments/amqp-certs/my-cacerts&transport.trustStorePassword=redhat&transport.verifyHost=false
activemq.broker.username=admin
activemq.broker.password=admin
```

5. The example can be built and run on OpenShift using a single goal:
   
   mvn fabric8:deploy

If everything works you should see the messages being produced and consumed in a QueuePersisted queue with this awesome live metrics:

![alt text](https://raw.githubusercontent.com/abouchama/camel-enmasse-amqps/master/images/enmasse_dashboard.png)


    


