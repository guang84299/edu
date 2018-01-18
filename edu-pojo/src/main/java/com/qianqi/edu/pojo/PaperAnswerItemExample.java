package com.qianqi.edu.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PaperAnswerItemExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PaperAnswerItemExample() {
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

        public Criteria andPaperItemIdIsNull() {
            addCriterion("paper_item_id is null");
            return (Criteria) this;
        }

        public Criteria andPaperItemIdIsNotNull() {
            addCriterion("paper_item_id is not null");
            return (Criteria) this;
        }

        public Criteria andPaperItemIdEqualTo(Long value) {
            addCriterion("paper_item_id =", value, "paperItemId");
            return (Criteria) this;
        }

        public Criteria andPaperItemIdNotEqualTo(Long value) {
            addCriterion("paper_item_id <>", value, "paperItemId");
            return (Criteria) this;
        }

        public Criteria andPaperItemIdGreaterThan(Long value) {
            addCriterion("paper_item_id >", value, "paperItemId");
            return (Criteria) this;
        }

        public Criteria andPaperItemIdGreaterThanOrEqualTo(Long value) {
            addCriterion("paper_item_id >=", value, "paperItemId");
            return (Criteria) this;
        }

        public Criteria andPaperItemIdLessThan(Long value) {
            addCriterion("paper_item_id <", value, "paperItemId");
            return (Criteria) this;
        }

        public Criteria andPaperItemIdLessThanOrEqualTo(Long value) {
            addCriterion("paper_item_id <=", value, "paperItemId");
            return (Criteria) this;
        }

        public Criteria andPaperItemIdIn(List<Long> values) {
            addCriterion("paper_item_id in", values, "paperItemId");
            return (Criteria) this;
        }

        public Criteria andPaperItemIdNotIn(List<Long> values) {
            addCriterion("paper_item_id not in", values, "paperItemId");
            return (Criteria) this;
        }

        public Criteria andPaperItemIdBetween(Long value1, Long value2) {
            addCriterion("paper_item_id between", value1, value2, "paperItemId");
            return (Criteria) this;
        }

        public Criteria andPaperItemIdNotBetween(Long value1, Long value2) {
            addCriterion("paper_item_id not between", value1, value2, "paperItemId");
            return (Criteria) this;
        }

        public Criteria andPaperAnswerIdIsNull() {
            addCriterion("paper_answer_id is null");
            return (Criteria) this;
        }

        public Criteria andPaperAnswerIdIsNotNull() {
            addCriterion("paper_answer_id is not null");
            return (Criteria) this;
        }

        public Criteria andPaperAnswerIdEqualTo(Long value) {
            addCriterion("paper_answer_id =", value, "paperAnswerId");
            return (Criteria) this;
        }

        public Criteria andPaperAnswerIdNotEqualTo(Long value) {
            addCriterion("paper_answer_id <>", value, "paperAnswerId");
            return (Criteria) this;
        }

        public Criteria andPaperAnswerIdGreaterThan(Long value) {
            addCriterion("paper_answer_id >", value, "paperAnswerId");
            return (Criteria) this;
        }

        public Criteria andPaperAnswerIdGreaterThanOrEqualTo(Long value) {
            addCriterion("paper_answer_id >=", value, "paperAnswerId");
            return (Criteria) this;
        }

        public Criteria andPaperAnswerIdLessThan(Long value) {
            addCriterion("paper_answer_id <", value, "paperAnswerId");
            return (Criteria) this;
        }

        public Criteria andPaperAnswerIdLessThanOrEqualTo(Long value) {
            addCriterion("paper_answer_id <=", value, "paperAnswerId");
            return (Criteria) this;
        }

        public Criteria andPaperAnswerIdIn(List<Long> values) {
            addCriterion("paper_answer_id in", values, "paperAnswerId");
            return (Criteria) this;
        }

        public Criteria andPaperAnswerIdNotIn(List<Long> values) {
            addCriterion("paper_answer_id not in", values, "paperAnswerId");
            return (Criteria) this;
        }

        public Criteria andPaperAnswerIdBetween(Long value1, Long value2) {
            addCriterion("paper_answer_id between", value1, value2, "paperAnswerId");
            return (Criteria) this;
        }

        public Criteria andPaperAnswerIdNotBetween(Long value1, Long value2) {
            addCriterion("paper_answer_id not between", value1, value2, "paperAnswerId");
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