# hoteladvisors-task

## Urls
Main page:
* http://localhost:8080/web/index.zul

Contact page:
* http://localhost:8080/web/contact_page.zul

Address page: 
* http://localhost:8080/web/address_page.zul
* http://localhost:8080/web/address_page.zul?contactId=test

## Tradeoffs
* Indexes not used;
* Filters implemented at Java;
* CSS and Layout almost ignored;
* No CLI for WildFly;
* No configurability;
* English only, no lang bundles;

## How to deploy
1) Setup MySQL driver and datasource at WildFly, suggested schema: sample-app;
2) Deploy via WildFly UI;

## Useful links
https://synaptiklabs.com/posts/adding-the-mysql-jdbc-driver-into-wildfly/

