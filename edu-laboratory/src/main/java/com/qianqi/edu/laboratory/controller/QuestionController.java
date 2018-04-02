package com.qianqi.edu.laboratory.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qianqi.edu.laboratory.pojo.QuestionCategoryItem;
import com.qianqi.edu.pojo.Grade;
import com.qianqi.edu.pojo.Knowledge;
import com.qianqi.edu.pojo.Question;
import com.qianqi.edu.pojo.QuestionCategory;
import com.qianqi.edu.pojo.Subject;
import com.qianqi.edu.pojo.common.EasyUIDataGridResult;
import com.qianqi.edu.pojo.common.EduResult;
import com.qianqi.edu.pojo.common.QuestionResult;
import com.qianqi.edu.service.GradeService;
import com.qianqi.edu.service.KnowledgeService;
import com.qianqi.edu.service.QuestionService;
import com.qianqi.edu.service.SubjectService;

@Controller
public class QuestionController {
	@Autowired
	private QuestionService questionService;
	@Autowired
	private GradeService gradeService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private KnowledgeService knowledgeService;
	
	@RequestMapping("/question/toadd")
	public String toAdd(Model model)
	{
		List<Grade> grades = gradeService.findGradeAll();
		List<Subject> subjects = subjectService.findSubjectAll();
		model.addAttribute("grades", grades);
		model.addAttribute("subjects", subjects);
		return "question-add";
	}
	
	@RequestMapping("/question/add")
	@ResponseBody
	public EduResult add(@RequestBody QuestionResult question)
	{
		if(!StringUtils.isEmpty(question.getKnowledgePoint()))
		{
			Knowledge kg = knowledgeService.findKnowledgeByKnowledge(question.getKnowledgePoint());
			if(kg == null)
			{
				kg = new Knowledge();
				kg.setKnowledge(question.getKnowledgePoint());
				kg.setSubjectId(question.getSubjectId());
				long id = knowledgeService.addKnowledge(kg);
				kg.setId(id);
			}
			question.setKnowledgeId(kg.getId());
		}
		question.setTeacherId(0l);
		question.setCreated(new Date());
		question.setUpdated(new Date());
		questionService.addQuestion(question);
		return EduResult.ok(null, null);
	}
	
	
	@RequestMapping("/question/tolist")
	public String toQuestionList(Model model)
	{
		List<Grade> grades = gradeService.findGradeAll();
		List<Subject> subjects = subjectService.findSubjectAll();
		model.addAttribute("grades", grades);
		model.addAttribute("subjects", subjects);
		return "question-list";
	}
	
	
	@RequestMapping("/question/list")
	@ResponseBody
	public EasyUIDataGridResult questionList(@RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="5")int rows,HttpServletRequest request)
	{
		String subjectId = request.getParameter("subjectIds");
		String gradeId = request.getParameter("gradeIds");
		String type = request.getParameter("types");
		String difficult = request.getParameter("difficults");
		
		List<Integer> subjectIds = new ArrayList<>();
		List<Integer> gradeIds = new ArrayList<>();
		List<Integer> types = new ArrayList<>();
		List<Integer> difficults = new ArrayList<>();
		
		if(!StringUtils.isEmpty(subjectId) && !"0".equals(subjectId))
		{
			subjectIds.add(Integer.parseInt(subjectId));
		}
		if(!StringUtils.isEmpty(gradeId) && !"0".equals(gradeId))
		{
			gradeIds.add(Integer.parseInt(gradeId));
		}
		if(!StringUtils.isEmpty(type) && !"0".equals(type))
		{
			types.add(Integer.parseInt(type));
		}
		if(!StringUtils.isEmpty(difficult) && !"-1".equals(difficult))
		{
			difficults.add(Integer.parseInt(difficult));
		}
		
		EasyUIDataGridResult result = questionService.findQuestionList(types,subjectIds,gradeIds,difficults,page, rows);
		List<Question> list = (List<Question>) result.getRows();
		List<QuestionResult> list2 = new ArrayList<QuestionResult>();
		for(Question question : list)
		{
			Knowledge kg = knowledgeService.findKnowledge(question.getKnowledgeId());
			QuestionResult r = new QuestionResult(question);
			r.setKnowledgePoint(kg.getKnowledge());
			list2.add(r);
		}
		result.setRows(list2);
		
		return result;
	}
	
	
	@RequestMapping("/question/toedit")
	public String toEditQuestion(Model model)
	{
		List<Grade> grades = gradeService.findGradeAll();
		List<Subject> subjects = subjectService.findSubjectAll();
		model.addAttribute("grades", grades);
		model.addAttribute("subjects", subjects);
		return "question-edit";
	}
	
	
	@RequestMapping("/question/toimport")
	public String toImport()
	{
		return "question-import";
	}
	
	@RequestMapping("/question/edit")
	@ResponseBody
	public EduResult editQuestion(@RequestBody Question question)
	{
		question.setUpdated(new Date());
		questionService.updateQuestion(question);
		return EduResult.ok("", null);
	}
	
	
	@RequestMapping("/question/delete")
	@ResponseBody
	public EduResult deleteQuestion(String ids)
	{
		String[] idss = ids.split(",");
		for(String sid : idss)
		{
			long id = Long.parseLong(sid);
			questionService.deleteQuestion(id);
		}
		return EduResult.ok("", null);
	}
	

	
	@RequestMapping("/question/import")
	@ResponseBody
    public EduResult uploadFile(MultipartFile excel) {
		List<Question> questions = new ArrayList<Question>();
		List<Grade> grades = gradeService.findGradeAll();
		List<Subject> subjects = subjectService.findSubjectAll();
		try {
			Workbook workbook = WorkbookFactory.create(excel.getInputStream());
			Sheet sheet = workbook.getSheetAt(0);
			DataFormatter formatter = new DataFormatter();
			List<String> colNames = new ArrayList<>();
			 for (Row row : sheet) 
			 {
				 Question question = new Question();
				 for (Cell cell : row)
				 {
					 String text = formatter.formatCellValue(cell);
					 if(row.getRowNum() == 0)
		             {
		                		colNames.add(text);
		             }
					 else
					 {
						 if(colNames.get(cell.getColumnIndex()).equals("类型"))
						 {
							 question.setType(Integer.parseInt(text));
						 }
						 else if(colNames.get(cell.getColumnIndex()).equals("题干"))
						 {
							 question.setContext(text);
						 }
						 else if(colNames.get(cell.getColumnIndex()).equals("答案"))
						 {
							 question.setAnswer(text);
						 }
						 else if(colNames.get(cell.getColumnIndex()).equals("分数"))
						 {
							 question.setScore(Integer.parseInt(text));
						 }
						 else if(colNames.get(cell.getColumnIndex()).equals("老师ID"))
						 {
							 question.setTeacherId(Long.parseLong(text));
						 }
						 else if(colNames.get(cell.getColumnIndex()).equals("学科"))
						 {
							 int subjectId = 0;
							 for(Subject subject : subjects)
							 {
								 if(subject.getName().equals(text))
								 {
									 subjectId = subject.getId();
									 break;
								 }
							 }
							 if(subjectId == 0)
							 {
								 subjectId = subjects.get(0).getId();
							 }
							 question.setSubjectId(subjectId);
						 }
						 else if(colNames.get(cell.getColumnIndex()).equals("年级"))
						 {
							 int gradeId = 0;
							 for(Grade grade : grades)
							 {
								 if(grade.getName().equals(text))
								 {
									 gradeId = grade.getId();
									 break;
								 }
							 }
							 if(gradeId == 0)
							 {
								 gradeId = grades.get(0).getId();
							 }
							 question.setGradeId(gradeId);
						 }
						 else if(colNames.get(cell.getColumnIndex()).equals("困难度"))
						 {
							 question.setDifficult(Integer.parseInt(text));
						 }
						 else if(colNames.get(cell.getColumnIndex()).equals("知识点"))
						 {
							 if(!StringUtils.isEmpty(text))
							 {
								 Knowledge kd = knowledgeService.findKnowledgeByKnowledge(text);
								 if(kd == null)
								 {
									 kd = new Knowledge();
									 kd.setKnowledge(text);
									 kd.setSubjectId(question.getSubjectId());
									 long id = knowledgeService.addKnowledge(kd);
									 kd.setId(id);
								 }
								 question.setKnowledgeId(kd.getId());
							 }
							 
						 }
						 else if(colNames.get(cell.getColumnIndex()).equals("答题时间"))
						 {
							 question.setNormalTime(Integer.parseInt(text));
						 }
						 else if(colNames.get(cell.getColumnIndex()).equals("A"))
						 {
							 if(!StringUtils.isEmpty(text))
								 question.setChoiceA(text);
						 }
						 else if(colNames.get(cell.getColumnIndex()).equals("B"))
						 {
							 if(!StringUtils.isEmpty(text))
								 question.setChoiceB(text);
						 }
						 else if(colNames.get(cell.getColumnIndex()).equals("C"))
						 {
							 if(!StringUtils.isEmpty(text))
								 question.setChoiceC(text);
						 }
						 else if(colNames.get(cell.getColumnIndex()).equals("D"))
						 {
							 if(!StringUtils.isEmpty(text))
								 question.setChoiceD(text);
						 }
					 }
				
				 }
				 if(row.getRowNum() != 0)
				 {
					 question.setUpdated(new Date());
					 question.setCreated(new Date());
					 questions.add(question);
				 }
			 }
		} catch (Exception e) {
			e.printStackTrace();
			return EduResult.err(null, null);
		}
        for(Question question : questions)
        {
        		questionService.addQuestion(question);
        }
		return EduResult.ok("", null);
    }
	
	
	@RequestMapping("/question/category")
	public String category()
	{
		return "question-category";
	}
	
	@RequestMapping("/question/category/list")
	@ResponseBody
	public List<QuestionCategoryItem> categoryList(@RequestParam(name="id",defaultValue="0") Integer parentId)
	{
		List<QuestionCategoryItem> items = new ArrayList<QuestionCategoryItem>();
		List<QuestionCategory> list = questionService.findQuestionCategoryByParentId(parentId);
		for(QuestionCategory category : list)
		{
			items.add(new QuestionCategoryItem(category.getId(), category.getName(), "closed"));
		}
		return items;
	}
	
	@RequestMapping("/question/category/create")
	@ResponseBody
	public EduResult categoryCreate(@RequestParam String name,@RequestParam Integer parentId)
	{
		if(StringUtils.isEmpty(parentId) || StringUtils.isEmpty(name))
		{
			return EduResult.err(null, null);
		}
		QuestionCategory category = new QuestionCategory();
		category.setParentId(parentId);
		category.setIsParent(false);
		category.setName(name);
		category.setSortOrder(1);
		category.setStatus(1);
		category.setUpdated(new Date());
		category.setCreated(new Date());
		
		int id = questionService.addQuestionCategory(category);
		return EduResult.ok(null, id);
	}
	
	@RequestMapping("/question/category/update")
	@ResponseBody
	public EduResult categoryUpdate(String name,Integer id)
	{
		if(StringUtils.isEmpty(id) || StringUtils.isEmpty(name))
		{
			return EduResult.err(null, null);
		}
		QuestionCategory category = questionService.findQuestionCategory(id);
		if(category != null)
		{
			category.setName(name);
			category.setUpdated(new Date());
		}
		questionService.updateQuestionCategory(category);
		return EduResult.ok(null, id);
	}
	
	@RequestMapping("/question/category/delete")
	@ResponseBody
	public EduResult categoryUpdate(Integer id)
	{
		if(StringUtils.isEmpty(id) || id == 1)
		{
			return EduResult.err(null, null);
		}
		QuestionCategory category = questionService.findQuestionCategory(id);
		if(category != null)
		{
			category.setStatus(2);
			category.setUpdated(new Date());
		}
		questionService.updateQuestionCategory(category);
		return EduResult.ok(null, id);
	}
}
