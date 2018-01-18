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
import com.qianqi.edu.pojo.QuestionCategory;
import com.qianqi.edu.pojo.QuestionJudge;
import com.qianqi.edu.pojo.QuestionMulti;
import com.qianqi.edu.pojo.QuestionSingle;
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
	
	@RequestMapping("/question/addJudge")
	@ResponseBody
	public EduResult addJudge(@RequestBody QuestionJudge questionJudge)
	{
		questionJudge.setTeacherId(0l);
		questionJudge.setCreated(new Date());
		questionJudge.setUpdated(new Date());
		questionService.addQuestionJudge(questionJudge);
		return EduResult.ok(null, null);
	}
	
	@RequestMapping("/question/addSingle")
	@ResponseBody
	public EduResult addSingle(@RequestBody QuestionSingle questionSingle)
	{
		questionSingle.setTeacherId(0l);
		questionSingle.setCreated(new Date());
		questionSingle.setUpdated(new Date());
		questionService.addQuestionSingle(questionSingle);
		return EduResult.ok(null, null);
	}
	
	@RequestMapping("/question/addMulti")
	@ResponseBody
	public EduResult addMulti(@RequestBody QuestionMulti questionMulti)
	{
		questionMulti.setTeacherId(0l);
		questionMulti.setCreated(new Date());
		questionMulti.setUpdated(new Date());
		questionService.addQuestionMulti(questionMulti);
		return EduResult.ok(null, null);
	}
	
	@RequestMapping("/question/tojudgelist")
	public String toJudgeList()
	{
		return "question-judgelist";
	}
	
	@RequestMapping("/question/tosinglelist")
	public String toSingleList()
	{
		return "question-singlelist";
	}
	
	@RequestMapping("/question/tomultilist")
	public String toMultiList()
	{
		return "question-multilist";
	}
	
	@RequestMapping("/question/judgelist")
	@ResponseBody
	public EasyUIDataGridResult questionJudgeList(@RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="5")int rows)
	{
		EasyUIDataGridResult result = questionService.findQuestionJudgeList(page, rows);
		return result;
	}
	
	@RequestMapping("/question/singlelist")
	@ResponseBody
	public EasyUIDataGridResult questionSingleList(@RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="5")int rows)
	{
		EasyUIDataGridResult result = questionService.findQuestionSingleList(page, rows);		
		return result;
	}
	
	@RequestMapping("/question/multilist")
	@ResponseBody
	public EasyUIDataGridResult questionMultiList(@RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="5")int rows)
	{
		EasyUIDataGridResult result = questionService.findQuestionMultiList(page, rows);
		return result;
	}
	
	@RequestMapping("/question/toeditjudge")
	public String toEditJudge(Model model)
	{
		List<Grade> grades = gradeService.findGradeAll();
		List<Subject> subjects = subjectService.findSubjectAll();
		model.addAttribute("grades", grades);
		model.addAttribute("subjects", subjects);
		return "question-editjudge";
	}
	
	@RequestMapping("/question/toeditsingle")
	public String toEditSingle(Model model)
	{
		List<Grade> grades = gradeService.findGradeAll();
		List<Subject> subjects = subjectService.findSubjectAll();
		model.addAttribute("grades", grades);
		model.addAttribute("subjects", subjects);
		return "question-editsingle";
	}
	
	@RequestMapping("/question/toeditmulti")
	public String toEditMulti(Model model)
	{
		List<Grade> grades = gradeService.findGradeAll();
		List<Subject> subjects = subjectService.findSubjectAll();
		model.addAttribute("grades", grades);
		model.addAttribute("subjects", subjects);
		return "question-editmulti";
	}
	
	@RequestMapping("/question/toimport")
	public String toImport()
	{
		return "question-import";
	}
	
	@RequestMapping("/question/editjudge")
	@ResponseBody
	public EduResult editJudge(@RequestBody QuestionJudge questionJudge)
	{
		questionJudge.setUpdated(new Date());
		questionService.updateQuestionJudge(questionJudge);
		return EduResult.ok("", null);
	}
	
	@RequestMapping("/question/editsingle")
	@ResponseBody
	public EduResult editSingle(@RequestBody QuestionSingle questionSingle)
	{
		questionSingle.setUpdated(new Date());
		questionService.updateQuestionSingle(questionSingle);
		return EduResult.ok("", null);
	}
	
	@RequestMapping("/question/editmulti")
	@ResponseBody
	public EduResult editMulti(@RequestBody QuestionMulti questionMulti)
	{
		questionMulti.setUpdated(new Date());
		questionService.updateQuestionMulti(questionMulti);
		return EduResult.ok("", null);
	}
	
	@RequestMapping("/question/deletejudge")
	@ResponseBody
	public EduResult deleteJudge(String ids)
	{
		String[] idss = ids.split(",");
		for(String sid : idss)
		{
			long id = Long.parseLong(sid);
			questionService.deleteQuestionJudge(id);
		}
		return EduResult.ok("", null);
	}
	
	@RequestMapping("/question/deletesingle")
	@ResponseBody
	public EduResult deleteSingle(String ids)
	{
		String[] idss = ids.split(",");
		for(String sid : idss)
		{
			long id = Long.parseLong(sid);
			questionService.deleteQuestionSingle(id);
		}
		return EduResult.ok("", null);
	}
	
	@RequestMapping("/question/deletemulti")
	@ResponseBody
	public EduResult deleteMulti(String ids)
	{
		String[] idss = ids.split(",");
		for(String sid : idss)
		{
			long id = Long.parseLong(sid);
			questionService.deleteQuestionMulti(id);
		}
		return EduResult.ok("", null);
	}
	
	@RequestMapping("/question/import")
	@ResponseBody
    public EduResult uploadFile(MultipartFile excel) {
		List<QuestionJudge> questions = new ArrayList<QuestionJudge>();
		try {
			Workbook workbook = WorkbookFactory.create(excel.getInputStream());
			Sheet sheet = workbook.getSheetAt(0);
			DataFormatter formatter = new DataFormatter();
			List<String> colNames = new ArrayList<>();
			 for (Row row : sheet) 
			 {
				 QuestionJudge questionJudge = new QuestionJudge();
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
							 questionJudge.setContext(text);
						 }
						 else if(colNames.get(cell.getColumnIndex()).equals("answer"))
						 {
							 questionJudge.setAnswer(text.equals("1"));
						 }
						 else if(colNames.get(cell.getColumnIndex()).equals("score"))
						 {
							 questionJudge.setScore(Integer.parseInt(text));
						 }
						 else if(colNames.get(cell.getColumnIndex()).equals("teacher_id"))
						 {
							 questionJudge.setTeacherId(Long.parseLong(text));
						 }
						 else if(colNames.get(cell.getColumnIndex()).equals("difficult"))
						 {
							 questionJudge.setDifficult(Integer.parseInt(text));
						 }
						 else if(colNames.get(cell.getColumnIndex()).equals("knowledge_point"))
						 {
							 questionJudge.setKnowledgePoint(text);
						 }
						 else if(colNames.get(cell.getColumnIndex()).equals("normal_time"))
						 {
							 questionJudge.setNormalTime(Integer.parseInt(text));
						 }
					 }
				
				 }
				 if(row.getRowNum() != 0)
				 {
					 questionJudge.setUpdated(new Date());
					 questionJudge.setCreated(new Date());
					 questions.add(questionJudge);
				 }
			 }
		} catch (Exception e) {
			e.printStackTrace();
			return EduResult.err(null, null);
		}
        for(QuestionJudge questionJudge : questions)
        {
        		questionService.addQuestionJudge(questionJudge);
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
