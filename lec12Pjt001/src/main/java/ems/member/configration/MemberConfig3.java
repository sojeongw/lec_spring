package ems.member.configration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ems.member.DataBaseConnectionInfo;
import ems.member.service.EMSInformationService;

@Configuration
public class MemberConfig3 {
	/*
	 * @Bean public DataBaseConnectionInfo dataBaseConnectionInfoDev() {
	 * DataBaseConnectionInfo infoDev = new DataBaseConnectionInfo();
	 * infoDev.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
	 * infoDev.setUserId("scott"); infoDev.setUserPw("tiger"); return infoDev; }
	 * 
	 * @Bean public DataBaseConnectionInfo dataBaseConnectionInfoReal() {
	 * DataBaseConnectionInfo infoReal = new DataBaseConnectionInfo();
	 * infoReal.setJdbcUrl("jdbc:oracle:thin:@192.168.0.1:1521:xe");
	 * infoReal.setUserId("masterid"); infoReal.setUserPw("masterpw"); return
	 * infoReal; }
	 */

	// 어노테이션만 붙여주면 2번 파일에 있는 객체를 사용할 수 있다. = 자동으로 의존 설정이 된다.
	// 생성된 bean객체는 스프링 컨테이너에 다 담기기 때문이다.
	@Autowired
	DataBaseConnectionInfo dataBaseConnectionInfoDev;

	@Autowired
	DataBaseConnectionInfo dataBaseConnectionInfoReal;

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

		ArrayList<String> developers = new ArrayList<String>();
		developers.add("Cheney.");
		developers.add("Eloy.");
		developers.add("Jasper.");
		developers.add("Dillon.");
		developers.add("Kian.");
		info.setDevelopers(developers);

		Map<String, String> administrators = new HashMap<String, String>();
		administrators.put("Cheney", "cheney@springPjt.org");
		administrators.put("Jasper", "jasper@springPjt.org");
		info.setAdministrators(administrators);

		Map<String, DataBaseConnectionInfo> dbInfos = new HashMap<String, DataBaseConnectionInfo>();
		dbInfos.put("dev", dataBaseConnectionInfoDev);
		dbInfos.put("real", dataBaseConnectionInfoReal);
		info.setDbInfos(dbInfos);

		return info;
	}

}
