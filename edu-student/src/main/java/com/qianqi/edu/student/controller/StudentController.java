package com.qianqi.edu.student.controller;

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

import com.qianqi.edu.pojo.Paper;
import com.qianqi.edu.pojo.PaperAnswer;
import com.qianqi.edu.pojo.PaperAnswerItem;
import com.qianqi.edu.pojo.PaperItem;
import com.qianqi.edu.pojo.QuestionJudge;
import com.qianqi.edu.pojo.Student;
import com.qianqi.edu.pojo.StudentTclass;
import com.qianqi.edu.pojo.Tclass;
import com.qianqi.edu.pojo.common.EasyUIDataGridResult;
import com.qianqi.edu.pojo.common.EduResult;
import com.qianqi.edu.pojo.common.PaperAnswerResult;
import com.qianqi.edu.pojo.common.PaperResult;
import com.qianqi.edu.pojo.common.QuestionItem;
import com.qianqi.edu.service.PaperService;
import com.qianqi.edu.service.QuestionService;
import com.qianqi.edu.service.SsoService;
import com.qianqi.edu.service.StudentService;
import com.qianqi.edu.service.SubjectService;
import com.qianqi.edu.service.TclassService;

@Controller
public class StudentController {
	@Autowired
	private StudentService studentService;
	@Autowired
	private TclassService tclassService;
	@Autowired
	private PaperService paperService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private SsoService ssoService;
	
	@RequestMapping("/toRegister")
	public String toRegister()
	{
		return "register";
	}
	
	@RequestMapping("/student/register")
	@ResponseBody
	public EduResult register(@RequestBody Student student)
	{
		student.setState(0);
		student.setUpdated(new Date());
		student.setCreated(new Date());
		studentService.addStudent(student);
		return EduResult.ok(null, null);
	}
	
	@RequestMapping("/student/login")
	@ResponseBody
	public EduResult login(String phone,String password)
	{
		if(StringUtils.isEmpty(phone) || StringUtils.isEmpty(password))
			return EduResult.err("null", null); 
		return ssoService.studentLogin(phone, password);
	}
	
	@RequestMapping("/student/loginout")
	@ResponseBody
	public EduResult loginout(String token)
	{
		if(StringUtils.isEmpty(token))
			return EduResult.err("null", null); 
		return ssoService.studentLoginOut(token);
	}
	
	@RequestMapping("/student_tclass/toadd")
	public String toAddStudentTclass()
	{
		return "student-tclass-add";
	}
	
	@RequestMapping("/student_tclass/add")
	@ResponseBody
	public EduResult addStudentTclass(@RequestBody StudentTclass studentTclass)
	{
		Tclass tclass = tclassService.findTclass(studentTclass.getTclassId());
		if(tclass == null)
		{
			return EduResult.err("代码不存在！", null);
		}
		studentTclass.setCreated(new Date());
		studentTclass.setUpdated(new Date());
		studentService.addStudentTclass(studentTclass);
		return EduResult.ok("", null);
	}
	
	@RequestMapping("/student_tclass/tolist")
	public String toStudentTclassList()
	{
		return "student-tclass-list";
	}
	
	@RequestMapping("/student_tclass/list")
	@ResponseBody
	public EasyUIDataGridResult studentTclassList(@RequestParam(defaultValue="0") long studentId, @RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="5")int rows)
	{
		EasyUIDataGridResult result = studentService.findStudentTclassListByStudentId(studentId, page, rows);
		return result;
	}
	
	@RequestMapping("/student_tclass/delete")
	@ResponseBody
	public EduResult deleteStudentTclass(String ids)
	{
		String[] idss = ids.split(",");
		for(String sid : idss)
		{
			long id = Long.parseLong(sid);
			studentService.deleteStudentTclass(id);
		}
		return EduResult.ok("", null);
	}
	
	@RequestMapping("/paper/toanswer")
	public String toPaperAnswer(Model model,HttpServletRequest request)
	{
		Student student = (Student) request.getAttribute("student");
		String paperIdStr =  request.getParameter("paperId");
		PaperAnswer pa = null;
		if(paperIdStr != null)
		{
			long paperId = Long.parseLong(paperIdStr);
			pa = paperService.findPaperAnswerByStudentIdAndPaperId(student.getId(), paperId);
		}
		else
		{
			List<PaperAnswer> list = paperService.findPaperAnswerByStudentIdAndState(student.getId(), 100);
			if(list != null && list.size()>0)
			{
				pa = list.get(0);
			}
		}
		
		if(pa != null)
		{
			List<QuestionItem> items = new ArrayList<>();
			List<PaperItem> pis = paperService.findPaperItemByPaperId(pa.getPaperId());
			for(PaperItem pi : pis)
			{
				QuestionJudge judge = questionService.findQuestionJudgeById(pi.getQuestionId());
				if(judge != null)
				{
					QuestionItem item = new QuestionItem(judge);
					item.setPaperAnswerId(pa.getId());
					item.setPaperItemId(pi.getId());
					item.setType(pi.getType());
					item.setPaperId(pi.getPaperId());
					item.setQuestionType(pi.getQuestionType());
					item.setPaperAnswerItem(paperService.findPaperAnswerItem(item.getPaperItemId(), item.getPaperAnswerId()));
					items.add(item);
				}
			}
			model.addAttribute("items", items);
		}
		return "answer";
	}
	
	@RequestMapping("/paper/answer")
	@ResponseBody
	public EduResult answer(@RequestBody List<PaperAnswerItem> items,HttpServletRequest request)
	{
		if(items != null)
		{
			long paperId = 0l;
			for(PaperAnswerItem item : items)
			{
				if(paperId == 0)
				{
					PaperItem pi = paperService.findPaperItemById(item.getPaperItemId());
					paperId = pi.getPaperId();
				}
				PaperAnswerItem paperAnswerItem = paperService.findPaperAnswerItem(item.getPaperItemId(), item.getPaperAnswerId());
				if(paperAnswerItem == null)
				{
					item.setCreated(new Date());
					paperService.addPaperAnswerItem(item);
				}
				else
				{
					paperAnswerItem.setAnswer(item.getAnswer());
					paperService.updatePaperAnswerItem(paperAnswerItem);
				}
			}
			if(paperId != 0)
			{
				int num = paperService.findPaperItemNumByPaperId(paperId);
				float size = items.size();
				int state = (int) (size/num*100);
				Student student = (Student) request.getAttribute("student");
				PaperAnswer pa = paperService.findPaperAnswerByStudentIdAndPaperId(student.getId(), paperId);
				if(pa != null)
				{
					pa.setState(state);
					paperService.updatePaperAnswer(pa);
				}
			}
			
		}
		return EduResult.ok("", null);
	}
	
	@RequestMapping("/paper/tolist")
	public String toPaperList()
	{
		return "paper-list";
	}
	
	@RequestMapping("/paper/list")
	@ResponseBody
	public EasyUIDataGridResult paperList(@RequestParam(defaultValue="0") long studentId, @RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="5")int rows)
	{
		EasyUIDataGridResult result = paperService.findPaperAnswerByStudentId(studentId, page, rows);
		List<PaperAnswer> pas =  (List<PaperAnswer>) result.getRows();
		List<PaperAnswerResult> pasr = new ArrayList<>();
		for(PaperAnswer pa : pas)
		{
			Paper paper = paperService.findPaperById(pa.getPaperId());
			PaperAnswerResult answerResult = new PaperAnswerResult(pa);
			answerResult.setTclass(tclassService.findTclass(paper.getTclassId()).getName());
			answerResult.setSubject(subjectService.findSubject(paper.getSubjectId()).getName());
			answerResult.setStateStr(pa.getState()+"%");
			pasr.add(answerResult);
		}
		result.setRows(pasr);
		return result;
	}
}