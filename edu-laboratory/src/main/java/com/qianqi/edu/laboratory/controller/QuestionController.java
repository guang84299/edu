package com.qianqi.edu.laboratory.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.qianqi.edu.pojo.Question;
import com.qianqi.edu.pojo.QuestionCategory;
import com.qianqi.edu.pojo.Subject;
import com.qianqi.edu.pojo.common.EasyUIDataGridResult;
import com.qianqi.edu.pojo.common.EduResult;
import com.qianqi.edu.service.GradeService;
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
	public EduResult add(@RequestBody Question question)
	{
		question.setTeacherId(0l);
		question.setCreated(new Date());
		question.setUpdated(new Date());
		questionService.addQuestion(question);
		return EduResult.ok(null, null);
	}
	
	
	@RequestMapping("/question/tolist")
	public String toJudgeList()
	{
		return "question-list";
	}
	
	
	@RequestMapping("/question/list")
	@ResponseBody
	public EasyUIDataGridResult questionJudgeList(@RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="5")int rows)
	{
		EasyUIDataGridResult result = questionService.findQuestionList(page, rows);
		return result;
	}
	
	
	@RequestMapping("/question/toedit")
	public String toEditJudge(Model model)
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
	public EduResult editJudge(@RequestBody Question question)
	{
		question.setUpdated(new Date());
		questionService.updateQuestion(question);
		return EduResult.ok("", null);
	}
	
	
	@RequestMapping("/question/delete")
	@ResponseBody
	public EduResult deleteJudge(String ids)
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
						 if(colNames.get(cell.getColumnIndex()).equals("context"))
						 {
							 question.setContext(text);
						 }
						 else if(colNames.get(cell.getColumnIndex()).equals("answer"))
						 {
							 question.setAnswer(text);
						 }
						 else if(colNames.get(cell.getColumnIndex()).equals("score"))
						 {
							 question.setScore(Integer.parseInt(text));
						 }
						 else if(colNames.get(cell.getColumnIndex()).equals("teacher_id"))
						 {
							 question.setTeacherId(Long.parseLong(text));
						 }
						 else if(colNames.get(cell.getColumnIndex()).equals("difficult"))
						 {
							 question.setDifficult(Integer.parseInt(text));
						 }
						 else if(colNames.get(cell.getColumnIndex()).equals("knowledge_point"))
						 {
							 question.setKnowledgePoint(text);
						 }
						 else if(colNames.get(cell.getColumnIndex()).equals("normal_time"))
						 {
							 question.setNormalTime(Integer.parseInt(text));
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
