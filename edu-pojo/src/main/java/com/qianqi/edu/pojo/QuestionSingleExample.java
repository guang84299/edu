package com.qianqi.edu.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuestionSingleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public QuestionSingleExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAnswerIsNull() {
            addCriterion("answer is null");
            return (Criteria) this;
        }

        public Criteria andAnswerIsNotNull() {
            addCriterion("answer is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerEqualTo(String value) {
            addCriterion("answer =", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotEqualTo(String value) {
            addCriterion("answer <>", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerGreaterThan(String value) {
            addCriterion("answer >", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerGreaterThanOrEqualTo(String value) {
            addCriterion("answer >=", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerLessThan(String value) {
            addCriterion("answer <", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerLessThanOrEqualTo(String value) {
            addCriterion("answer <=", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerLike(String value) {
            addCriterion("answer like", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotLike(String value) {
            addCriterion("answer not like", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerIn(List<String> values) {
            addCriterion("answer in", values, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotIn(List<String> values) {
            addCriterion("answer not in", values, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerBetween(String value1, String value2) {
            addCriterion("answer between", value1, value2, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotBetween(String value1, String value2) {
            addCriterion("answer not between", value1, value2, "answer");
            return (Criteria) this;
        }

        public Criteria andScoreIsNull() {
            addCriterion("score is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("score is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(Integer value) {
            addCriterion("score =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(Integer value) {
            addCriterion("score <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(Integer value) {
            addCriterion("score >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("score >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(Integer value) {
            addCriterion("score <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(Integer value) {
            addCriterion("score <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<Integer> values) {
            addCriterion("score in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<Integer> values) {
            addCriterion("score not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(Integer value1, Integer value2) {
            addCriterion("score between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("score not between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andTeacherIdIsNull() {
            addCriterion("teacher_id is null");
            return (Criteria) this;
        }

        public Criteria andTeacherIdIsNotNull() {
            addCriterion("teacher_id is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherIdEqualTo(Long value) {
            addCriterion("teacher_id =", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdNotEqualTo(Long value) {
            addCriterion("teacher_id <>", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdGreaterThan(Long value) {
            addCriterion("teacher_id >", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdGreaterThanOrEqualTo(Long value) {
            addCriterion("teacher_id >=", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdLessThan(Long value) {
            addCriterion("teacher_id <", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdLessThanOrEqualTo(Long value) {
            addCriterion("teacher_id <=", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdIn(List<Long> values) {
            addCriterion("teacher_id in", values, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdNotIn(List<Long> values) {
            addCriterion("teacher_id not in", values, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdBetween(Long value1, Long value2) {
            addCriterion("teacher_id between", value1, value2, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdNotBetween(Long value1, Long value2) {
            addCriterion("teacher_id not between", value1, value2, "teacherId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdIsNull() {
            addCriterion("subject_id is null");
            return (Criteria) this;
        }

        public Criteria andSubjectIdIsNotNull() {
            addCriterion("subject_id is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectIdEqualTo(Integer value) {
            addCriterion("subject_id =", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdNotEqualTo(Integer value) {
            addCriterion("subject_id <>", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdGreaterThan(Integer value) {
            addCriterion("subject_id >", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("subject_id >=", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdLessThan(Integer value) {
            addCriterion("subject_id <", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdLessThanOrEqualTo(Integer value) {
            addCriterion("subject_id <=", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdIn(List<Integer> values) {
            addCriterion("subject_id in", values, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdNotIn(List<Integer> values) {
            addCriterion("subject_id not in", values, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdBetween(Integer value1, Integer value2) {
            addCriterion("subject_id between", value1, value2, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdNotBetween(Integer value1, Integer value2) {
            addCriterion("subject_id not between", value1, value2, "subjectId");
            return (Criteria) this;
        }

        public Criteria andGradeIdIsNull() {
            addCriterion("grade_id is null");
            return (Criteria) this;
        }

        public Criteria andGradeIdIsNotNull() {
            addCriterion("grade_id is not null");
            return (Criteria) this;
        }

        public Criteria andGradeIdEqualTo(Integer value) {
            addCriterion("grade_id =", value, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdNotEqualTo(Integer value) {
            addCriterion("grade_id <>", value, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdGreaterThan(Integer value) {
            addCriterion("grade_id >", value, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("grade_id >=", value, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdLessThan(Integer value) {
            addCriterion("grade_id <", value, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdLessThanOrEqualTo(Integer value) {
            addCriterion("grade_id <=", value, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdIn(List<Integer> values) {
            addCriterion("grade_id in", values, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdNotIn(List<Integer> values) {
            addCriterion("grade_id not in", values, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdBetween(Integer value1, Integer value2) {
            addCriterion("grade_id between", value1, value2, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("grade_id not between", value1, value2, "gradeId");
            return (Criteria) this;
        }

        public Criteria andDifficultIsNull() {
            addCriterion("difficult is null");
            return (Criteria) this;
        }

        public Criteria andDifficultIsNotNull() {
            addCriterion("difficult is not null");
            return (Criteria) this;
        }

        public Criteria andDifficultEqualTo(Integer value) {
            addCriterion("difficult =", value, "difficult");
            return (Criteria) this;
        }

        public Criteria andDifficultNotEqualTo(Integer value) {
            addCriterion("difficult <>", value, "difficult");
            return (Criteria) this;
        }

        public Criteria andDifficultGreaterThan(Integer value) {
            addCriterion("difficult >", value, "difficult");
            return (Criteria) this;
        }

        public Criteria andDifficultGreaterThanOrEqualTo(Integer value) {
            addCriterion("difficult >=", value, "difficult");
            return (Criteria) this;
        }

        public Criteria andDifficultLessThan(Integer value) {
            addCriterion("difficult <", value, "difficult");
            return (Criteria) this;
        }

        public Criteria andDifficultLessThanOrEqualTo(Integer value) {
            addCriterion("difficult <=", value, "difficult");
            return (Criteria) this;
        }

        public Criteria andDifficultIn(List<Integer> values) {
            addCriterion("difficult in", values, "difficult");
            return (Criteria) this;
        }

        public Criteria andDifficultNotIn(List<Integer> values) {
            addCriterion("difficult not in", values, "difficult");
            return (Criteria) this;
        }

        public Criteria andDifficultBetween(Integer value1, Integer value2) {
            addCriterion("difficult between", value1, value2, "difficult");
            return (Criteria) this;
        }

        public Criteria andDifficultNotBetween(Integer value1, Integer value2) {
            addCriterion("difficult not between", value1, value2, "difficult");
            return (Criteria) this;
        }

        public Criteria andKnowledgePointIsNull() {
            addCriterion("knowledge_point is null");
            return (Criteria) this;
        }

        public Criteria andKnowledgePointIsNotNull() {
            addCriterion("knowledge_point is not null");
            return (Criteria) this;
        }

        public Criteria andKnowledgePointEqualTo(String value) {
            addCriterion("knowledge_point =", value, "knowledgePoint");
            return (Criteria) this;
        }

        public Criteria andKnowledgePointNotEqualTo(String value) {
            addCriterion("knowledge_point <>", value, "knowledgePoint");
            return (Criteria) this;
        }

        public Criteria andKnowledgePointGreaterThan(String value) {
            addCriterion("knowledge_point >", value, "knowledgePoint");
            return (Criteria) this;
        }

        public Criteria andKnowledgePointGreaterThanOrEqualTo(String value) {
            addCriterion("knowledge_point >=", value, "knowledgePoint");
            return (Criteria) this;
        }

        public Criteria andKnowledgePointLessThan(String value) {
            addCriterion("knowledge_point <", value, "knowledgePoint");
            return (Criteria) this;
        }

        public Criteria andKnowledgePointLessThanOrEqualTo(String value) {
            addCriterion("knowledge_point <=", value, "knowledgePoint");
            return (Criteria) this;
        }

        public Criteria andKnowledgePointLike(String value) {
            addCriterion("knowledge_point like", value, "knowledgePoint");
            return (Criteria) this;
        }

        public Criteria andKnowledgePointNotLike(String value) {
            addCriterion("knowledge_point not like", value, "knowledgePoint");
            return (Criteria) this;
        }

        public Criteria andKnowledgePointIn(List<String> values) {
            addCriterion("knowledge_point in", values, "knowledgePoint");
            return (Criteria) this;
        }

        public Criteria andKnowledgePointNotIn(List<String> values) {
            addCriterion("knowledge_point not in", values, "knowledgePoint");
            return (Criteria) this;
        }

        public Criteria andKnowledgePointBetween(String value1, String value2) {
            addCriterion("knowledge_point between", value1, value2, "knowledgePoint");
            return (Criteria) this;
        }

        public Criteria andKnowledgePointNotBetween(String value1, String value2) {
            addCriterion("knowledge_point not between", value1, value2, "knowledgePoint");
            return (Criteria) this;
        }

        public Criteria andNormalTimeIsNull() {
            addCriterion("normal_time is null");
            return (Criteria) this;
        }

        public Criteria andNormalTimeIsNotNull() {
            addCriterion("normal_time is not null");
            return (Criteria) this;
        }

        public Criteria andNormalTimeEqualTo(Integer value) {
            addCriterion("normal_time =", value, "normalTime");
            return (Criteria) this;
        }

        public Criteria andNormalTimeNotEqualTo(Integer value) {
            addCriterion("normal_time <>", value, "normalTime");
            return (Criteria) this;
        }

        public Criteria andNormalTimeGreaterThan(Integer value) {
            addCriterion("normal_time >", value, "normalTime");
            return (Criteria) this;
        }

        public Criteria andNormalTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("normal_time >=", value, "normalTime");
            return (Criteria) this;
        }

        public Criteria andNormalTimeLessThan(Integer value) {
            addCriterion("normal_time <", value, "normalTime");
            return (Criteria) this;
        }

        public Criteria andNormalTimeLessThanOrEqualTo(Integer value) {
            addCriterion("normal_time <=", value, "normalTime");
            return (Criteria) this;
        }

        public Criteria andNormalTimeIn(List<Integer> values) {
            addCriterion("normal_time in", values, "normalTime");
            return (Criteria) this;
        }

        public Criteria andNormalTimeNotIn(List<Integer> values) {
            addCriterion("normal_time not in", values, "normalTime");
            return (Criteria) this;
        }

        public Criteria andNormalTimeBetween(Integer value1, Integer value2) {
            addCriterion("normal_time between", value1, value2, "normalTime");
            return (Criteria) this;
        }

        public Criteria andNormalTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("normal_time not between", value1, value2, "normalTime");
            return (Criteria) this;
        }

        public Criteria andChoiceAIsNull() {
            addCriterion("choice_a is null");
            return (Criteria) this;
        }

        public Criteria andChoiceAIsNotNull() {
            addCriterion("choice_a is not null");
            return (Criteria) this;
        }

        public Criteria andChoiceAEqualTo(String value) {
            addCriterion("choice_a =", value, "choiceA");
            return (Criteria) this;
        }

        public Criteria andChoiceANotEqualTo(String value) {
            addCriterion("choice_a <>", value, "choiceA");
            return (Criteria) this;
        }

        public Criteria andChoiceAGreaterThan(String value) {
            addCriterion("choice_a >", value, "choiceA");
            return (Criteria) this;
        }

        public Criteria andChoiceAGreaterThanOrEqualTo(String value) {
            addCriterion("choice_a >=", value, "choiceA");
            return (Criteria) this;
        }

        public Criteria andChoiceALessThan(String value) {
            addCriterion("choice_a <", value, "choiceA");
            return (Criteria) this;
        }

        public Criteria andChoiceALessThanOrEqualTo(String value) {
            addCriterion("choice_a <=", value, "choiceA");
            return (Criteria) this;
        }

        public Criteria andChoiceALike(String value) {
            addCriterion("choice_a like", value, "choiceA");
            return (Criteria) this;
        }

        public Criteria andChoiceANotLike(String value) {
            addCriterion("choice_a not like", value, "choiceA");
            return (Criteria) this;
        }

        public Criteria andChoiceAIn(List<String> values) {
            addCriterion("choice_a in", values, "choiceA");
            return (Criteria) this;
        }

        public Criteria andChoiceANotIn(List<String> values) {
            addCriterion("choice_a not in", values, "choiceA");
            return (Criteria) this;
        }

        public Criteria andChoiceABetween(String value1, String value2) {
            addCriterion("choice_a between", value1, value2, "choiceA");
            return (Criteria) this;
        }

        public Criteria andChoiceANotBetween(String value1, String value2) {
            addCriterion("choice_a not between", value1, value2, "choiceA");
            return (Criteria) this;
        }

        public Criteria andChoiceBIsNull() {
            addCriterion("choice_b is null");
            return (Criteria) this;
        }

        public Criteria andChoiceBIsNotNull() {
            addCriterion("choice_b is not null");
            return (Criteria) this;
        }

        public Criteria andChoiceBEqualTo(String value) {
            addCriterion("choice_b =", value, "choiceB");
            return (Criteria) this;
        }

        public Criteria andChoiceBNotEqualTo(String value) {
            addCriterion("choice_b <>", value, "choiceB");
            return (Criteria) this;
        }

        public Criteria andChoiceBGreaterThan(String value) {
            addCriterion("choice_b >", value, "choiceB");
            return (Criteria) this;
        }

        public Criteria andChoiceBGreaterThanOrEqualTo(String value) {
            addCriterion("choice_b >=", value, "choiceB");
            return (Criteria) this;
        }

        public Criteria andChoiceBLessThan(String value) {
            addCriterion("choice_b <", value, "choiceB");
            return (Criteria) this;
        }

        public Criteria andChoiceBLessThanOrEqualTo(String value) {
            addCriterion("choice_b <=", value, "choiceB");
            return (Criteria) this;
        }

        public Criteria andChoiceBLike(String value) {
            addCriterion("choice_b like", value, "choiceB");
            return (Criteria) this;
        }

        public Criteria andChoiceBNotLike(String value) {
            addCriterion("choice_b not like", value, "choiceB");
            return (Criteria) this;
        }

        public Criteria andChoiceBIn(List<String> values) {
            addCriterion("choice_b in", values, "choiceB");
            return (Criteria) this;
        }

        public Criteria andChoiceBNotIn(List<String> values) {
            addCriterion("choice_b not in", values, "choiceB");
            return (Criteria) this;
        }

        public Criteria andChoiceBBetween(String value1, String value2) {
            addCriterion("choice_b between", value1, value2, "choiceB");
            return (Criteria) this;
        }

        public Criteria andChoiceBNotBetween(String value1, String value2) {
            addCriterion("choice_b not between", value1, value2, "choiceB");
            return (Criteria) this;
        }

        public Criteria andChoiceCIsNull() {
            addCriterion("choice_c is null");
            return (Criteria) this;
        }

        public Criteria andChoiceCIsNotNull() {
            addCriterion("choice_c is not null");
            return (Criteria) this;
        }

        public Criteria andChoiceCEqualTo(String value) {
            addCriterion("choice_c =", value, "choiceC");
            return (Criteria) this;
        }

        public Criteria andChoiceCNotEqualTo(String value) {
            addCriterion("choice_c <>", value, "choiceC");
            return (Criteria) this;
        }

        public Criteria andChoiceCGreaterThan(String value) {
            addCriterion("choice_c >", value, "choiceC");
            return (Criteria) this;
        }

        public Criteria andChoiceCGreaterThanOrEqualTo(String value) {
            addCriterion("choice_c >=", value, "choiceC");
            return (Criteria) this;
        }

        public Criteria andChoiceCLessThan(String value) {
            addCriterion("choice_c <", value, "choiceC");
            return (Criteria) this;
        }

        public Criteria andChoiceCLessThanOrEqualTo(String value) {
            addCriterion("choice_c <=", value, "choiceC");
            return (Criteria) this;
        }

        public Criteria andChoiceCLike(String value) {
            addCriterion("choice_c like", value, "choiceC");
            return (Criteria) this;
        }

        public Criteria andChoiceCNotLike(String value) {
            addCriterion("choice_c not like", value, "choiceC");
            return (Criteria) this;
        }

        public Criteria andChoiceCIn(List<String> values) {
            addCriterion("choice_c in", values, "choiceC");
            return (Criteria) this;
        }

        public Criteria andChoiceCNotIn(List<String> values) {
            addCriterion("choice_c not in", values, "choiceC");
            return (Criteria) this;
        }

        public Criteria andChoiceCBetween(String value1, String value2) {
            addCriterion("choice_c between", value1, value2, "choiceC");
            return (Criteria) this;
        }

        public Criteria andChoiceCNotBetween(String value1, String value2) {
            addCriterion("choice_c not between", value1, value2, "choiceC");
            return (Criteria) this;
        }

        public Criteria andChoiceDIsNull() {
            addCriterion("choice_d is null");
            return (Criteria) this;
        }

        public Criteria andChoiceDIsNotNull() {
            addCriterion("choice_d is not null");
            return (Criteria) this;
        }

        public Criteria andChoiceDEqualTo(String value) {
            addCriterion("choice_d =", value, "choiceD");
            return (Criteria) this;
        }

        public Criteria andChoiceDNotEqualTo(String value) {
            addCriterion("choice_d <>", value, "choiceD");
            return (Criteria) this;
        }

        public Criteria andChoiceDGreaterThan(String value) {
            addCriterion("choice_d >", value, "choiceD");
            return (Criteria) this;
        }

        public Criteria andChoiceDGreaterThanOrEqualTo(String value) {
            addCriterion("choice_d >=", value, "choiceD");
            return (Criteria) this;
        }

        public Criteria andChoiceDLessThan(String value) {
            addCriterion("choice_d <", value, "choiceD");
            return (Criteria) this;
        }

        public Criteria andChoiceDLessThanOrEqualTo(String value) {
            addCriterion("choice_d <=", value, "choiceD");
            return (Criteria) this;
        }

        public Criteria andChoiceDLike(String value) {
            addCriterion("choice_d like", value, "choiceD");
            return (Criteria) this;
        }

        public Criteria andChoiceDNotLike(String value) {
            addCriterion("choice_d not like", value, "choiceD");
            return (Criteria) this;
        }

        public Criteria andChoiceDIn(List<String> values) {
            addCriterion("choice_d in", values, "choiceD");
            return (Criteria) this;
        }

        public Criteria andChoiceDNotIn(List<String> values) {
            addCriterion("choice_d not in", values, "choiceD");
            return (Criteria) this;
        }

        public Criteria andChoiceDBetween(String value1, String value2) {
            addCriterion("choice_d between", value1, value2, "choiceD");
            return (Criteria) this;
        }

        public Criteria andChoiceDNotBetween(String value1, String value2) {
            addCriterion("choice_d not between", value1, value2, "choiceD");
            return (Criteria) this;
        }

        public Criteria andChoiceEIsNull() {
            addCriterion("choice_e is null");
            return (Criteria) this;
        }

        public Criteria andChoiceEIsNotNull() {
            addCriterion("choice_e is not null");
            return (Criteria) this;
        }

        public Criteria andChoiceEEqualTo(String value) {
            addCriterion("choice_e =", value, "choiceE");
            return (Criteria) this;
        }

        public Criteria andChoiceENotEqualTo(String value) {
            addCriterion("choice_e <>", value, "choiceE");
            return (Criteria) this;
        }

        public Criteria andChoiceEGreaterThan(String value) {
            addCriterion("choice_e >", value, "choiceE");
            return (Criteria) this;
        }

        public Criteria andChoiceEGreaterThanOrEqualTo(String value) {
            addCriterion("choice_e >=", value, "choiceE");
            return (Criteria) this;
        }

        public Criteria andChoiceELessThan(String value) {
            addCriterion("choice_e <", value, "choiceE");
            return (Criteria) this;
        }

        public Criteria andChoiceELessThanOrEqualTo(String value) {
            addCriterion("choice_e <=", value, "choiceE");
            return (Criteria) this;
        }

        public Criteria andChoiceELike(String value) {
            addCriterion("choice_e like", value, "choiceE");
            return (Criteria) this;
        }

        public Criteria andChoiceENotLike(String value) {
            addCriterion("choice_e not like", value, "choiceE");
            return (Criteria) this;
        }

        public Criteria andChoiceEIn(List<String> values) {
            addCriterion("choice_e in", values, "choiceE");
            return (Criteria) this;
        }

        public Criteria andChoiceENotIn(List<String> values) {
            addCriterion("choice_e not in", values, "choiceE");
            return (Criteria) this;
        }

        public Criteria andChoiceEBetween(String value1, String value2) {
            addCriterion("choice_e between", value1, value2, "choiceE");
            return (Criteria) this;
        }

        public Criteria andChoiceENotBetween(String value1, String value2) {
            addCriterion("choice_e not between", value1, value2, "choiceE");
            return (Criteria) this;
        }

        public Criteria andChoiceFIsNull() {
            addCriterion("choice_f is null");
            return (Criteria) this;
        }

        public Criteria andChoiceFIsNotNull() {
            addCriterion("choice_f is not null");
            return (Criteria) this;
        }

        public Criteria andChoiceFEqualTo(String value) {
            addCriterion("choice_f =", value, "choiceF");
            return (Criteria) this;
        }

        public Criteria andChoiceFNotEqualTo(String value) {
            addCriterion("choice_f <>", value, "choiceF");
            return (Criteria) this;
        }

        public Criteria andChoiceFGreaterThan(String value) {
            addCriterion("choice_f >", value, "choiceF");
            return (Criteria) this;
        }

        public Criteria andChoiceFGreaterThanOrEqualTo(String value) {
            addCriterion("choice_f >=", value, "choiceF");
            return (Criteria) this;
        }

        public Criteria andChoiceFLessThan(String value) {
            addCriterion("choice_f <", value, "choiceF");
            return (Criteria) this;
        }

        public Criteria andChoiceFLessThanOrEqualTo(String value) {
            addCriterion("choice_f <=", value, "choiceF");
            return (Criteria) this;
        }

        public Criteria andChoiceFLike(String value) {
            addCriterion("choice_f like", value, "choiceF");
            return (Criteria) this;
        }

        public Criteria andChoiceFNotLike(String value) {
            addCriterion("choice_f not like", value, "choiceF");
            return (Criteria) this;
        }

        public Criteria andChoiceFIn(List<String> values) {
            addCriterion("choice_f in", values, "choiceF");
            return (Criteria) this;
        }

        public Criteria andChoiceFNotIn(List<String> values) {
            addCriterion("choice_f not in", values, "choiceF");
            return (Criteria) this;
        }

        public Criteria andChoiceFBetween(String value1, String value2) {
            addCriterion("choice_f between", value1, value2, "choiceF");
            return (Criteria) this;
        }

        public Criteria andChoiceFNotBetween(String value1, String value2) {
            addCriterion("choice_f not between", value1, value2, "choiceF");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNull() {
            addCriterion("created is null");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNotNull() {
            addCriterion("created is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedEqualTo(Date value) {
            addCriterion("created =", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotEqualTo(Date value) {
            addCriterion("created <>", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThan(Date value) {
            addCriterion("created >", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThanOrEqualTo(Date value) {
            addCriterion("created >=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThan(Date value) {
            addCriterion("created <", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThanOrEqualTo(Date value) {
            addCriterion("created <=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedIn(List<Date> values) {
            addCriterion("created in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotIn(List<Date> values) {
            addCriterion("created not in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedBetween(Date value1, Date value2) {
            addCriterion("created between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotBetween(Date value1, Date value2) {
            addCriterion("created not between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andUpdatedIsNull() {
            addCriterion("updated is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedIsNotNull() {
            addCriterion("updated is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedEqualTo(Date value) {
            addCriterion("updated =", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotEqualTo(Date value) {
            addCriterion("updated <>", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedGreaterThan(Date value) {
            addCriterion("updated >", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedGreaterThanOrEqualTo(Date value) {
            addCriterion("updated >=", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedLessThan(Date value) {
            addCriterion("updated <", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedLessThanOrEqualTo(Date value) {
            addCriterion("updated <=", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedIn(List<Date> values) {
            addCriterion("updated in", values, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotIn(List<Date> values) {
            addCriterion("updated not in", values, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedBetween(Date value1, Date value2) {
            addCriterion("updated between", value1, value2, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotBetween(Date value1, Date value2) {
            addCriterion("updated not between", value1, value2, "updated");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}