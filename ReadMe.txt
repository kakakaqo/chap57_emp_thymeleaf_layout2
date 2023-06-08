[특이사항]

 1. 사원관리 + Maven + Thymeleaf 뷰 템플릿 엔진
 
 2. chap56_emp_thymeleaf 
  - 새 프로젝트를 생성하고 기존의 사원관리 기능의 모듈을 복사해옴.
 
3. 사원 목록 + 사원 정보 상세 보기 
 
 [진행 순서]
  
 1. 의존성 pom.xml
 
 	
		<!--타임리프 의존성-->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
		
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		
		<!--다양한 편의기능 - 라이브 리로드 -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		
		<!--롬복-->
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		
		<!--마이바티스-->
		<dependency>
			<groupId>org.mybatis.spring.boot</groupId>
			<artifactId>mybatis-spring-boot-starter</artifactId>
			<version>2.3.0</version>
		</dependency>
		
		<!--히카리 커넥션 풀(스프링 부트는 기본 주입) -->
		<!--<dependency>
			<groupId>com.zaxxer</groupId>
			<artifactId>HikariCP</artifactId>
		</dependency>-->

		<!-- 로깅 라이브러리 logback-classic -->
		<dependency>
		    <groupId>ch.qos.logback</groupId>
		    <artifactId>logback-classic</artifactId>
		    <scope>runtime</scope>
		</dependency>		
		
		<!--SQL 로그 편집-->
		<dependency>
		    <groupId>org.bgee.log4jdbc-log4j2</groupId>
		    <artifactId>log4jdbc-log4j2-jdbc4.1</artifactId>
		    <version>1.16</version>
		</dependency>		

		<!--내장 톰캣-->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-tomcat</artifactId>
			<scope>provided</scope>
		</dependency>		

		<!-- JUnit Jupiter API -->
	    <dependency>
	        <groupId>org.junit.jupiter</groupId>
	        <artifactId>junit-jupiter-api</artifactId>
	        <scope>test</scope>
	    </dependency>
	    
	    <!-- JUnit Jupiter Engine -->
	    <dependency>
	        <groupId>org.junit.jupiter</groupId>
	        <artifactId>junit-jupiter-engine</artifactId>
	        <scope>test</scope>
	    </dependency>
	    
	    <!-- Mockito Core -->
	    <dependency>
	        <groupId>org.mockito</groupId>
	        <artifactId>mockito-core</artifactId>
	        <scope>test</scope>
	    </dependency>
	    
	    <!-- Mockito JUnit Jupiter -->
	    <dependency>
	        <groupId>org.mockito</groupId>
	        <artifactId>mockito-junit-jupiter</artifactId>
	        <scope>test</scope>
	    </dependency>
		
		<!--마리아DB 의존성-->
		<dependency>
			<groupId>org.mariadb.jdbc</groupId>
			<artifactId>mariadb-java-client</artifactId>
			<scope>runtime</scope>
		</dependency>
		
2. 이상태로 어플리케이션을 실행하면 오류 발생
 - 마이바티스 매퍼XML, 데이터베이스 관련 설정이 안되어서 어플리케이션 실행 오류남.
 
3. 오류 내용  		
 - No MyBatis mapper was found in '[com.javalab.board]' package. Please check your configuration. 2023-06-04 11:59:11.121[0;39m [33m WARN[0;39m [35m15836[0;39m [2m---[0;39m [2m[  restartedMain][0;39m [36mion$DefaultTemplateResolverConfiguration[0;39m [2m:[0;39m Cannot find template location: classpath:/templates/ (please add some templates, check your Thymeleaf configuration, or set spring.thymeleaf.check-template-location=false)
 - Cannot find template location: classpath:/templates/ (please add some templates, check your Thymeleaf configuration, or set spring.thymeleaf.check-template-location=false)
 - Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'dataSource' defined in class path resource [org/springframework/boot/autoconfigure/jdbc/DataSourceConfiguration$Hikari.class]: Bean instantiation via factory method failed; nested exception is org.springframework.beans.BeanInstantiationException: Failed to instantiate [com.zaxxer.hikari.HikariDataSource]: Factory method 'dataSource' threw exception; nested exception is org.springframework.boot.autoconfigure.jdbc.DataSourceProperties$DataSourceBeanCreationException: Failed to determine a suitable driver class
 
4. application.properties 설정
 1) application.properties 파일 생성
 2) chap54_maven_mariadb 프로젝트의 application.properties 내용 복사
 3) Thymeleaf 관련 설정
	 # 뷰리졸버(Thymeleaf)
	spring.thymeleaf.enabled=true
	spring.thymeleaf.cache=false
	spring.thymeleaf.check-template-location=true
	spring.thymeleaf.prefix=classpath:/templates/
	spring.thymeleaf.suffix=.html

5. 매퍼XML 설정
 chap54_maven_mariadb 프로젝트의 mapper 폴더 복사
 
6. 자바 패키지(클래스) 구성
 1) chap54_maven_mariadb 프로젝트의 패키지(클래스) 복사
   - HomeController 내용중 return 부분을 다음과 같이 변경
   	return "redirect:/emp/list";  
   - EmployeeControll 복사
   - vo, service, dao 복사
   
7. logback 로그 파일 복사

8. 프로그램 실행

[추가 작업]

1. 사원 상세 보기 구현



 
 