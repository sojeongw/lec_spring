package ems.member.configration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ems.member.DataBaseConnectionInfo;
import ems.member.dao.StudentDao;
import ems.member.service.EMSInformationService;
import ems.member.service.StudentAllSelectService;
import ems.member.service.StudentDeleteService;
import ems.member.service.StudentModifyService;
import ems.member.service.StudentRegisterService;
import ems.member.service.StudentSelectService;

@Configuration
public class MemberConfig {

	// <bean id="studentDao" class="ems.member.dao.StudentDao" ></bean>
	// 해당 객체의 이름을 가진 메소드를 만든다.
	// Bean annotaion을 붙여준다.
	@Bean
	public StudentDao studentDao() {
		return new StudentDao();
	}

	/*
	 * <bean id="registerService" class="ems.member.service.StudentRegisterService">
	 * <constructor-arg ref="studentDao" ></constructor-arg> </bean>
	 */
	@Bean
	public StudentRegisterService registerService() {
		return new StudentRegisterService(studentDao());
	}

	@Bean
	public StudentModifyService modifyService() {
		return new StudentModifyService(studentDao());
	}

	@Bean
	public StudentSelectService selectService() {
		return new StudentSelectService(studentDao());
	}

	@Bean
	public StudentDeleteService deleteService() {
		return new StudentDeleteService(studentDao());
	}

	@Bean
	public StudentAllSelectService allSelectService() {
		return new StudentAllSelectService(studentDao());
	}

	/*
	 * <bean id="dataBaseConnectionInfoDev"
	 * class="ems.member.DataBaseConnectionInfo"> <property name="jdbcUrl"
	 * value="jdbc:oracle:thin:@localhost:1521:xe" /> <property name="userId"
	 * value="scott" /> <property name="userPw" value="tiger" /> </bean>
	 */
	@Bean
	public DataBaseConnectionInfo dataBaseConnectionInfoDev() {
		// 일단 새 객체를 만들어준다
		DataBaseConnectionInfo infoDev = new DataBaseConnectionInfo();
		// property name 메소드 안에 value값을 넣는다.
		infoDev.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
		infoDev.setUserId("scott");
		infoDev.setUserPw("tiger");

		return infoDev;
	}

	@Bean
	public DataBaseConnectionInfo dataBaseConnectionInfoReal() {
		DataBaseConnectionInfo infoReal = new DataBaseConnectionInfo();
		infoReal.setJdbcUrl("jdbc:oracle:thin:@192.168.0.1:1521:xe");
		infoReal.setUserId("masterid");
		infoReal.setUserPw("masterpw");

		return infoReal;
	}

	@Bean
	public EMSInformationService informationService() {
		EMSInformationService info = new EMSInformationService();
		info.setInfo("Education Management System program was developed in 2015.");
		info.setCopyRight("COPYRIGHT(C) 2015 EMS CO., LTD. ALL RIGHT RESERVED. CONTACT MASTER FOR MORE INFORMATION.");
		info.setVer("The version is 1.0");
		info.setsYear(2015);
		info.setsMonth(1);
		info.setsDay(1);
		info.seteYear(2015);
		info.seteMonth(2);
		info.seteDay(28);

		// <property name="developers">
		// <list>
		// <value>Cheney.</value>
		// <value>Eloy.</value>
		// <value>Jasper.</value>
		// <value>Dillon.</value>
		// <value>Kian.</value>
		// </list>
		// </property>
		ArrayList<String> developers = new ArrayList<String>();
		developers.add("Cheney.");
		developers.add("Eloy.");
		developers.add("Jasper.");
		developers.add("Dillon.");
		developers.add("Kian.");
		info.setDevelopers(developers);

		// 가장 대표적으로 사용되는 것이 hashmap이므로 hashmap 사용
		Map<String, String> administrators = new HashMap<String, String>();
		administrators.put("Cheney", "cheney@springPjt.org");
		administrators.put("Jasper", "jasper@springPjt.org");
		info.setAdministrators(administrators);

		Map<String, DataBaseConnectionInfo> dbInfos = new HashMap<String, DataBaseConnectionInfo>();
		// 위에 썼던 메소드를 호출해 객체를 리턴받는다.
		dbInfos.put("dev", dataBaseConnectionInfoDev());
		dbInfos.put("real", dataBaseConnectionInfoReal());
		info.setDbInfos(dbInfos);

		return info;
	}

}
