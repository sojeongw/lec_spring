<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<h1> memJoinOk </h1>
	<%--
	ID : ${memId}<br />
	PW : ${memPw}<br />
	Mail : ${memMail} <br />
	Phone : ${memPhone} <br />
	--%>
	 
	 <!-- 이렇게 바로 커맨드 객체에 접근할 수 있다. -->
	ID : ${member.memId}<br />
	PW : ${member.memPw}<br />
	Mail : ${member.memMail} <br />
	Phone : ${member.memPhone.memPhone1} - ${member.memPhone.memPhone2} - ${member.memPhone.memPhone3}<br />
	
	<a href="/lec18/resources/html/memJoin.html"> Go MemberJoin </a>
</body>
</html>