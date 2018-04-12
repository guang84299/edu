package com.qianqi.edu.laboratory.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qianqi.edu.pojo.Grade;
import com.qianqi.edu.pojo.School;
import com.qianqi.edu.pojo.StaPaper;
import com.qianqi.edu.pojo.Subject;
import com.qianqi.edu.pojo.Tclass;
import com.qianqi.edu.pojo.common.StaSubjectData;
import com.qianqi.edu.service.GradeService;
import com.qianqi.edu.service.KnowledgeService;
import com.qianqi.edu.service.SchoolService;
import com.qianqi.edu.service.StaService;
import com.qianqi.edu.service.SubjectService;
import com.qianqi.edu.service.TclassService;

@Controller
public class StaController {
	@Autowired
	private StaService staService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private GradeService gradeService;
	@Autowired
	private KnowledgeService knowledgeService;
	@Autowired
	private SchoolService schoolService;
	@Autowired
	private TclassService tclassService;
	
	@RequestMapping("/sta/tosubject")
	public String tosubject(Model model)
	{
		List<School> schools = schoolService.findSchoolAll();
		List<Grade> grades = gradeService.findGradeAll();
		List<Subject> subjects = subjectService.findSubjectAll();
		model.addAttribute("grades", grades);
		model.addAttribute("subjects", subjects);
		model.addAttribute("schools", schools);
		return "sta-subject";
	}
	

	@RequestMapping("/sta/gettclass")
	@ResponseBody
	public List<Tclass> gettclass(HttpServletRequest request)
	{
		String school = request.getParameter("schoolId");
		String grade = request.getParameter("gradeId");
		
		Integer schoolId = null;
		Integer gradeId = null;
		if(!StringUtils.isEmpty(school) && !"0".equals(school))
			schoolId = Integer.parseInt(school);
		if(!StringUtils.isEmpty(grade)  && !"0".equals(grade))
			gradeId = Integer.parseInt(grade);
		
		List<Tclass> list = new ArrayList<>();
		if(schoolId != null && gradeId != null)
		{
			list = tclassService.findTclassBySchoolIdAndGradeId(schoolId, gradeId);
		}
		return list;
	}
	
	@RequestMapping("/sta/subject")
	@ResponseBody
	public List<StaSubjectData> subject(HttpServletRequest request)
	{
		String school = request.getParameter("schoolId");
		String grade = request.getParameter("gradeId");
		String tclass = request.getParameter("tclassId");
//		String subject = request.getParameter("subject");
		String difficult1 = request.getParameter("difficult");
		String fromTime1 = request.getParameter("fromTime");
		String toTime1 = request.getParameter("toTime");
		
		Integer schoolId = null;
		Integer gradeId = null;
		Long tclassId = null;
//		Integer subjectId = null;
		Integer difficult = null;
		
		Date now = new Date();
		now.setDate(now.getDate()-1);
		long fromTime = now.getTime();
		long toTime = new Date().getTime();
		
		if(!StringUtils.isEmpty(school) && !"0".equals(school))
			schoolId = Integer.parseInt(school);
		if(!StringUtils.isEmpty(grade) && !"0".equals(grade))
			gradeId = Integer.parseInt(grade);
		if(!StringUtils.isEmpty(tclass) && !"0".equals(tclass))
			tclassId = Long.parseLong(tclass);
//		if(!StringUtils.isEmpty(subject))
//			subjectId = Integer.parseInt(subject);
		if(!StringUtils.isEmpty(difficult1) && !"0".equals(difficult1))
			difficult = Integer.parseInt(difficult1);
		if(!StringUtils.isEmpty(fromTime1))
			fromTime = Long.parseLong(fromTime1);
		if(!StringUtils.isEmpty(toTime1))
			toTime = Long.parseLong(toTime1);
				
		List<Subject> subjects = subjectService.findSubjectAll();
		List<StaSubjectData> ssds = new ArrayList<StaSubjectData>();
		for(Subject s : subjects)
		{
			List<StaPaper> staPapers = staService.findStaPapers(schoolId, gradeId, tclassId, s.getId(), difficult, fromTime, toTime);
			StaSubjectData ssd = new StaSubjectData();
			ssd.setSubject(s.getName());
			for(StaPaper sp : staPapers)
			{
				ssd.setPredictTime(ssd.getPredictTime()+sp.getPredictTime());
				ssd.setActualTime(ssd.getActualTime()+sp.getActualTime());
			}
			if(staPapers != null && staPapers.size()>0)
			{
				ssd.setPredictTime(ssd.getPredictTime()/staPapers.size());
				ssd.setActualTime(ssd.getActualTime()/staPapers.size());
			}
			ssds.add(ssd);
		}
		
		return ssds;
	}
	
	
	@RequestMapping("/sta/toknowledge")
	public String toknowledge()
	{
		return "sta-knowledge";
	}
	
	@RequestMapping("/sta/totclass")
	public String totclass()
	{
		return "sta-tclass";
	}
}
