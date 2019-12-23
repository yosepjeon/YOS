# YOS

## 실행 방법
#### 해당 프로젝트는 로컬환경에서 모두 실행한다는 기준으로 설명하고 있습니다. 각 서비스 별로 서버를 두어 실행하기 위해서는 
#### Config 프로젝트에서 local호스트 설정값들을 모두 각자의 서비스 url로 설정하시고,
#### Git의 Config Repository의 order-service-dev.properties, product-service-dev.properties의 설정 값중에서
#### spring.rabbitmq.host=localhost 부분들을 RabbitMQ를 따로 설치하신 서버의 URL로 수정하시기 바랍니다.

### 1.1 순서
#### 1) 해당 파일을 다운로드 받습니다.
#### 2) 기존의 제가 만든 Repository 설정을 사용해도 되지만, 그렇지 않으실 분들은 여러분들의 Git Repository에 설정파일을 모아놓을 Repository를 하나 만듭니다. 그다음 https://github.com/yosepjeon/yosep-spring-shoppingsite-msa-configserver 에 있는 설정값들을 받아서 여러분들 서버 설정에 맞게 수정합니다. 마지막으로 https://github.com/yosepjeon/YOS 의 yosep-spring-shoppingsite-msa-configserver파일을 다운받아서 src/main/resources/bootstrap.properties의 설정값 중 spring.cloud.config.server.git.uri=https://github.com/yosepjeon/yosep-spring-shoppingsite-msa-configserver.git 이 부분을 여러분의 config Repository 주소로 수정한 뒤에 Rebuild해서 새로운 jar파일을 만듭니다.
####  
#### 3) 각 서비스를 실행할 서버에 데이터베이스 테이블을 생성합니다.(하나의 서버에 모두 생성해서 하나의 서버에서 프로그램을 한꺼번에 실행해도 가능)
#### *MYSQL을 사용해주시기 바랍니다.
##### 3-1. user 서비스를 실행할 서버의 데이터베이스에 해당 테이블을 생성합니다.
###### 1. USER TABLE
###### create table yoggaebi_user(
###### 	userId varchar(50) primary key not null,
######     password varchar(100) not null,
######     name varchar(50) not null,
######     email varchar(100) not null,
######     phone varchar(50) not null,
######     postCode varchar(50) not null,
######     roadAddr varchar(50) not null,
######     jibunAddr varchar(50) not null,
######     extraAddr varchar(50) not null,
######     detailAddr varchar(50) not null,
######     user_rdate datetime default CURRENT_TIMESTAMP,
######     user_udate datetime default CURRENT_TIMESTAMP
###### )default charset=utf8 collate utf8_general_ci;
* * *
##### 3-2. product 서비스를 실행할 서버의 데이터베이스에 해당 테이블을 생성합니다.
###### 1. PRODUCT TABLE
###### create table yoggaebi_product(
###### 	productId varchar(300) primary key,
######     productName varchar(100) not null,
######     productSale int,
######     productPrice int not null,
######     productQuantity int not null,
######     productDetail text,
######     productType text,
######     product_rdate datetime default CURRENT_TIMESTAMP,
######     product_udate datetime default CURRENT_TIMESTAMP
###### )default charset=utf8 collate utf8_general_ci;
###### 2. PRODUCT PROFILE TABLE
###### create table yoggaebi_product_profile_files(
###### 	productId varchar(300) not null,
######     fileId bigint primary key auto_increment,
######     fileName varchar(100) not null,
######     url varchar(300) not null,
######     product_rdate datetime default CURRENT_TIMESTAMP,
######     product_udate datetime default CURRENT_TIMESTAMP,
######     constraint fk_productprofile_productId_reference_yoggaebi_product foreign key(productId) references yoggaebi_product(productId) on delete cascade on update cascade
###### )default charset=utf8 collate utf8_general_ci;
###### 3. PRODUCT DESCRIPTION TABLE
###### create table yoggaebi_product_description(
###### 	productId varchar(300) not null,
######     descriptionId bigint primary key auto_increment,
######     url varchar(300) not null,
######     productDescription varchar(1000) not null,
######     product_rdate datetime default CURRENT_TIMESTAMP,
######     product_udate datetime default CURRENT_TIMESTAMP,
###### 	constraint fk_productdescription_productId_reference_yoggaebi_product foreign key(productId) references yoggaebi_product(productId) on delete cascade on update cascade
###### )default charset=utf8 collate utf8_general_ci;
* * *
##### 3-3 order 서비스를 실행할 서버의 데이터베이스에 해당 테이블을 생성합니다.
###### 1. ORDER TABLE
###### create table yoggaebi_order(
###### 	  orderId varchar(300) primary key,
######     productId varchar(300) not null,
######     senderId varchar(50) not null,
######     senderName varchar(50) not null,
######     receiverName varchar(50),
######     phone varchar(50),
######     postCode varchar(50),
######     roadAddr varchar(50),
######     jibunAddr varchar(50),
######     extraAddr varchar(50),
######     detailAddr varchar(50),
######     isBuy boolean,
######     user_rdate datetime default CURRENT_TIMESTAMP,
######     user_udate datetime default CURRENT_TIMESTAMP
###### )default charset=utf8 collate utf8_general_ci;
* * *
#### 4) RabbitMQ를 설치합니다.(해당 설명은 Local에 설치한다고 가정, MessageQueue 서버를 따로 둘 경우, 맨 위의 설명 참조...)
* * *
#### 5) 가장 먼저 RabbitMQ를 실행 -> configserver.jar 실행 -> eurekaserver.jar 실행!! 반드시 순서대로 프로그램을 실행해주세요.
####    - java -jar yosep-spring-shoppingsite-msa-configserver-0.0.1-SNAPSHOT.jar
####    - java -jar yosep-spring-shoppingsite-msa-eurekaserver-0.0.1-SNAPSHOT.jar

#### 6) 두개의 프로그램을 실행하였으면, 각 서비스를 실행할 서버에 나머지 프로그램들을 실행합니다.(로컬에 한꺼번에 실행 가능)
##### order 서버
###### java -jar yosep-spring-shoppingsite-msa-order-apigateway-0.0.1-SNAPSHOT.jar
###### java -jar yosep-spring-shoppingsite-msa-orderservice-0.0.1-SNAPSHOT.jar
* * *
##### product 서버
###### java -jar yosep-spring-shoppingsite-msa-product-apigateway-0.0.1-SNAPSHOT.jar
###### java -jar yosep-spring-shoppingsite-msa-productservice-0.0.1-SNAPSHOT.jar
* * *
##### user 서버
###### java -jar yosep-spring-shoppingsite-msa-user-apigateway-0.0.1-SNAPSHOT.jar
###### java -jar yosep-spring-shoppingsite-msa-userservice-0.0.1-SNAPSHOT.jar
* * *
##### browser 서버
###### java -jar yosep-spring-shoppingsite-msa-website-0.0.1-SNAPSHOT.jar

