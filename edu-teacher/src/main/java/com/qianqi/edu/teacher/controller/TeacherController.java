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
import com.qianqi.edu.pojo.Knowledge;
import com.qianqi.edu.pojo.Paper;
import com.qianqi.edu.pojo.PaperAnswer;
import com.qianqi.edu.pojo.PaperAnswerItem;
import com.qianqi.edu.pojo.PaperItem;
import com.qianqi.edu.pojo.Question;
import com.qianqi.edu.pojo.School;
import com.qianqi.edu.pojo.Student;
import com.qianqi.edu.pojo.StudentTeacherSubject;
import com.qianqi.edu.pojo.Subject;
import com.qianqi.edu.pojo.Tclass;
import com.qianqi.edu.pojo.Teacher;
import com.qianqi.edu.pojo.TeacherSubject;
import com.qianqi.edu.pojo.common.EasyUIDataGridResult;
import com.qianqi.edu.pojo.common.EduResult;
import com.qianqi.edu.pojo.common.PaperAnswerResult;
import com.qianqi.edu.pojo.common.PaperResult;
import com.qianqi.edu.pojo.common.QuestionItem;
import com.qianqi.edu.pojo.common.QuestionResult;
import com.qianqi.edu.pojo.common.SearchItem;
import com.qianqi.edu.pojo.common.StudentData;
import com.qianqi.edu.pojo.common.TeacherSubjectData;
import com.qianqi.edu.service.GradeService;
import com.qianqi.edu.service.KnowledgeService;
import com.qianqi.edu.service.PaperService;
import com.qianqi.edu.service.QuestionService;
import com.qianqi.edu.service.SchoolService;
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
	@Autowired
	private SchoolService schoolService;
	@Autowired
	private  KnowledgeService knowledgeService;
	
	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;
	
	@RequestMapping("/toRegister")
	public String toRegister(Model model)
	{
		List<Grade> grades = gradeService.findGradeAll();
		List<Subject> subjects = subjectService.findSubjectAll();
		List<School> schools = schoolService.findSchoolAll();
		model.addAttribute("grades", grades);
		model.addAttribute("subjects", subjects);
		model.addAttribute("schools", schools);
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
	
	@RequestMapping("/tsubject/toadd")
	public String toAddTeacherSubject(Model model)
	{
		List<Grade> grades = gradeService.findGradeAll();
		List<Subject> subjects = subjectService.findSubjectAll();
		List<School> schools = schoolService.findSchoolAll();
		
		if(schools != null && schools.size()>0 && grades != null && grades.size()>0)
		{
			List<Tclass> tclasss = tclassService.findTclassBySchoolIdAndGradeId(schools.get(0).getId(), grades.get(0).getId());
			model.addAttribute("tclasss", tclasss);
		}
		
		
		model.addAttribute("grades", grades);
		model.addAttribute("subjects", subjects);
		model.addAttribute("schools", schools);
		
		return "tsubject-add";
	}
	
	@RequestMapping("/tsubject/getclass")
	@ResponseBody
	public List<Tclass> getClass(int schoolId,int gradeId)
	{
		List<Tclass> tclass = tclassService.findTclassBySchoolIdAndGradeId(schoolId, gradeId);
		return tclass;
	}
	
	@RequestMapping("/tsubject/add")
	@ResponseBody
	public EduResult addTeacherSubject(@RequestBody TeacherSubject teacherSubject)
	{
		teacherSubject.setCreated(new Date());
		teacherService.addTeacherSubject(teacherSubject);
		return EduResult.ok("", null);
	}
	
	@RequestMapping("/tsubject/tolist")
	public String toTeacherSubjectList()
	{
		return "tsubject-list";
	}
	
	@RequestMapping("/tsubject/list")
	@ResponseBody
	public EasyUIDataGridResult teacherSubjectList(@RequestParam(defaultValue="0") long teacherId, @RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="5")int rows)
	{
		EasyUIDataGridResult result = teacherService.findTeacherSubjectList(teacherId, page, rows);
		List<TeacherSubject> list = (List<TeacherSubject>) result.getRows();
		List<TeacherSubjectData> list2 = new ArrayList<>();
		for(TeacherSubject subject : list)
		{
			TeacherSubjectData subjectData = new TeacherSubjectData(subject);
			subjectData.setSchool(schoolService.findSchool(subject.getSchoolId()).getName());
			subjectData.setGrade(gradeService.findGrade(subject.getGradeId()).getName());
			subjectData.setTclass(tclassService.findTclass(subject.getTclassId()).getName());
			subjectData.setSubject(subjectService.findSubject(subject.getSubjectId()).getName());
			list2.add(subjectData);
		}
		result.setRows(list2);
		return result;
	}
	

	@RequestMapping("/tsubject/toedit")
	public String toEditTeacherSubject(Model model)
	{
		List<Grade> grades = gradeService.findGradeAll();
		List<Subject> subjects = subjectService.findSubjectAll();
		List<School> schools = schoolService.findSchoolAll();
		
		if(schools != null && schools.size()>0 && grades != null && grades.size()>0)
		{
			List<Tclass> tclasss = tclassService.findTclassBySchoolIdAndGradeId(schools.get(0).getId(), grades.get(0).getId());
			model.addAttribute("tclasss", tclasss);
		}
		
		
		model.addAttribute("grades", grades);
		model.addAttribute("subjects", subjects);
		model.addAttribute("schools", schools);
		
		return "tsubject-edit";
	}
	
	@RequestMapping("/tsubject/edit")
	@ResponseBody
	public EduResult editTeacherSubject(@RequestBody TeacherSubject teacherSubject)
	{
		teacherService.updateTeacherSubject(teacherSubject);
		return EduResult.ok("", null);
	}
	
	@RequestMapping("/tsubject/delete")
	@ResponseBody
	public EduResult deleteTeacherSubject(String ids)
	{
		String[] idss = ids.split(",");
		for(String sid : idss)
		{
			long id = Long.parseLong(sid);
			teacherService.deleteTeacherSubject(id);
		}
		return EduResult.ok("", null);
	}
	
	@RequestMapping("/paper/toadd")
	public String toAddPaper(Model model,HttpServletRequest request)
	{
		List<Subject> subjects = subjectService.findSubjectAll();
		model.addAttribute("subjects", subjects);
		Teacher teacher = (Teacher) request.getAttribute("teacher");
		List<TeacherSubject> teacherSubjects = teacherService.findTeacherSubjectByTeacherId(teacher.getId());
		List<TeacherSubjectData> teacherSubjects2 = new ArrayList<>();
		for(TeacherSubject subject : teacherSubjects)
		{
			TeacherSubjectData subjectData = new TeacherSubjectData(subject);
			subjectData.setSchool(schoolService.findSchool(subject.getSchoolId()).getName());
			subjectData.setGrade(gradeService.findGrade(subject.getGradeId()).getName());
			subjectData.setTclass(tclassService.findTclass(subject.getTclassId()).getName());
			subjectData.setSubject(subjectService.findSubject(subject.getSubjectId()).getName());
			teacherSubjects2.add(subjectData);
		}
		model.addAttribute("teacherSubjects", teacherSubjects2);
		
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
			TeacherSubject teacherSubject = teacherService.findTeacherSubject(paper.getTeacherSubjectId());
			paperResult.setTclass(tclassService.findTclass(teacherSubject.getTclassId()).getName());
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
		List<TeacherSubject> teacherSubjects = teacherService.findTeacherSubjectByTeacherId(teacher.getId());
		List<TeacherSubjectData> teacherSubjects2 = new ArrayList<>();
		for(TeacherSubject subject : teacherSubjects)
		{
			TeacherSubjectData subjectData = new TeacherSubjectData(subject);
			subjectData.setSchool(schoolService.findSchool(subject.getSchoolId()).getName());
			subjectData.setGrade(gradeService.findGrade(subject.getGradeId()).getName());
			subjectData.setTclass(tclassService.findTclass(subject.getTclassId()).getName());
			subjectData.setSubject(subjectService.findSubject(subject.getSubjectId()).getName());
			teacherSubjects2.add(subjectData);
		}
		model.addAttribute("teacherSubjects", teacherSubjects2);
		
		if(teacherSubjects != null && teacherSubjects.size()>0)
		{
			List<StudentTeacherSubject> sts = studentService.findStudentTeacherSubjectByTeacherSubjectId(teacherSubjects.get(0).getId());
			List<Long> ids = new ArrayList<>();
			for(StudentTeacherSubject st : sts)
			{
				ids.add(st.getStudentId());
			}
			List<Student> students = studentService.findStudentList(ids);
			model.addAttribute("students", students);
		}
		
		return "paper-edit";
	}
	
	@RequestMapping("/paper/getstudent")
	@ResponseBody
	public List<Student> getStudent(long teacherSubjectId)
	{
		List<StudentTeacherSubject> sts = studentService.findStudentTeacherSubjectByTeacherSubjectId(teacherSubjectId);
		List<Long> ids = new ArrayList<>();
		for(StudentTeacherSubject st : sts)
		{
			ids.add(st.getStudentId());
		}
		List<Student> list = studentService.findStudentList(ids);
		return list;
	}
	
	@RequestMapping("/paper/question/tolist")
	public String toQuestionList(@RequestParam(defaultValue="0") long paperId,@RequestParam(defaultValue="1") int type,Model model)
	{
		model.addAttribute("paperId", paperId);
		model.addAttribute("type", type);
		return "paper-question-list";
	}
	
	@RequestMapping("/paper/question/list")
	@ResponseBody
	public EasyUIDataGridResult questionList(@RequestParam(defaultValue="0") long paperId,@RequestParam(defaultValue="1") int type,@RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="5")int rows)
	{
		List<PaperItem> haslist = paperService.findPaperItemByPaperIdAndType(paperId,type);
		List<Long> ids = new ArrayList<>();
		for(PaperItem item : haslist)
		{
			ids.add(item.getQuestionId());
		}
		EasyUIDataGridResult result = questionService.findQuestionListByInIds(ids, page, rows);
		int page2 = page - ids.size()/rows;
		EasyUIDataGridResult result2 = null;
		if(page2>=0)
			result2 = questionService.findQuestionListByNotInIds(ids, page2, rows);
		
		
		List<Question> list = (List<Question>) result.getRows();
		List<QuestionResult> newlist = new ArrayList<>();
		for(Question question : list)
		{
			QuestionResult questionResult = new QuestionResult(question);
			questionResult.setCk(true);
			questionResult.setKnowledgePoint(knowledgeService.findKnowledge(question.getKnowledgeId()).getKnowledge());
			newlist.add(questionResult);
		}
		
		if(result2!=null)
		{
			list = (List<Question>) result2.getRows();
			for(Question question : list)
			{
				QuestionResult questionResult = new QuestionResult(question);
				questionResult.setCk(false);
				questionResult.setKnowledgePoint(knowledgeService.findKnowledge(question.getKnowledgeId()).getKnowledge());
				newlist.add(questionResult);
			}
		}
		result.setRows(newlist);
		result.setTotal(newlist.size());
		System.out.println("newlist="+newlist.size());
		return result;
	}
	
	@RequestMapping("/paper/question/save")
	@ResponseBody
	public EduResult questionSave(int type,long paperId,String ids)
	{
		List<PaperItem> list = paperService.findPaperItemByPaperIdAndType(paperId,type);
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
		List<StudentTeacherSubject> sts = null;
		if(StringUtils.isEmpty(paper.getStudentIds()))
		{
			sts = studentService.findStudentTeacherSubjectByTeacherSubjectId(paper.getTeacherSubjectId());
		}
		else
		{
			String[] ids = paper.getStudentIds().split(",");
			List<Long> sids = new ArrayList<>();
			for(String id : ids)
			{
				sids.add(Long.parseLong(id));
			}
			sts = studentService.findStudentTeacherSubjectByTeacherSubjectIdAndStudentIds(paper.getTeacherSubjectId(),sids);
		}
		for(StudentTeacherSubject st : sts)
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
	public EasyUIDataGridResult checkPaperList(@RequestParam(defaultValue="0") long teacherId,@RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="5")int rows,HttpServletRequest request)
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
			TeacherSubject teacherSubject = teacherService.findTeacherSubject(currpaper.getTeacherSubjectId());
			result.setTclass(tclassService.findTclass(teacherSubject.getTclassId()).getName());
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
			pa.setCheckTime(new Date());
			paperService.updatePaperAnswer(pa);
			
			List<PaperAnswer> pas = paperService.findPaperAnswerByPaperId(pa.getPaperId());
			boolean b = true;
			long time = 0;
			for(PaperAnswer p : pas)
			{
				if(p.getCheckState() != 2)
				{
					b = false;
					break;
				}
				time += (p.getCheckTime().getTime() - p.getSubmitTime().getTime());
			}
			if(b)
			{
				Paper paper = paperService.findPaperById(pa.getPaperId());
				paper.setCheckEvlTime(time/pas.size());
				paperService.updatePaper(paper);
			}
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
		List<TeacherSubject> teacherSubjects = teacherService.findTeacherSubjectByTeacherId(teacherId);
		List<Long> clsids = new ArrayList<>();
		for(TeacherSubject c : teacherSubjects)
		{
			clsids.add(c.getId());
		}
		EasyUIDataGridResult res = studentService.findStudentTeacherSubjectListByTeacherSubjectIds(clsids, page, rows);
		List<StudentTeacherSubject> sts = (List<StudentTeacherSubject>) res.getRows();
		Teacher teacher = teacherService.findTeacherById(teacherId);
		
		List<StudentData> list = new ArrayList<>();
		for(StudentTeacherSubject st : sts)
		{
			StudentData result = new StudentData(studentService.findStudentById(st.getStudentId()));
			result.setStudentTclassId(st.getId());
			TeacherSubject teacherSubject = null;
			for(TeacherSubject c : teacherSubjects)
			{
				if((long)c.getId() == (long)st.getTeacherSubjectId())
				{
					teacherSubject = c;
					break;
				}
			}
			Tclass tclass = tclassService.findTclass(teacherSubject.getTclassId());
			result.setTclass(tclass.getName());
			result.setSubject(subjectService.findSubject(teacherSubject.getSubjectId()).getName());
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
			studentService.deleteStudentTeacherSubject(id);
		}
		return EduResult.ok("", null);
	}
}
