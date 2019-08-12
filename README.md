# beer-service

Demo project for Spring Boot Beer Lovers.

## Build Application

```
./mvnw clean package
```

## Build Docker Container

* By adding `Dockerfile` you can build the container image, e.g.

```
FROM adoptopenjdk/openjdk11:alpine-jre
MAINTAINER Neven Cvetkovic <nevenc@pivotal.io>
ARG JAR_FILE
ADD ${JAR_FILE} /app.jar
ENTRYPOINT ["/opt/java/openjdk/bin/java", "-jar", "/app.jar" ]
```

```
docker build --build-arg JAR_FILE=target/beer-service-0.0.1-SNAPSHOT.jar -t nevenc/beer-service:0.0.1 -t nevenc/beer-service:latest .
```

## Run Application

```
java -jar target/beer-service-0.0.1-SNAPSHOT.jar
```

## Run Application in local Docker

```
docker run -d -p 8080:8080 nevenc/beer-service/latest
```

## Push a container image to container registry

* We are using Docker Hub as a container registry. You can use other container registries, e.g. GCR, Harbor, etc.

```
docker login
docker push nevenc/beer-service:0.0.1
docker push nevenc/beer-service:latest
```

## Deploy to Pivotal Application Service (PAS)

* You can easily deploy this app to PAS (Pivotal Application Service) or a similar PaaS (Platform as a Service).

```
cf push
```

* Here's an example of pushing the application to Pivotal Web Services (PWS) at http://run.pivotal.io, e.g.

```
% cf push                                                                                                        ✹ ✚ ✭
Pushing from manifest to org my-org / space my-space as xxxx@xxxx.xxxx ...
Using manifest file /Users/user/workspace/beer-service/manifest.yml
Getting app info...
Creating app with these attributes...
+ name:       beer-service
  path:       /Users/user/workspace/beer-service/target/beer-service-0.0.1-SNAPSHOT.jar
+ memory:     1G
  env:
+   JBP_CONFIG_OPEN_JDK_JRE
  routes:
+   beer-service.cfapps.io

Creating app beer-service...
Mapping routes...
Comparing local files to remote cache...
Packaging files to upload...
Uploading files...
 400.67 KiB / 400.67 KiB [=================================================================================] 100.00% 1s

Waiting for API to complete processing files...

Staging app and tracing logs...
   Downloading dotnet_core_buildpack_beta...
   Downloading dotnet_core_buildpack...
   Downloading python_buildpack...
   Downloading php_buildpack...
   Downloading nodejs_buildpack...
   Downloaded python_buildpack
   Downloaded php_buildpack
   Downloading binary_buildpack...
   Downloaded dotnet_core_buildpack_beta
   Downloading staticfile_buildpack...
   Downloading go_buildpack...
   Downloaded dotnet_core_buildpack
   Downloading java_buildpack...
   Downloaded binary_buildpack
   Downloading ruby_buildpack...
   Downloaded java_buildpack
   Downloaded go_buildpack
   Downloaded nodejs_buildpack
   Downloaded staticfile_buildpack
   Downloaded ruby_buildpack
   Cell 8b9de04a-84b4-4367-b97a-7bd56a4d0d62 creating container for instance c0c4427a-a314-4b44-bf81-a9b0b15294eb
   Cell 8b9de04a-84b4-4367-b97a-7bd56a4d0d62 successfully created container for instance c0c4427a-a314-4b44-bf81-a9b0b15294eb
   Downloading app package...
   Downloaded app package (36.4M)
   -----> Java Buildpack v4.20 (offline) | https://github.com/cloudfoundry/java-buildpack.git#2cd7e3e
   -----> Downloading Jvmkill Agent 1.16.0_RELEASE from https://java-buildpack.cloudfoundry.org/jvmkill/bionic/x86_64/jvmkill-1.16.0-RELEASE.so (found in cache)
   -----> Downloading Open Jdk JRE 11.0.4_11 from https://java-buildpack.cloudfoundry.org/openjdk/bionic/x86_64/openjdk-jre-11.0.4_11-bionic.tar.gz (found in cache)
          Expanding Open Jdk JRE to .java-buildpack/open_jdk_jre (1.4s)
          JVM DNS caching disabled in lieu of BOSH DNS caching
   -----> Downloading Open JDK Like Memory Calculator 3.13.0_RELEASE from https://java-buildpack.cloudfoundry.org/memory-calculator/bionic/x86_64/memory-calculator-3.13.0-RELEASE.tar.gz (found in cache)
          Loaded Classes: 23485, Threads: 250
   -----> Downloading Client Certificate Mapper 1.8.0_RELEASE from https://java-buildpack.cloudfoundry.org/client-certificate-mapper/client-certificate-mapper-1.8.0-RELEASE.jar (found in cache)
   -----> Downloading Container Security Provider 1.16.0_RELEASE from https://java-buildpack.cloudfoundry.org/container-security-provider/container-security-provider-1.16.0-RELEASE.jar (found in cache)
   -----> Downloading Spring Auto Reconfiguration 2.7.0_RELEASE from https://java-buildpack.cloudfoundry.org/auto-reconfiguration/auto-reconfiguration-2.7.0-RELEASE.jar (found in cache)
   Exit status 0
   Uploading droplet, build artifacts cache...
   Uploading droplet...
   Uploading build artifacts cache...
   Uploaded build artifacts cache (128B)
   Uploaded droplet (81.4M)
   Uploading complete
   Cell 8b9de04a-84b4-4367-b97a-7bd56a4d0d62 stopping instance c0c4427a-a314-4b44-bf81-a9b0b15294eb
   Cell 8b9de04a-84b4-4367-b97a-7bd56a4d0d62 destroying container for instance c0c4427a-a314-4b44-bf81-a9b0b15294eb
   Cell 8b9de04a-84b4-4367-b97a-7bd56a4d0d62 successfully destroyed container for instance c0c4427a-a314-4b44-bf81-a9b0b15294eb

Waiting for app to start...

name:              beer-service
requested state:   started
routes:            beer-service.cfapps.io
last uploaded:     Mon 12 Aug 13:00:23 CEST 2019
stack:             cflinuxfs3
buildpacks:        client-certificate-mapper=1.8.0_RELEASE container-security-provider=1.16.0_RELEASE
                   java-buildpack=v4.20-offline-https://github.com/cloudfoundry/java-buildpack.git#2cd7e3e
                   java-main java-opts java-security jvmkill-agent=1.16.0_RELEASE open-jdk-...

type:            web
instances:       1/1
memory usage:    1024M
start command:   JAVA_OPTS="-agentpath:$PWD/.java-buildpack/open_jdk_jre/bin/jvmkill-1.16.0_RELEASE=printHeapHistogram=1
                 -Djava.io.tmpdir=$TMPDIR -XX:ActiveProcessorCount=$(nproc) -Djava.ext.dirs=
                 -Djava.security.properties=$PWD/.java-buildpack/java_security/java.security $JAVA_OPTS" &&
                 CALCULATED_MEMORY=$($PWD/.java-buildpack/open_jdk_jre/bin/java-buildpack-memory-calculator-3.13.0_RELEASE
                 -totMemory=$MEMORY_LIMIT -loadedClasses=24785 -poolType=metaspace -stackThreads=250
                 -vmOptions="$JAVA_OPTS") && echo JVM Memory Configuration: $CALCULATED_MEMORY && JAVA_OPTS="$JAVA_OPTS
                 $CALCULATED_MEMORY" && MALLOC_ARENA_MAX=2 SERVER_PORT=$PORT eval exec
                 $PWD/.java-buildpack/open_jdk_jre/bin/java $JAVA_OPTS -cp
                 $PWD/.:$PWD/.java-buildpack/container_security_provider/container_security_provider-1.16.0_RELEASE.jar
                 org.springframework.boot.loader.JarLauncher
     state     since                  cpu    memory         disk         details
#0   running   2019-08-12T12:00:00Z   0.0%   109.2M of 1G   170M of 1G   

```

* Check `manifest.yml` file for deployment details, e.g.
  * name of the app
  * hostname
  * memory allocation for the app (container)
  * number of instances
  * JDK version number
  * etc.

  
```
applications:
- name: beer-service
  memory: 1G
  path: target/beer-service-0.0.1-SNAPSHOT.jar
  # services:
  #   - beer-service-db
  env:
    JBP_CONFIG_OPEN_JDK_JRE: '{ jre: { version: 11.+ } }'
```

## Deploy to Kubernetes (k8s)

* You can deploy the container image to a Kubernetes cluster (e.g. PKS, GKE, Docker Kubernetes, etc)

```
kubectl run beer-service --image=nevenc/beer-service:0.0.1 --port=8080
kubectl expose deployment beer-service --type=NodePort
```

```
% kubectl get all                                                                                                             ✹
NAME                                READY   STATUS    RESTARTS   AGE
pod/beer-service-6987bb7944-n9x4t   1/1     Running   0          81s


NAME                   TYPE        CLUSTER-IP      EXTERNAL-IP   PORT(S)          AGE
service/beer-service   NodePort    10.102.170.54   <none>        8080:30645/TCP   3s
service/kubernetes     ClusterIP   10.96.0.1       <none>        443/TCP          41h


NAME                           READY   UP-TO-DATE   AVAILABLE   AGE
deployment.apps/beer-service   1/1     1            1           81s

NAME                                      DESIRED   CURRENT   READY   AGE
replicaset.apps/beer-service-6987bb7944   1         1         1       81s
```

* Take a note of the NodePort (if running local Kubernetes on your laptop), e.g.

```
% http localhost:30645                                                                                                    1 ↵ ✹
HTTP/1.1 200 
Content-Type: application/hal+json;charset=UTF-8
Date: Mon, 12 Aug 2019 12:00:00 GMT
Transfer-Encoding: chunked

{
    "_links": {
        "beers": {
            "href": "http://localhost:30645/beers{?page,size,sort}",
            "templated": true
        },
        "profile": {
            "href": "http://localhost:30645/profile"
        }
    }
}
```

* If running on Pivotal Container Service (PKS with NSXT) or Google Cloud Enginer (GKE), or other public cloud providers - you could use an external load balancer, e.g.

```
kubectl expose deployment beer-service --type=LoadBalancer --port=80 --target-port=80
```

## Rest Endpoints

* Use 'httpie' to test the REST endpoints, e.g. https://httpie.org/
* It's easy to install `httpie` client on Mac, e.g.

```
brew install httpie
```

* Test the BeerRepository REST endpoints, e.g.

```
% http localhost:8080

HTTP/1.1 200 
Content-Type: application/hal+json;charset=UTF-8
Date: Mon, 12 Aug 2019 12:00:00 GMT
Transfer-Encoding: chunked

{
    "_links": {
        "beers": {
            "href": "http://localhost:8080/beers{?page,size,sort}",
            "templated": true
        },
        "profile": {
            "href": "http://localhost:8080/profile"
        }
    }
}
```

```
% http localhost:8080/beers

HTTP/1.1 200 
Content-Type: application/hal+json;charset=UTF-8
Date: Mon, 12 Aug 2019 12:00:00 GMT
Transfer-Encoding: chunked

{
    "_embedded": {
        "beers": []
    },
    "_links": {
        "profile": {
            "href": "http://localhost:8080/profile/beers"
        },
        "self": {
            "href": "http://localhost:8080/beers{?page,size,sort}",
            "templated": true
        }
    },
    "page": {
        "number": 0,
        "size": 20,
        "totalElements": 0,
        "totalPages": 0
    }
}
```

```
% http post localhost:8080/beers name="New Beer" hop="HopMax" malt="Super Malt" yeast="Y2K" style="Ale" 

HTTP/1.1 201 
Content-Type: application/json;charset=UTF-8
Date: Mon, 12 Aug 2019 12:00:00 GMT
Location: http://localhost:8080/beers/1
Transfer-Encoding: chunked

{
    "_links": {
        "beer": {
            "href": "http://localhost:8080/beers/1"
        },
        "self": {
            "href": "http://localhost:8080/beers/1"
        }
    },
    "hop": "HopMax",
    "malt": "Super Malt",
    "name": "New Beer",
    "style": "Ale",
    "yeast": "Y2K"
}
```

* Test BeerInitializer endpoints

```
% http get localhost:8080/init                                                                          ✹
HTTP/1.1 200 
Content-Length: 20
Content-Type: application/json;charset=UTF-8
Date: Mon, 12 Aug 2019 12:00:00 GMT

Created 10 record(s)
```

```
% http get 'localhost:8080/init?count=100'                                                                           ✹
HTTP/1.1 200 
Content-Length: 21
Content-Type: text/plain;charset=UTF-8
Date: Mon, 12 Aug 2019 12:00:00 GMT

Created 100 record(s)
```