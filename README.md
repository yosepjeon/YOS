# YOS

## 실행 방법

### 1.1 순서
#### 1) 해당 파일을 다운로드 받습니다.
#### 2) jar파일들 폴더에 접근합니다.
#### 3) 각 서비스를 실행할 서버에 데이터베이스 테이블을 생성합니다.(하나의 서버에 모두 생성해서 하나의 서버에서 프로그램을 한꺼번에 실행해도 가능)
#### MYSQL을 사용해주시기 바랍니다.
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
#### 4) 두개의 프로그램은 가장 먼저 실행하되, 반드시 순서대로 프로그램을 실행해주세요.
####    - java -jar yosep-spring-shoppingsite-msa-configserver-0.0.1-SNAPSHOT.jar
####    - java -jar yosep-spring-shoppingsite-msa-eurekaserver-0.0.1-SNAPSHOT.jar

#### 4) 두개의 프로그램을 실행하였으면, 각 서비스를 실행할 서버에 나머지 프로그램들을 실행합니다.(로컬에 한꺼번에 실행 가능)
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
###### java -jar yosep-spring-shoppingsite-msa-configserver-0.0.1-SNAPSHOT.jar

