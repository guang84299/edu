package com.qianqi.edu.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PaperAnswerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PaperAnswerExample() {
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

        public Criteria andStudentIdIsNull() {
            addCriterion("student_id is null");
            return (Criteria) this;
        }

        public Criteria andStudentIdIsNotNull() {
            addCriterion("student_id is not null");
            return (Criteria) this;
        }

        public Criteria andStudentIdEqualTo(Long value) {
            addCriterion("student_id =", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotEqualTo(Long value) {
            addCriterion("student_id <>", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThan(Long value) {
            addCriterion("student_id >", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThanOrEqualTo(Long value) {
            addCriterion("student_id >=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThan(Long value) {
            addCriterion("student_id <", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThanOrEqualTo(Long value) {
            addCriterion("student_id <=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdIn(List<Long> values) {
            addCriterion("student_id in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotIn(List<Long> values) {
            addCriterion("student_id not in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdBetween(Long value1, Long value2) {
            addCriterion("student_id between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotBetween(Long value1, Long value2) {
            addCriterion("student_id not between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andPaperIdIsNull() {
            addCriterion("paper_id is null");
            return (Criteria) this;
        }

        public Criteria andPaperIdIsNotNull() {
            addCriterion("paper_id is not null");
            return (Criteria) this;
        }

        public Criteria andPaperIdEqualTo(Long value) {
            addCriterion("paper_id =", value, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdNotEqualTo(Long value) {
            addCriterion("paper_id <>", value, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdGreaterThan(Long value) {
            addCriterion("paper_id >", value, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdGreaterThanOrEqualTo(Long value) {
            addCriterion("paper_id >=", value, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdLessThan(Long value) {
            addCriterion("paper_id <", value, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdLessThanOrEqualTo(Long value) {
            addCriterion("paper_id <=", value, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdIn(List<Long> values) {
            addCriterion("paper_id in", values, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdNotIn(List<Long> values) {
            addCriterion("paper_id not in", values, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdBetween(Long value1, Long value2) {
            addCriterion("paper_id between", value1, value2, "paperId");
            return (Criteria) this;
        }

        public Criteria andPaperIdNotBetween(Long value1, Long value2) {
            addCriterion("paper_id not between", value1, value2, "paperId");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andCheckStateIsNull() {
            addCriterion("check_state is null");
            return (Criteria) this;
        }

        public Criteria andCheckStateIsNotNull() {
            addCriterion("check_state is not null");
            return (Criteria) this;
        }

        public Criteria andCheckStateEqualTo(Integer value) {
            addCriterion("check_state =", value, "checkState");
            return (Criteria) this;
        }

        public Criteria andCheckStateNotEqualTo(Integer value) {
            addCriterion("check_state <>", value, "checkState");
            return (Criteria) this;
        }

        public Criteria andCheckStateGreaterThan(Integer value) {
            addCriterion("check_state >", value, "checkState");
            return (Criteria) this;
        }

        public Criteria andCheckStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("check_state >=", value, "checkState");
            return (Criteria) this;
        }

        public Criteria andCheckStateLessThan(Integer value) {
            addCriterion("check_state <", value, "checkState");
            return (Criteria) this;
        }

        public Criteria andCheckStateLessThanOrEqualTo(Integer value) {
            addCriterion("check_state <=", value, "checkState");
            return (Criteria) this;
        }

        public Criteria andCheckStateIn(List<Integer> values) {
            addCriterion("check_state in", values, "checkState");
            return (Criteria) this;
        }

        public Criteria andCheckStateNotIn(List<Integer> values) {
            addCriterion("check_state not in", values, "checkState");
            return (Criteria) this;
        }

        public Criteria andCheckStateBetween(Integer value1, Integer value2) {
            addCriterion("check_state between", value1, value2, "checkState");
            return (Criteria) this;
        }

        public Criteria andCheckStateNotBetween(Integer value1, Integer value2) {
            addCriterion("check_state not between", value1, value2, "checkState");
            return (Criteria) this;
        }

        public Criteria andSEvaluateIsNull() {
            addCriterion("s_evaluate is null");
            return (Criteria) this;
        }

        public Criteria andSEvaluateIsNotNull() {
            addCriterion("s_evaluate is not null");
            return (Criteria) this;
        }

        public Criteria andSEvaluateEqualTo(Integer value) {
            addCriterion("s_evaluate =", value, "sEvaluate");
            return (Criteria) this;
        }

        public Criteria andSEvaluateNotEqualTo(Integer value) {
            addCriterion("s_evaluate <>", value, "sEvaluate");
            return (Criteria) this;
        }

        public Criteria andSEvaluateGreaterThan(Integer value) {
            addCriterion("s_evaluate >", value, "sEvaluate");
            return (Criteria) this;
        }

        public Criteria andSEvaluateGreaterThanOrEqualTo(Integer value) {
            addCriterion("s_evaluate >=", value, "sEvaluate");
            return (Criteria) this;
        }

        public Criteria andSEvaluateLessThan(Integer value) {
            addCriterion("s_evaluate <", value, "sEvaluate");
            return (Criteria) this;
        }

        public Criteria andSEvaluateLessThanOrEqualTo(Integer value) {
            addCriterion("s_evaluate <=", value, "sEvaluate");
            return (Criteria) this;
        }

        public Criteria andSEvaluateIn(List<Integer> values) {
            addCriterion("s_evaluate in", values, "sEvaluate");
            return (Criteria) this;
        }

        public Criteria andSEvaluateNotIn(List<Integer> values) {
            addCriterion("s_evaluate not in", values, "sEvaluate");
            return (Criteria) this;
        }

        public Criteria andSEvaluateBetween(Integer value1, Integer value2) {
            addCriterion("s_evaluate between", value1, value2, "sEvaluate");
            return (Criteria) this;
        }

        public Criteria andSEvaluateNotBetween(Integer value1, Integer value2) {
            addCriterion("s_evaluate not between", value1, value2, "sEvaluate");
            return (Criteria) this;
        }

        public Criteria andSCommentIsNull() {
            addCriterion("s_comment is null");
            return (Criteria) this;
        }

        public Criteria andSCommentIsNotNull() {
            addCriterion("s_comment is not null");
            return (Criteria) this;
        }

        public Criteria andSCommentEqualTo(String value) {
            addCriterion("s_comment =", value, "sComment");
            return (Criteria) this;
        }

        public Criteria andSCommentNotEqualTo(String value) {
            addCriterion("s_comment <>", value, "sComment");
            return (Criteria) this;
        }

        public Criteria andSCommentGreaterThan(String value) {
            addCriterion("s_comment >", value, "sComment");
            return (Criteria) this;
        }

        public Criteria andSCommentGreaterThanOrEqualTo(String value) {
            addCriterion("s_comment >=", value, "sComment");
            return (Criteria) this;
        }

        public Criteria andSCommentLessThan(String value) {
            addCriterion("s_comment <", value, "sComment");
            return (Criteria) this;
        }

        public Criteria andSCommentLessThanOrEqualTo(String value) {
            addCriterion("s_comment <=", value, "sComment");
            return (Criteria) this;
        }

        public Criteria andSCommentLike(String value) {
            addCriterion("s_comment like", value, "sComment");
            return (Criteria) this;
        }

        public Criteria andSCommentNotLike(String value) {
            addCriterion("s_comment not like", value, "sComment");
            return (Criteria) this;
        }

        public Criteria andSCommentIn(List<String> values) {
            addCriterion("s_comment in", values, "sComment");
            return (Criteria) this;
        }

        public Criteria andSCommentNotIn(List<String> values) {
            addCriterion("s_comment not in", values, "sComment");
            return (Criteria) this;
        }

        public Criteria andSCommentBetween(String value1, String value2) {
            addCriterion("s_comment between", value1, value2, "sComment");
            return (Criteria) this;
        }

        public Criteria andSCommentNotBetween(String value1, String value2) {
            addCriterion("s_comment not between", value1, value2, "sComment");
            return (Criteria) this;
        }

        public Criteria andTCommentIsNull() {
            addCriterion("t_comment is null");
            return (Criteria) this;
        }

        public Criteria andTCommentIsNotNull() {
            addCriterion("t_comment is not null");
            return (Criteria) this;
        }

        public Criteria andTCommentEqualTo(String value) {
            addCriterion("t_comment =", value, "tComment");
            return (Criteria) this;
        }

        public Criteria andTCommentNotEqualTo(String value) {
            addCriterion("t_comment <>", value, "tComment");
            return (Criteria) this;
        }

        public Criteria andTCommentGreaterThan(String value) {
            addCriterion("t_comment >", value, "tComment");
            return (Criteria) this;
        }

        public Criteria andTCommentGreaterThanOrEqualTo(String value) {
            addCriterion("t_comment >=", value, "tComment");
            return (Criteria) this;
        }

        public Criteria andTCommentLessThan(String value) {
            addCriterion("t_comment <", value, "tComment");
            return (Criteria) this;
        }

        public Criteria andTCommentLessThanOrEqualTo(String value) {
            addCriterion("t_comment <=", value, "tComment");
            return (Criteria) this;
        }

        public Criteria andTCommentLike(String value) {
            addCriterion("t_comment like", value, "tComment");
            return (Criteria) this;
        }

        public Criteria andTCommentNotLike(String value) {
            addCriterion("t_comment not like", value, "tComment");
            return (Criteria) this;
        }

        public Criteria andTCommentIn(List<String> values) {
            addCriterion("t_comment in", values, "tComment");
            return (Criteria) this;
        }

        public Criteria andTCommentNotIn(List<String> values) {
            addCriterion("t_comment not in", values, "tComment");
            return (Criteria) this;
        }

        public Criteria andTCommentBetween(String value1, String value2) {
            addCriterion("t_comment between", value1, value2, "tComment");
            return (Criteria) this;
        }

        public Criteria andTCommentNotBetween(String value1, String value2) {
            addCriterion("t_comment not between", value1, value2, "tComment");
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

        public Criteria andCheckTimeIsNull() {
            addCriterion("check_time is null");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIsNotNull() {
            addCriterion("check_time is not null");
            return (Criteria) this;
        }

        public Criteria andCheckTimeEqualTo(Date value) {
            addCriterion("check_time =", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotEqualTo(Date value) {
            addCriterion("check_time <>", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeGreaterThan(Date value) {
            addCriterion("check_time >", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("check_time >=", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLessThan(Date value) {
            addCriterion("check_time <", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLessThanOrEqualTo(Date value) {
            addCriterion("check_time <=", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIn(List<Date> values) {
            addCriterion("check_time in", values, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotIn(List<Date> values) {
            addCriterion("check_time not in", values, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeBetween(Date value1, Date value2) {
            addCriterion("check_time between", value1, value2, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotBetween(Date value1, Date value2) {
            addCriterion("check_time not between", value1, value2, "checkTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeIsNull() {
            addCriterion("submit_time is null");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeIsNotNull() {
            addCriterion("submit_time is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeEqualTo(Date value) {
            addCriterion("submit_time =", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotEqualTo(Date value) {
            addCriterion("submit_time <>", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeGreaterThan(Date value) {
            addCriterion("submit_time >", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("submit_time >=", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeLessThan(Date value) {
            addCriterion("submit_time <", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeLessThanOrEqualTo(Date value) {
            addCriterion("submit_time <=", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeIn(List<Date> values) {
            addCriterion("submit_time in", values, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotIn(List<Date> values) {
            addCriterion("submit_time not in", values, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeBetween(Date value1, Date value2) {
            addCriterion("submit_time between", value1, value2, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotBetween(Date value1, Date value2) {
            addCriterion("submit_time not between", value1, value2, "submitTime");
            return (Criteria) this;
        }

        public Criteria andZhiAnswerIsNull() {
            addCriterion("zhi_answer is null");
            return (Criteria) this;
        }

        public Criteria andZhiAnswerIsNotNull() {
            addCriterion("zhi_answer is not null");
            return (Criteria) this;
        }

        public Criteria andZhiAnswerEqualTo(String value) {
            addCriterion("zhi_answer =", value, "zhiAnswer");
            return (Criteria) this;
        }

        public Criteria andZhiAnswerNotEqualTo(String value) {
            addCriterion("zhi_answer <>", value, "zhiAnswer");
            return (Criteria) this;
        }

        public Criteria andZhiAnswerGreaterThan(String value) {
            addCriterion("zhi_answer >", value, "zhiAnswer");
            return (Criteria) this;
        }

        public Criteria andZhiAnswerGreaterThanOrEqualTo(String value) {
            addCriterion("zhi_answer >=", value, "zhiAnswer");
            return (Criteria) this;
        }

        public Criteria andZhiAnswerLessThan(String value) {
            addCriterion("zhi_answer <", value, "zhiAnswer");
            return (Criteria) this;
        }

        public Criteria andZhiAnswerLessThanOrEqualTo(String value) {
            addCriterion("zhi_answer <=", value, "zhiAnswer");
            return (Criteria) this;
        }

        public Criteria andZhiAnswerLike(String value) {
            addCriterion("zhi_answer like", value, "zhiAnswer");
            return (Criteria) this;
        }

        public Criteria andZhiAnswerNotLike(String value) {
            addCriterion("zhi_answer not like", value, "zhiAnswer");
            return (Criteria) this;
        }

        public Criteria andZhiAnswerIn(List<String> values) {
            addCriterion("zhi_answer in", values, "zhiAnswer");
            return (Criteria) this;
        }

        public Criteria andZhiAnswerNotIn(List<String> values) {
            addCriterion("zhi_answer not in", values, "zhiAnswer");
            return (Criteria) this;
        }

        public Criteria andZhiAnswerBetween(String value1, String value2) {
            addCriterion("zhi_answer between", value1, value2, "zhiAnswer");
            return (Criteria) this;
        }

        public Criteria andZhiAnswerNotBetween(String value1, String value2) {
            addCriterion("zhi_answer not between", value1, value2, "zhiAnswer");
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