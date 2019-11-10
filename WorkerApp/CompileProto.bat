cd app\src\main\java\com\prod\app\protos
echo [COMPILEING..........]
protoc deviceinfo.proto --java_out=..\..\..\..\
protoc responsestatusenum.proto --java_out=..\..\..\..\
protoc address.proto --java_out=..\..\..\..\
protoc persontypeenum.proto --java_out=..\..\..\..\
protoc summary.proto --java_out=..\..\..\..\
protoc time.proto --java_out=..\..\..\..\
protoc email.proto --java_out=..\..\..\..\
protoc mobile.proto --java_out=..\..\..\..\
protoc contactdetails.proto --java_out=..\..\..\..\
protoc entity.proto --java_out=..\..\..\..\
protoc names.proto --java_out=..\..\..\..\
protoc gender.proto --java_out=..\..\..\..\
protoc uid.proto --java_out=..\..\..\..\
protoc worker.proto --java_out=..\..\..\..\
protoc workertype.proto --java_out=..\..\..\..\
protoc login.proto --java_out=..\..\..\..\
protoc workersearch.proto --java_out=..\..\..\..\
protoc consumer.proto --java_out=..\..\..\..\
protoc registration.proto --java_out=..\..\..\..\
protoc task.proto --java_out=..\..\..\..\
cd ..
cd ..
cd ..
cd ..
cd ..
cd ..
cd ..
cd ..

