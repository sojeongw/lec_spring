package com.word.service;

import javax.annotation.Resource;

import com.word.WordSet;
import com.word.dao.WordDao;

public class WordRegisterServiceUseResource {

	// 객체의 타입을 보지 않고 id 이름을 보고 xml에서 찾는다.
	// resource는 생성자엔 사용하지 못한다 >> 기본 생성자 명시 필요
	@Resource
//	@Qualifier("usedDao") -> Autowired랑 같이 써야 함.
	private WordDao wordDao;

	public WordRegisterServiceUseResource() {

	}

	// @Resource
	public WordRegisterServiceUseResource(WordDao wordDao) {
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

	// @Resource
	public void setWordDao(WordDao wordDao) {
		this.wordDao = wordDao;
	}

}