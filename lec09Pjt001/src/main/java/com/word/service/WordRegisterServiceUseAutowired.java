package com.word.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.word.WordSet;
import com.word.dao.WordDao;

public class WordRegisterServiceUseAutowired {

	// db를 사용해서 처리는 부분에 달아준다.
	// Autowired는 생성자 뿐만 아니라 메서드나 변수에도 적용 가능하다.
	// 단, 이럴 때는 디폴드 생성자가 꼭 존재해야 한다. 이 객체가 생성되어야 속성과 메소드를 불러올 수 있기 때문.
	// 생성자에 Autowired가 되어있으면 상관없는데, 그렇지 않으면 꼭 디폴드 생성자가 있어야 한다.
	// 이때 생성자엔 Autowired 필요없음.
	@Autowired
	// @Qualifier("usedDao")
	private WordDao wordDao;

	public WordRegisterServiceUseAutowired() {
		// TODO Auto-generated constructor stub
	}

	// @Resource는 생성자에는 쓰지 못한다. >> 디폴트 생성자가 존재해야 한다.
//	@Autowired >> 생성자, 메소드, 멤버 변수 어디에나 사용 가능하고 결과는 같지만 대부분 멤버 변수에 쓴다.
	public WordRegisterServiceUseAutowired(WordDao wordDao) {
		this.wordDao = wordDao;
	}

	public void register(WordSet wordSet) {
		String wordKey = wordSet.getWordKey();
		if (verify(wordKey)) {
			wordDao.insert(wordSet);
		} else {
			System.out.println("The word has already registered.");
		}
	}

	public boolean verify(String wordKey) {
		WordSet wordSet = wordDao.select(wordKey);
		return wordSet == null ? true : false;
	}

//	@Autowired
	public void setWordDao(WordDao wordDao) {
		this.wordDao = wordDao;
	}

}
