package com.qianqi.edu.service;

import java.util.List;

import com.qianqi.edu.pojo.Knowledge;

public interface KnowledgeService {
	void addKnowledge(Knowledge knowledge);
	void deleteKnowledge(Long id);
	void updateKnowledge(Knowledge knowledge);
	Knowledge findKnowledge(Long id);
	Knowledge findKnowledgeByKnowledge(String knowledge);
	List<Knowledge> findKnowledgeBySubjectId(int subjectId);
	List<Knowledge> findKnowledgeAll();
}
