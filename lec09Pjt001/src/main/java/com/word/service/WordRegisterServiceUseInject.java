package com.word.service;

import javax.inject.Inject;
import javax.inject.Named;

import com.word.WordSet;
import com.word.dao.WordDao;

public class WordRegisterServiceUseInject {

	@Inject
	// 객체가 중복될 경우, Autowired와 달리 xml파일에 따로 써주지 않아도 named하나로 자동 연결된다.
	@Named(value = "wordDao1")
	private WordDao wordDao;

	public WordRegisterServiceUseInject() {

	}

//	@Inject
	public WordRegisterServiceUseInject(WordDao wordDao) {
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

//	@Inject
	public void setWordDao(WordDao wordDao) {
		this.wordDao = wordDao;
	}

}
