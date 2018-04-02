package com.qianqi.edu.teacher.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.qianqi.edu.pojo.PaperAnswerItem;
import com.qianqi.edu.pojo.PaperItem;
import com.qianqi.edu.pojo.Question;
import com.qianqi.edu.pojo.StudentTeacherSubject;
import com.qianqi.edu.pojo.Subject;
import com.qianqi.edu.pojo.Tclass;
import com.qianqi.edu.pojo.Teacher;
import com.qianqi.edu.pojo.common.EasyUIDataGridResult;
import com.qianqi.edu.pojo.common.EduResult;
import com.qianqi.edu.pojo.common.PaperAnswerResult;
import com.qianqi.edu.pojo.common.PaperResult;
import com.qianqi.edu.pojo.common.QuestionItem;
import com.qianqi.edu.pojo.common.QuestionResult;
import com.qianqi.edu.pojo.common.SearchItem;
import com.qianqi.edu.pojo.common.StudentData;
import com.qianqi.edu.service.GradeService;
import com.qianqi.edu.service.PaperService;
import com.qianqi.edu.service.QuestionService;
import com.qianqi.edu.service.SearchService;
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
	@Autowired
	private SearchService searchService;
	
	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;
	
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
		EasyUIDataGridResult result = questionService.findQuestionList(page, rows);
		List<PaperItem> haslist = paperService.findPaperItemByPaperId(paperId);
		List<Question> list = (List<Question>) result.getRows();
		List<QuestionResult> newlist = new ArrayList<>();
		for(Question question : list)
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
				answer.setCheckState(0);
				answer.setCreated(new Date());
				paperService.addPaperAnswer(answer);
			}
		}
		
		return EduResult.ok("", null);
	}
	
	@RequestMapping("/paper/check/tolist")
	public String toPaperCheckList()
	{
		return "paper-check-list";
	}
	
	@RequestMapping("/paper/check/list")
	@ResponseBody
	public EasyUIDataGridResult checkPaper(@RequestParam(defaultValue="0") long teacherId,@RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="5")int rows,HttpServletRequest request)
	{
		EasyUIDataGridResult res = paperService.findPaperList(teacherId,page,rows);
		List<Paper> papers = (List<Paper>) res.getRows();
		List<Long> paperIds = new ArrayList<>();
		for(Paper paper : papers)
		{
			paperIds.add(paper.getId());
		}
		List<PaperAnswer> pas = paperService.findPaperAnswerByPaperIds(paperIds);
		List<PaperAnswerResult> list = new ArrayList<>();
		for(PaperAnswer pa : pas)
		{
			PaperAnswerResult result = new PaperAnswerResult(pa);
			Paper currpaper = null;
			for(Paper paper : papers)
			{
				if((long)(pa.getPaperId()) == (long)(paper.getId()))
				{
					currpaper = paper;
					break;
				}
			}
			result.setTclass(tclassService.findTclass(currpaper.getTclassId()).getName());
			result.setSubject(subjectService.findSubject(currpaper.getSubjectId()).getName());
			result.setStateStr(pa.getState()+"%");
			if(pa.getCheckState() == 0)
				result.setCheckStateStr("未批改");
			else if(pa.getCheckState() == 1)
				result.setCheckStateStr("批改中");
			else if(pa.getCheckState() == 2)
				result.setCheckStateStr("已批改");
			result.setStudentName(studentService.findStudentById(pa.getStudentId()).getName());
			list.add(result);
		}
		res.setRows(list);
		res.setTotal(list.size());
		
		return res;
	}
	
	@RequestMapping("/paper/tocheck")
	public String toPaperCheck(Model model,HttpServletRequest request)
	{
		String pas = request.getParameter("paperAnswerId");
		if(pas != null)
		{
			long paid = Long.parseLong(pas);
			model.addAttribute("paid", paid);
			
			PaperAnswer pa = paperService.findPaperAnswerById(paid);
			if(pa != null)
			{
				List<QuestionItem> items = new ArrayList<>();
				List<PaperItem> pis = paperService.findPaperItemByPaperId(pa.getPaperId());
				for(PaperItem pi : pis)
				{
					Question question = questionService.findQuestionById(pi.getQuestionId());
					if(question != null && question.getType() == 5)
					{
						QuestionItem item = new QuestionItem(question);
						item.setPaperAnswerId(pa.getId());
						item.setPaperItemId(pi.getId());
						item.setPaperItemType(pi.getType());
						item.setPaperId(pi.getPaperId());
						PaperAnswerItem pai = paperService.findPaperAnswerItem(item.getPaperItemId(), item.getPaperAnswerId());
						if(pai != null)
							pai.setAnswer(IMAGE_SERVER_URL+pai.getAnswer());
						item.setPaperAnswerItem(pai);
						items.add(item);
					}
				}
				
				model.addAttribute("items", items);
			}
		}
		return "paper-check";
	}
	
	@RequestMapping("/paper/check")
	@ResponseBody
	public EduResult paperCheck(@RequestParam(defaultValue="0") long paperAnswerId)
	{
		if(paperAnswerId != 0)
		{
			PaperAnswer pa = paperService.findPaperAnswerById(paperAnswerId);
			pa.setCheckState(2);
			paperService.updatePaperAnswer(pa);
		}
		return EduResult.ok("", null);
	}
	
	@RequestMapping("/question/tosearch")
	public String tosearch()
	{
		return "search";
	}
	
	@RequestMapping("/question/search")
	@ResponseBody
	public List<SearchItem> search(String keyword)
	{
		List<SearchItem> list = searchService.searchQuestion(keyword, 1, 10);
		return list;
	}
	
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
		question.setCreated(new Date());
		question.setUpdated(new Date());
		questionService.addQuestion(question);
		return EduResult.ok(null, null);
	}
	
	@RequestMapping("/student/tolist")
	public String tostudent()
	{
		return "student-list";
	}
	
	@RequestMapping("/student/list")
	@ResponseBody
	public EasyUIDataGridResult studentList(@RequestParam(defaultValue="0") long teacherId,@RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="5")int rows,HttpServletRequest request)
	{
		List<Tclass> clss = tclassService.findTclassByTeacherId(teacherId);
		List<Long> clsids = new ArrayList<>();
		for(Tclass c : clss)
		{
			clsids.add(c.getId());
		}
		EasyUIDataGridResult res = studentService.findStudentTclassListByTclassIds(clsids, page, rows);
		List<StudentTclass> sts = (List<StudentTclass>) res.getRows();
		Teacher teacher = teacherService.findTeacherById(teacherId);
		
		List<StudentData> list = new ArrayList<>();
		for(StudentTclass st : sts)
		{
			StudentData result = new StudentData(studentService.findStudentById(st.getStudentId()));
			result.setStudentTclassId(st.getId());
			Tclass tclass = null;
			for(Tclass c : clss)
			{
				if((long)c.getId() == (long)st.getTclassId())
				{
					tclass = c;
					break;
				}
			}
			result.setTclass(tclass.getName());
			result.setSubject(subjectService.findSubject(teacher.getSubjectId()).getName());
			list.add(result);
		}
		res.setRows(list);
		res.setTotal(list.size());
		
		return res;
	}
	
	@RequestMapping("/student/delete")
	@ResponseBody
	public EduResult deleteStudent(String ids)
	{
		String[] idss = ids.split(",");
		for(String sid : idss)
		{
			long id = Long.parseLong(sid);
			studentService.deleteStudentTclass(id);
		}
		return EduResult.ok("", null);
	}
}
