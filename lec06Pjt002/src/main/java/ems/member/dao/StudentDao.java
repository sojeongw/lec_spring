package ems.member.dao;

import java.util.HashMap;
import java.util.Map;

import ems.member.Student;

public class StudentDao {

	// 같은 dao를 주입했기 때문에 수정, 삭제, 검색 등의 기능이 다 같은 DB를 사용할 수 있게 된다.
	private Map<String, Student> studentDB = new HashMap<String, Student>();

	public void insert(Student student) {
		studentDB.put(student.getsNum(), student);
	}

	public Student select(String sNum) {
		return studentDB.get(sNum);
	}

	public void update(Student student) {
		studentDB.put(student.getsNum(), student);
	}

	public void delete(String sNum) {
		studentDB.remove(sNum);
	}

	public Map<String, Student> getStudentDB() {
		return studentDB;
	}

}
