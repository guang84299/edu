package com.qianqi.edu.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StaPaperExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StaPaperExample() {
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

        public Criteria andSchoolIdIsNull() {
            addCriterion("school_id is null");
            return (Criteria) this;
        }

        public Criteria andSchoolIdIsNotNull() {
            addCriterion("school_id is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolIdEqualTo(Integer value) {
            addCriterion("school_id =", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdNotEqualTo(Integer value) {
            addCriterion("school_id <>", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdGreaterThan(Integer value) {
            addCriterion("school_id >", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("school_id >=", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdLessThan(Integer value) {
            addCriterion("school_id <", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdLessThanOrEqualTo(Integer value) {
            addCriterion("school_id <=", value, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdIn(List<Integer> values) {
            addCriterion("school_id in", values, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdNotIn(List<Integer> values) {
            addCriterion("school_id not in", values, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdBetween(Integer value1, Integer value2) {
            addCriterion("school_id between", value1, value2, "schoolId");
            return (Criteria) this;
        }

        public Criteria andSchoolIdNotBetween(Integer value1, Integer value2) {
            addCriterion("school_id not between", value1, value2, "schoolId");
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

        public Criteria andTclassIdIsNull() {
            addCriterion("tclass_id is null");
            return (Criteria) this;
        }

        public Criteria andTclassIdIsNotNull() {
            addCriterion("tclass_id is not null");
            return (Criteria) this;
        }

        public Criteria andTclassIdEqualTo(Long value) {
            addCriterion("tclass_id =", value, "tclassId");
            return (Criteria) this;
        }

        public Criteria andTclassIdNotEqualTo(Long value) {
            addCriterion("tclass_id <>", value, "tclassId");
            return (Criteria) this;
        }

        public Criteria andTclassIdGreaterThan(Long value) {
            addCriterion("tclass_id >", value, "tclassId");
            return (Criteria) this;
        }

        public Criteria andTclassIdGreaterThanOrEqualTo(Long value) {
            addCriterion("tclass_id >=", value, "tclassId");
            return (Criteria) this;
        }

        public Criteria andTclassIdLessThan(Long value) {
            addCriterion("tclass_id <", value, "tclassId");
            return (Criteria) this;
        }

        public Criteria andTclassIdLessThanOrEqualTo(Long value) {
            addCriterion("tclass_id <=", value, "tclassId");
            return (Criteria) this;
        }

        public Criteria andTclassIdIn(List<Long> values) {
            addCriterion("tclass_id in", values, "tclassId");
            return (Criteria) this;
        }

        public Criteria andTclassIdNotIn(List<Long> values) {
            addCriterion("tclass_id not in", values, "tclassId");
            return (Criteria) this;
        }

        public Criteria andTclassIdBetween(Long value1, Long value2) {
            addCriterion("tclass_id between", value1, value2, "tclassId");
            return (Criteria) this;
        }

        public Criteria andTclassIdNotBetween(Long value1, Long value2) {
            addCriterion("tclass_id not between", value1, value2, "tclassId");
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

        public Criteria andPredictTimeIsNull() {
            addCriterion("predict_time is null");
            return (Criteria) this;
        }

        public Criteria andPredictTimeIsNotNull() {
            addCriterion("predict_time is not null");
            return (Criteria) this;
        }

        public Criteria andPredictTimeEqualTo(Long value) {
            addCriterion("predict_time =", value, "predictTime");
            return (Criteria) this;
        }

        public Criteria andPredictTimeNotEqualTo(Long value) {
            addCriterion("predict_time <>", value, "predictTime");
            return (Criteria) this;
        }

        public Criteria andPredictTimeGreaterThan(Long value) {
            addCriterion("predict_time >", value, "predictTime");
            return (Criteria) this;
        }

        public Criteria andPredictTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("predict_time >=", value, "predictTime");
            return (Criteria) this;
        }

        public Criteria andPredictTimeLessThan(Long value) {
            addCriterion("predict_time <", value, "predictTime");
            return (Criteria) this;
        }

        public Criteria andPredictTimeLessThanOrEqualTo(Long value) {
            addCriterion("predict_time <=", value, "predictTime");
            return (Criteria) this;
        }

        public Criteria andPredictTimeIn(List<Long> values) {
            addCriterion("predict_time in", values, "predictTime");
            return (Criteria) this;
        }

        public Criteria andPredictTimeNotIn(List<Long> values) {
            addCriterion("predict_time not in", values, "predictTime");
            return (Criteria) this;
        }

        public Criteria andPredictTimeBetween(Long value1, Long value2) {
            addCriterion("predict_time between", value1, value2, "predictTime");
            return (Criteria) this;
        }

        public Criteria andPredictTimeNotBetween(Long value1, Long value2) {
            addCriterion("predict_time not between", value1, value2, "predictTime");
            return (Criteria) this;
        }

        public Criteria andActualTimeIsNull() {
            addCriterion("actual_time is null");
            return (Criteria) this;
        }

        public Criteria andActualTimeIsNotNull() {
            addCriterion("actual_time is not null");
            return (Criteria) this;
        }

        public Criteria andActualTimeEqualTo(Long value) {
            addCriterion("actual_time =", value, "actualTime");
            return (Criteria) this;
        }

        public Criteria andActualTimeNotEqualTo(Long value) {
            addCriterion("actual_time <>", value, "actualTime");
            return (Criteria) this;
        }

        public Criteria andActualTimeGreaterThan(Long value) {
            addCriterion("actual_time >", value, "actualTime");
            return (Criteria) this;
        }

        public Criteria andActualTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("actual_time >=", value, "actualTime");
            return (Criteria) this;
        }

        public Criteria andActualTimeLessThan(Long value) {
            addCriterion("actual_time <", value, "actualTime");
            return (Criteria) this;
        }

        public Criteria andActualTimeLessThanOrEqualTo(Long value) {
            addCriterion("actual_time <=", value, "actualTime");
            return (Criteria) this;
        }

        public Criteria andActualTimeIn(List<Long> values) {
            addCriterion("actual_time in", values, "actualTime");
            return (Criteria) this;
        }

        public Criteria andActualTimeNotIn(List<Long> values) {
            addCriterion("actual_time not in", values, "actualTime");
            return (Criteria) this;
        }

        public Criteria andActualTimeBetween(Long value1, Long value2) {
            addCriterion("actual_time between", value1, value2, "actualTime");
            return (Criteria) this;
        }

        public Criteria andActualTimeNotBetween(Long value1, Long value2) {
            addCriterion("actual_time not between", value1, value2, "actualTime");
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

        public Criteria andCheckTimeIsNull() {
            addCriterion("check_time is null");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIsNotNull() {
            addCriterion("check_time is not null");
            return (Criteria) this;
        }

        public Criteria andCheckTimeEqualTo(Long value) {
            addCriterion("check_time =", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotEqualTo(Long value) {
            addCriterion("check_time <>", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeGreaterThan(Long value) {
            addCriterion("check_time >", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("check_time >=", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLessThan(Long value) {
            addCriterion("check_time <", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLessThanOrEqualTo(Long value) {
            addCriterion("check_time <=", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIn(List<Long> values) {
            addCriterion("check_time in", values, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotIn(List<Long> values) {
            addCriterion("check_time not in", values, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeBetween(Long value1, Long value2) {
            addCriterion("check_time between", value1, value2, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotBetween(Long value1, Long value2) {
            addCriterion("check_time not between", value1, value2, "checkTime");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeIsNull() {
            addCriterion("answer_time is null");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeIsNotNull() {
            addCriterion("answer_time is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeEqualTo(Long value) {
            addCriterion("answer_time =", value, "answerTime");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeNotEqualTo(Long value) {
            addCriterion("answer_time <>", value, "answerTime");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeGreaterThan(Long value) {
            addCriterion("answer_time >", value, "answerTime");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("answer_time >=", value, "answerTime");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeLessThan(Long value) {
            addCriterion("answer_time <", value, "answerTime");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeLessThanOrEqualTo(Long value) {
            addCriterion("answer_time <=", value, "answerTime");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeIn(List<Long> values) {
            addCriterion("answer_time in", values, "answerTime");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeNotIn(List<Long> values) {
            addCriterion("answer_time not in", values, "answerTime");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeBetween(Long value1, Long value2) {
            addCriterion("answer_time between", value1, value2, "answerTime");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeNotBetween(Long value1, Long value2) {
            addCriterion("answer_time not between", value1, value2, "answerTime");
            return (Criteria) this;
        }

        public Criteria andStarLevelIsNull() {
            addCriterion("star_level is null");
            return (Criteria) this;
        }

        public Criteria andStarLevelIsNotNull() {
            addCriterion("star_level is not null");
            return (Criteria) this;
        }

        public Criteria andStarLevelEqualTo(Integer value) {
            addCriterion("star_level =", value, "starLevel");
            return (Criteria) this;
        }

        public Criteria andStarLevelNotEqualTo(Integer value) {
            addCriterion("star_level <>", value, "starLevel");
            return (Criteria) this;
        }

        public Criteria andStarLevelGreaterThan(Integer value) {
            addCriterion("star_level >", value, "starLevel");
            return (Criteria) this;
        }

        public Criteria andStarLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("star_level >=", value, "starLevel");
            return (Criteria) this;
        }

        public Criteria andStarLevelLessThan(Integer value) {
            addCriterion("star_level <", value, "starLevel");
            return (Criteria) this;
        }

        public Criteria andStarLevelLessThanOrEqualTo(Integer value) {
            addCriterion("star_level <=", value, "starLevel");
            return (Criteria) this;
        }

        public Criteria andStarLevelIn(List<Integer> values) {
            addCriterion("star_level in", values, "starLevel");
            return (Criteria) this;
        }

        public Criteria andStarLevelNotIn(List<Integer> values) {
            addCriterion("star_level not in", values, "starLevel");
            return (Criteria) this;
        }

        public Criteria andStarLevelBetween(Integer value1, Integer value2) {
            addCriterion("star_level between", value1, value2, "starLevel");
            return (Criteria) this;
        }

        public Criteria andStarLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("star_level not between", value1, value2, "starLevel");
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