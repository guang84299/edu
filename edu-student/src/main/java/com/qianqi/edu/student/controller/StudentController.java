package com.qianqi.edu.student.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import org.springframework.web.multipart.MultipartFile;

import com.qianqi.edu.common.FastDFSClient;
import com.qianqi.edu.pojo.Paper;
import com.qianqi.edu.pojo.PaperAnswer;
import com.qianqi.edu.pojo.PaperAnswerItem;
import com.qianqi.edu.pojo.PaperItem;
import com.qianqi.edu.pojo.Question;
import com.qianqi.edu.pojo.StaPaper;
import com.qianqi.edu.pojo.StaQuestion;
import com.qianqi.edu.pojo.Student;
import com.qianqi.edu.pojo.StudentTeacherSubject;
import com.qianqi.edu.pojo.Tclass;
import com.qianqi.edu.pojo.TeacherSubject;
import com.qianqi.edu.pojo.common.EasyUIDataGridResult;
import com.qianqi.edu.pojo.common.EduResult;
import com.qianqi.edu.pojo.common.PaperAnswerResult;
import com.qianqi.edu.pojo.common.PaperResult;
import com.qianqi.edu.pojo.common.QuestionItem;
import com.qianqi.edu.service.PaperService;
import com.qianqi.edu.service.QuestionService;
import com.qianqi.edu.service.SsoService;
import com.qianqi.edu.service.StaService;
import com.qianqi.edu.service.StudentService;
import com.qianqi.edu.service.SubjectService;
import com.qianqi.edu.service.TclassService;
import com.qianqi.edu.service.TeacherService;

@Controller
public class StudentController {
	@Autowired
	private StudentService studentService;
	@Autowired
	private TeacherService teacherService;
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
	@Autowired
	private StaService staService;
	
	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;
	
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
	public EduResult addStudentTclass(@RequestBody StudentTeacherSubject studentTeacherSubject)
	{
		TeacherSubject ts = teacherService.findTeacherSubject(studentTeacherSubject.getTeacherSubjectId());
		if(ts == null)
		{
			return EduResult.err("代码不存在！", null);
		}
		studentTeacherSubject.setCreated(new Date());
		studentTeacherSubject.setUpdated(new Date());
		int count = studentService.countStudentTeacherSubjectByTeacherSubjectIdAndStudentId(studentTeacherSubject.getTeacherSubjectId(),studentTeacherSubject.getStudentId());
		if(count<=0)
			studentService.addStudentTeacherSubject(studentTeacherSubject);
		else
		{
			return EduResult.err("已经添加！", null);
		}
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
		EasyUIDataGridResult result = studentService.findStudentTeacherSubjectListByStudentId(studentId, page, rows);
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
			studentService.deleteStudentTeacherSubject(id);
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
			pa = paperService.findPaperAnswerByStudentIdAndPaperIdAndDifficult(student.getId(), paperId,student.getDifficult());
		}
		else
		{
			List<PaperAnswer> list = paperService.findPaperAnswerByStudentIdAndStateAndDifficult(student.getId(), 100,student.getDifficult());
			if(list != null && list.size()>0)
			{
				pa = list.get(0);
			}
		}
		
		if(pa != null)
		{
			List<QuestionItem> items = new ArrayList<>();
			List<PaperItem> pis = paperService.findPaperItemByPaperIdAndType(pa.getPaperId(),student.getDifficult());
			for(PaperItem pi : pis)
			{
				Question question = questionService.findQuestionById(pi.getQuestionId());
				if(question != null)
				{
					QuestionItem item = new QuestionItem(question);
					item.setPaperAnswerId(pa.getId());
					item.setPaperItemId(pi.getId());
					item.setPaperItemType(pi.getType());
					item.setPaperId(pi.getPaperId());
					PaperAnswerItem pai = paperService.findPaperAnswerItem(item.getPaperItemId(), item.getPaperAnswerId());
					if(question.getType() == 5 && pai != null)
						pai.setAnswer(IMAGE_SERVER_URL+pai.getAnswer());
					item.setPaperAnswerItem(pai);
					items.add(item);
				}
			}
			//按题库类型重新排序
			Collections.sort(items,new Comparator<QuestionItem>(){
	            public int compare(QuestionItem arg0, QuestionItem arg1) {
	                return arg0.getType().compareTo(arg1.getType());
	            }
	        });
			model.addAttribute("items", items);
		}
		return "answer";
	}
	
	@RequestMapping("/paper/toanswer2")
	@ResponseBody
	public List<QuestionItem> toPaperAnswer2(Model model,HttpServletRequest request)
	{
		Student student = (Student) request.getAttribute("student");
		String paperIdStr =  request.getParameter("paperId");
		PaperAnswer pa = null;
		if(paperIdStr != null)
		{
			long paperId = Long.parseLong(paperIdStr);
			pa = paperService.findPaperAnswerByStudentIdAndPaperIdAndDifficult(student.getId(), paperId,student.getDifficult());
		}
		else
		{
			List<PaperAnswer> list = paperService.findPaperAnswerByStudentIdAndStateAndDifficult(student.getId(), 100,student.getDifficult());
			if(list != null && list.size()>0)
			{
				pa = list.get(0);
			}
		}
		List<QuestionItem> items = new ArrayList<>();
		if(pa != null)
		{
			List<PaperItem> pis = paperService.findPaperItemByPaperIdAndType(pa.getPaperId(),student.getDifficult());
			for(PaperItem pi : pis)
			{
				Question question = questionService.findQuestionById(pi.getQuestionId());
				if(question != null)
				{
					QuestionItem item = new QuestionItem(question);
					item.setPaperAnswerId(pa.getId());
					item.setPaperItemId(pi.getId());
					item.setPaperItemType(pi.getType());
					item.setPaperId(pi.getPaperId());
					PaperAnswerItem pai = paperService.findPaperAnswerItem(item.getPaperItemId(), item.getPaperAnswerId());
					if(question.getType() == 5 && pai != null)
						pai.setAnswer(IMAGE_SERVER_URL+pai.getAnswer());
					item.setPaperAnswerItem(pai);
					items.add(item);
				}
			}
			//按题库类型重新排序
			Collections.sort(items,new Comparator<QuestionItem>(){
	            public int compare(QuestionItem arg0, QuestionItem arg1) {
	                return arg0.getType().compareTo(arg1.getType());
	            }
	        });
		}
		return items;
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
				if(item.getAnswer().contains(IMAGE_SERVER_URL))
				{
					String answer = item.getAnswer().replace(IMAGE_SERVER_URL, "");
					item.setAnswer(answer);
				}
				
				PaperAnswerItem paperAnswerItem = paperService.findPaperAnswerItem(item.getPaperItemId(), item.getPaperAnswerId());
				PaperItem paperItem = paperService.findPaperItemById(item.getPaperItemId());
				Question question = questionService.findQuestionById(paperItem.getQuestionId());
				
				if(question.getType() == 1 || question.getType() == 2)
				{
					if(item.getAnswer() != null && item.getAnswer().equals(question.getAnswer()))
						item.setAnswerResult(1);	
					else
						item.setAnswerResult(0);	
				}
				else if(question.getType() == 3)
				{
					int b = 1;
					if(item.getAnswer() != null)
					{
						String[] rs = item.getAnswer().split(",");
						String[] rs2 = question.getAnswer().split(",");
						if(rs2.length == rs.length)
						{
							for(int i=0;i<rs.length;i++)
							{
								if(!question.getAnswer().contains(rs[i]))
									b = 0;
							}
						}
						else
							b = 0;
					}
					else
						b = 0;
					item.setAnswerResult(b);	
				}
				else
				{
					item.setAnswerResult(0);	
				}
				
				if(paperAnswerItem == null)
				{
					item.setCreated(new Date());
					paperService.addPaperAnswerItem(item);
				}
				else
				{
					paperAnswerItem.setAnswerTime(paperAnswerItem.getAnswerTime()+item.getAnswerTime());
					paperAnswerItem.setAnswer(item.getAnswer());
					paperAnswerItem.setAnswerResult(item.getAnswerResult());
					paperService.updatePaperAnswerItem(paperAnswerItem);
				}
			}
			if(paperId != 0)
			{
				Student student = (Student) request.getAttribute("student");
				
				int num = paperService.findPaperItemNumByPaperIdAndType(paperId,student.getDifficult());
				float size = items.size();
				int state = (int) (size/num*100);
				
				PaperAnswer pa = paperService.findPaperAnswerByStudentIdAndPaperIdAndDifficult(student.getId(), paperId,student.getDifficult());
				if(pa != null)
				{
					pa.setState(state);
					if(state == 100)
					{
						pa.setSubmitTime(new Date());
						pa.setSubmitState(1);
					}
					paperService.updatePaperAnswer(pa);
					if(state == 100)
					{
						uploadSta(paperId,student,pa);
					}
				}
			}
			
		}
		return EduResult.ok("", null);
	}
	
	public void uploadSta(long paperId,Student student,PaperAnswer paperAnswer)
	{
		Paper paper = paperService.findPaperById(paperId);
		TeacherSubject teacherSubject = teacherService.findTeacherSubject(paper.getTeacherSubjectId());
		
		int schoolId = teacherSubject.getSchoolId();
		int gradeId = teacherSubject.getGradeId();
		int subjectId = teacherSubject.getSubjectId();
		long tclassId = teacherSubject.getTclassId();
		long teacherId = teacherSubject.getTeacherId();
		long studentId = student.getId();
		int difficult = paperAnswer.getDifficult();
		long predictTime = 0;
		long actualTime = 0;
		int checkState = 0;
		long checkTime = 0;
		long answerTime = 0;
		int starLevel = paperAnswer.getsEvaluate();
		Date created = new Date();
		
		//是否包含主观题
		boolean zhuguan = false;
		
		List<PaperItem> pis = paperService.findPaperItemByPaperIdAndType(paperId,student.getDifficult());
		for(PaperItem pi : pis)
		{
			Question question = questionService.findQuestionById(pi.getQuestionId());
			if(question != null)
			{
				predictTime += question.getNormalTime()*1000;
			}
			PaperAnswerItem paperAnswerItem = paperService.findPaperAnswerItem(pi.getId(), paperAnswer.getId());
			actualTime += paperAnswerItem.getAnswerTime();
			
			long questionId = question.getId();
			int q_difficult = question.getDifficult();
			long knowledgeId = question.getKnowledgeId();
			long q_answerTime =  paperAnswerItem.getAnswerTime();
			int answerResult = paperAnswerItem.getAnswerResult();
			int objective = 1;
			if(question.getType().intValue() == 1 || question.getType().intValue() == 2 || question.getType().intValue() == 3)
				objective = 0;
			
			StaQuestion staQuestion = new StaQuestion();
			staQuestion.setPaperId(paperId);
			staQuestion.setSchoolId(schoolId);
			staQuestion.setGradeId(gradeId);
			staQuestion.setSubjectId(subjectId);
			staQuestion.setTclassId(tclassId);
			staQuestion.setTeacherId(teacherId);
			staQuestion.setStudentId(studentId);
			staQuestion.setQuestionId(questionId);
			staQuestion.setDifficult(q_difficult);
			staQuestion.setKnowledgeId(knowledgeId);
			staQuestion.setAnswerTime(q_answerTime);
			staQuestion.setAnswerResult(answerResult);
			staQuestion.setObjective(objective);
			staQuestion.setCreated(created);
			
			staService.addStaQuestion(staQuestion);
			if(objective == 1)
				zhuguan = true;
		}
		answerTime = actualTime;
		
		//如果不包含主观题，直接批改完成
		if(!zhuguan)
		{
			checkState = 100;
		}
		
		StaPaper staPaper = new StaPaper();
		staPaper.setPaperId(paperId);
		staPaper.setSchoolId(schoolId);
		staPaper.setGradeId(gradeId);
		staPaper.setSubjectId(subjectId);
		staPaper.setTclassId(tclassId);
		staPaper.setTeacherId(teacherId);
		staPaper.setStudentId(studentId);
		staPaper.setDifficult(difficult);
		staPaper.setPredictTime(predictTime);
		staPaper.setActualTime(actualTime);
		staPaper.setCheckState(checkState);
		staPaper.setCheckTime(checkTime);
		staPaper.setAnswerTime(answerTime);
		staPaper.setStarLevel(starLevel);
		staPaper.setInobjective(zhuguan ? 1 : 0);
		staPaper.setCreated(created);
		
		staService.addStaPaper(staPaper);
	}
	
	@RequestMapping("/paper/answer/upload")
	@ResponseBody
    public EduResult uploadFile(MultipartFile file) 
    {
		try {
			//把图片上传的图片服务器
			FastDFSClient fastDFSClient = new FastDFSClient("classpath:conf/client.conf");
			//取文件扩展名
			String originalFilename = file.getOriginalFilename();
			String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
			//得到一个图片的地址和文件名
			String url = fastDFSClient.uploadFile(file.getBytes(), extName);
			//补充为完整的url
			url = IMAGE_SERVER_URL + url;
			return EduResult.ok(url, "");
		}
		catch (Exception e) {
			return EduResult.err(null, null);
		}
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
			TeacherSubject ts = teacherService.findTeacherSubject(paper.getTeacherSubjectId());
			answerResult.setTclass(tclassService.findTclass(ts.getTclassId()).getName());
			answerResult.setSubject(subjectService.findSubject(paper.getSubjectId()).getName());
			answerResult.setStateStr(pa.getState()+"%");
			if(pa.getCheckState() == 0)
				answerResult.setCheckStateStr("未批改");
			else if(pa.getCheckState() == 1)
				answerResult.setCheckStateStr("批改中");
			else if(pa.getCheckState() == 2)
				answerResult.setCheckStateStr("已批改");
			pasr.add(answerResult);
		}
		result.setRows(pasr);
		return result;
	}
	
	
	@RequestMapping("/paper/test")
	@ResponseBody
    public EduResult test(MultipartFile file) 
    {
		try {
			File f = new File("/Users/guang/Documents/work/abcd/test_images/test01.jpg");
			if(f.exists())
				f.delete();
			file.transferTo(f);
			if(f.exists())
			{
				f = new File("/Users/guang/Documents/work/abcd/output/test01.jpg");
				if(f.exists())
					f.delete();
				execPy();
				if(f.exists())
				{
					//把图片上传的图片服务器
					FastDFSClient fastDFSClient = new FastDFSClient("classpath:conf/client.conf");
					//得到一个图片的地址和文件名
					String url = fastDFSClient.uploadFile(f.getAbsolutePath());
					//补充为完整的url
					url = IMAGE_SERVER_URL + url;
					return EduResult.ok(url, "");
				}
			}
			return EduResult.err(null, null);
		}
		catch (Exception e) {
			return EduResult.err(null, null);
		}
    }
	
	
	
	public void execPy() {
        Process proc = null;
        try {
            proc = Runtime.getRuntime().exec("python " + "/Users/guang/Documents/work/abcd/object_detection/object_detection_runner.py");
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
