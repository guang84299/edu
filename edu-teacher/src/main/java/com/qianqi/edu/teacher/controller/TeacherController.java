package com.qianqi.edu.teacher.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qianqi.edu.pojo.Grade;
import com.qianqi.edu.pojo.Paper;
import com.qianqi.edu.pojo.PaperAnswer;
import com.qianqi.edu.pojo.PaperItem;
import com.qianqi.edu.pojo.QuestionJudge;
import com.qianqi.edu.pojo.StudentTclass;
import com.qianqi.edu.pojo.Subject;
import com.qianqi.edu.pojo.Tclass;
import com.qianqi.edu.pojo.Teacher;
import com.qianqi.edu.pojo.common.EasyUIDataGridResult;
import com.qianqi.edu.pojo.common.EduResult;
import com.qianqi.edu.pojo.common.PaperResult;
import com.qianqi.edu.pojo.common.QuestionResult;
import com.qianqi.edu.service.GradeService;
import com.qianqi.edu.service.PaperService;
import com.qianqi.edu.service.QuestionService;
import com.qianqi.edu.service.SsoService;
import com.qianqi.edu.service.StudentService;
import com.qianqi.edu.service.SubjectService;
import com.qianqi.edu.service.TclassService;
import com.qianqi.edu.service.TeacherService;

@Controller
public class TeacherController {
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private GradeService gradeService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private TclassService tclassService;
	@Autowired
	private PaperService paperService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private SsoService ssoService;
	
	@RequestMapping("/toRegister")
	public String toRegister(Model model)
	{
		List<Grade> grades = gradeService.findGradeAll();
		List<Subject> subjects = subjectService.findSubjectAll();
		model.addAttribute("grades", grades);
		model.addAttribute("subjects", subjects);
		return "register";
	}
	
	@RequestMapping("/teacher/register")
	@ResponseBody
	public EduResult register(@RequestBody Teacher teacher)
	{
		teacher.setState(0);
		teacher.setUpdated(new Date());
		teacher.setCreated(new Date());
		teacherService.addTeacher(teacher);
		return EduResult.ok(null, null);
	}
	
	@RequestMapping("/teacher/login")
	@ResponseBody
	public EduResult login(String phone,String password)
	{
		if(StringUtils.isEmpty(phone) || StringUtils.isEmpty(password))
			return EduResult.err("null", null); 
		return ssoService.teacherLogin(phone, password);
	}
	
	@RequestMapping("/teacher/loginout")
	@ResponseBody
	public EduResult loginout(String token)
	{
		if(StringUtils.isEmpty(token))
			return EduResult.err("null", null); 
		return ssoService.teacherLoginOut(token);
	}
	
	@RequestMapping("/tclass/toadd")
	public String toAddTclass()
	{
		return "tclass-add";
	}
	
	@RequestMapping("/tclass/add")
	@ResponseBody
	public EduResult addTclass(@RequestBody Tclass tclass)
	{
		tclass.setCreated(new Date());
		tclass.setUpdated(new Date());
		tclassService.addTclass(tclass);
		return EduResult.ok("", null);
	}
	
	@RequestMapping("/tclass/tolist")
	public String toTclassList()
	{
		return "tclass-list";
	}
	
	@RequestMapping("/tclass/list")
	@ResponseBody
	public EasyUIDataGridResult tclassList(@RequestParam(defaultValue="0") long teacherId, @RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="5")int rows)
	{
		EasyUIDataGridResult result = tclassService.findTclassList(teacherId, page, rows);
		return result;
	}
	

	@RequestMapping("/tclass/toedit")
	public String toEditTclass()
	{
		return "tclass-edit";
	}
	
	@RequestMapping("/tclass/edit")
	@ResponseBody
	public EduResult editTclass(@RequestBody Tclass tclass)
	{
		tclass.setUpdated(new Date());
		tclassService.updateTclass(tclass);
		return EduResult.ok("", null);
	}
	
	@RequestMapping("/tclass/delete")
	@ResponseBody
	public EduResult deleteTclass(String ids)
	{
		String[] idss = ids.split(",");
		for(String sid : idss)
		{
			long id = Long.parseLong(sid);
			tclassService.deleteTclass(id);
		}
		return EduResult.ok("", null);
	}
	
	@RequestMapping("/paper/toadd")
	public String toAddPaper(Model model,HttpServletRequest request)
	{
		List<Subject> subjects = subjectService.findSubjectAll();
		model.addAttribute("subjects", subjects);
		Teacher teacher = (Teacher) request.getAttribute("teacher");
		List<Tclass> tclasss = tclassService.findTclassByTeacherId(teacher.getId());
		model.addAttribute("tclasss", tclasss);
		
		return "paper-add";
	}
	
	@RequestMapping("/paper/add")
	@ResponseBody
	public EduResult addPaper(@RequestBody Paper paper)
	{
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		paper.setName(format.format(new Date()));
		paper.setState(1);
		paper.setCreated(new Date());
		paper.setUpdated(new Date());
		long id = paperService.addPaper(paper);
		return EduResult.ok("", id);
	}
	
	@RequestMapping("/paper/tolist")
	public String toPaperList()
	{
		return "paper-list";
	}
	
	@RequestMapping("/paper/list")
	@ResponseBody
	public EasyUIDataGridResult paperList(@RequestParam(defaultValue="0") long teacherId, @RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="5")int rows)
	{
		EasyUIDataGridResult result = paperService.findPaperList(teacherId, page, rows);
		List<PaperResult> res = new ArrayList<>();
		List<Paper> list = (List<Paper>) result.getRows();
		for(Paper paper : list)
		{
			PaperResult paperResult = new PaperResult(paper);
			paperResult.setTclass(tclassService.findTclass(paper.getTclassId()).getName());
			paperResult.setSubject(subjectService.findSubject(paper.getSubjectId()).getName());
			if(paper.getState() == 1)
				paperResult.setStateStr("未发布");
			else
				paperResult.setStateStr("发布");
			res.add(paperResult);
		}
		result.setRows(res);
		return result;
	}
	
	@RequestMapping("/paper/toedit")
	public String toEditPaper(Model model,HttpServletRequest request)
	{
		List<Subject> subjects = subjectService.findSubjectAll();
		model.addAttribute("subjects", subjects);
		Teacher teacher = (Teacher) request.getAttribute("teacher");
		List<Tclass> tclasss = tclassService.findTclassByTeacherId(teacher.getId());
		model.addAttribute("tclasss", tclasss);
		return "paper-edit";
	}
	
	@RequestMapping("/paper/question/tolist")
	public String toQuestionList(@RequestParam(defaultValue="0") long paperId,Model model)
	{
		model.addAttribute("paperId", paperId);
		return "paper-question-list";
	}
	
	@RequestMapping("/paper/question/list")
	@ResponseBody
	public EasyUIDataGridResult questionList(@RequestParam(defaultValue="0") long paperId,@RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="5")int rows)
	{
		EasyUIDataGridResult result = questionService.findQuestionJudgeList(page, rows);
		List<PaperItem> haslist = paperService.findPaperItemByPaperId(paperId);
		List<QuestionJudge> list = (List<QuestionJudge>) result.getRows();
		List<QuestionResult> newlist = new ArrayList<>();
		for(QuestionJudge question : list)
		{
			QuestionResult questionResult = new QuestionResult(question);
			questionResult.setCk(false);
			for(PaperItem item : haslist)
			{
				if((long)(item.getQuestionId()) == (long)(question.getId()))
				{
					questionResult.setCk(true);
					break;
				}
			}
			newlist.add(questionResult);
		}
		result.setRows(newlist);
		return result;
	}
	
	@RequestMapping("/paper/question/save")
	@ResponseBody
	public EduResult questionSave(int type,long paperId,String ids)
	{
		List<PaperItem> list = paperService.findPaperItemByPaperId(paperId);
		List<PaperItem> delist = new ArrayList<>();
		String[] idss = ids.split(",");
		//删除多余 过滤重复
		for(PaperItem item : list)
		{
			for(int i=0;i<idss.length;i++)
			{
				String sid = idss[i];
				long id = Long.parseLong(sid);
				if(item.getId() == id)
				{
					idss[i] = "0";
					break;
				}
			}
			delist.add(item);
		}
		for(PaperItem item : delist)
		{
			paperService.deletePaperItem(item.getId());
		}
		
		
		for(String sid : idss)
		{
			long id = Long.parseLong(sid);
			//判断是否存在 不存在就创建
			if(id != 0)
			{
				PaperItem item = new PaperItem();
				item.setPaperId(paperId);
				item.setType(type);
				item.setQuestionType(1);
				item.setQuestionId(id);
				item.setCreated(new Date());
				paperService.addPaperItem(item);
			}
		}
		return EduResult.ok("", null);
	}
	
	//发布作业
	@RequestMapping("/paper/edit")
	@ResponseBody
	public EduResult editPaper(@RequestBody Paper paper)
	{
		paper.setState(2);
		paper.setUpdated(new Date());
		paperService.updatePaper(paper);
		//给每个学生发布作业
		List<StudentTclass> sts = studentService.findStudentTclassByTclassId(paper.getTclassId());
		for(StudentTclass st : sts)
		{
			PaperAnswer pa = paperService.findPaperAnswerByStudentIdAndPaperId(st.getStudentId(), paper.getId());
			if(pa == null)
			{
				PaperAnswer answer = new PaperAnswer();
				answer.setPaperId(paper.getId());
				answer.setStudentId(st.getStudentId());
				answer.setState(0);
				answer.setCreated(new Date());
				paperService.addPaperAnswer(answer);
			}
		}
		
		return EduResult.ok("", null);
	}
}
