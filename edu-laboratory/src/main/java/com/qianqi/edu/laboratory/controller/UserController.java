package com.qianqi.edu.laboratory.controller;

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
import com.qianqi.edu.pojo.School;
import com.qianqi.edu.pojo.Subject;
import com.qianqi.edu.pojo.Tclass;
import com.qianqi.edu.pojo.Teacher;
import com.qianqi.edu.pojo.TeacherSubject;
import com.qianqi.edu.pojo.User;
import com.qianqi.edu.pojo.common.EasyUIDataGridResult;
import com.qianqi.edu.pojo.common.EduResult;
import com.qianqi.edu.pojo.common.TeacherData;
import com.qianqi.edu.service.GradeService;
import com.qianqi.edu.service.PaperService;
import com.qianqi.edu.service.SchoolService;
import com.qianqi.edu.service.SsoService;
import com.qianqi.edu.service.SubjectService;
import com.qianqi.edu.service.TclassService;
import com.qianqi.edu.service.TeacherService;
import com.qianqi.edu.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private SsoService ssoService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private SchoolService schoolService;
	@Autowired
	private GradeService gradeService;
	@Autowired
	private SubjectService subjectService;
	@Autowired
	private TclassService tclassService;
	@Autowired
	private PaperService paperService;
	
	@RequestMapping("/")
	public String showIndex()
	{
		return "index";
	}
	
	@RequestMapping("/toLogin")
	public String toLogin()
	{
		return "login";
	}
	
	@RequestMapping("/user/login")
	@ResponseBody
	public EduResult userLogin(String name,String password)
	{
		if(StringUtils.isEmpty(name) || StringUtils.isEmpty(name))
			return EduResult.err("null", null); 
		return ssoService.userLogin(name, password);
	}
	
	@RequestMapping("/user/loginout")
	@ResponseBody
	public EduResult userLoginOut(String token)
	{
		if(StringUtils.isEmpty(token))
			return EduResult.err("null", null); 
		return ssoService.userLoginOut(token);
	}
	
	@RequestMapping("/user/toadd")
	public String toAddUser()
	{
		return "user-add";
	}
	
	@RequestMapping("/user/add")
	@ResponseBody
	public EduResult addUser(@RequestBody User user)
	{
		user.setCreated(new Date());
		user.setUpdated(new Date());
		user.setStatus(0);
		userService.addUser(user);
		return EduResult.ok("", null);
	}
	
	@RequestMapping("/user/tolist")
	public String toUserList()
	{
		return "user-list";
	}
	
	@RequestMapping("/user/list")
	@ResponseBody
	public EasyUIDataGridResult userList(@RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="5")int rows)
	{
		EasyUIDataGridResult result = userService.findUserList(page, rows);
		return result;
	}
	
	@RequestMapping("/user/toedit")
	public String toEditUser()
	{
		return "user-edit";
	}
	
	@RequestMapping("/user/edit")
	@ResponseBody
	public EduResult editUser(@RequestBody User user)
	{
		user.setUpdated(new Date());
		userService.updateUser(user);
		return EduResult.ok("", null);
	}
	
	@RequestMapping("/user/delete")
	@ResponseBody
	public EduResult deleteUser(String ids)
	{
		String[] idss = ids.split(",");
		for(String sid : idss)
		{
			long id = Long.parseLong(sid);
			userService.deleteUser(id);
		}
		return EduResult.ok("", null);
	}
	
	@RequestMapping("/user/disable")
	@ResponseBody
	public EduResult disableUser(String ids)
	{
		String[] idss = ids.split(",");
		for(String sid : idss)
		{
			long id = Long.parseLong(sid);
			User u = userService.findUserById(id);
			u.setStatus(-1);
			u.setUpdated(new Date());
			userService.updateUser(u);
		}
		return EduResult.ok("", null);
	}
	
	@RequestMapping("/user/enable")
	@ResponseBody
	public EduResult enableUser(String ids)
	{
		String[] idss = ids.split(",");
		for(String sid : idss)
		{
			long id = Long.parseLong(sid);
			User u = userService.findUserById(id);
			u.setStatus(0);
			u.setUpdated(new Date());
			userService.updateUser(u);
		}
		return EduResult.ok("", null);
	}
	
	@RequestMapping("/teacher/tolist")
	public String toTeacherList(Model model)
	{
		List<School> schools = schoolService.findSchoolAll();
		List<Grade> grades = gradeService.findGradeAll();
		List<Subject> subjects = subjectService.findSubjectAll();
		model.addAttribute("grades", grades);
		model.addAttribute("subjects", subjects);
		model.addAttribute("schools", schools);
		
		return "teacher-list";
	}
	
	@RequestMapping("/teacher/list")
	@ResponseBody
	public EasyUIDataGridResult teacherList(HttpServletRequest request,@RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="5")int rows)
	{
		String subjectId = request.getParameter("subjectIds");
		String gradeId = request.getParameter("gradeIds");
		String schoolId = request.getParameter("schoolIds");
		
		List<Integer> subjectIds = new ArrayList<>();
		List<Integer> gradeIds = new ArrayList<>();
		List<Integer> schoolIds = new ArrayList<>();
		
		if(!StringUtils.isEmpty(subjectId) && !"0".equals(subjectId))
		{
			subjectIds.add(Integer.parseInt(subjectId));
		}
		if(!StringUtils.isEmpty(gradeId) && !"0".equals(gradeId))
		{
			gradeIds.add(Integer.parseInt(gradeId));
		}
		if(!StringUtils.isEmpty(schoolId) && !"0".equals(schoolId))
		{
			schoolIds.add(Integer.parseInt(schoolId));
		}
		
		
		EasyUIDataGridResult result = teacherService.findTeacherList(schoolIds,gradeIds,subjectIds,page, rows);
		List<Teacher> list = (List<Teacher>) result.getRows();
		List<TeacherData> list2 = new ArrayList<TeacherData>();
		
		for(Teacher t : list)
		{
			TeacherData td = new TeacherData(t);
			td.setSchool(schoolService.findSchool(t.getSchoolId()).getName());
			td.setGrade(gradeService.findGrade(t.getGradeId()).getName());
			td.setSubject(subjectService.findSubject(t.getSubjectId()).getName());
			
			List<TeacherSubject> tss = teacherService.findTeacherSubjectByTeacherId(t.getId());
			List<Long> ids = new ArrayList<Long>();
			for(TeacherSubject ts : tss)
			{
				ids.add(ts.getTclassId());
			}
			List<Tclass> tcs = tclassService.findTclassByIds(ids);
			String tclassStr = "";
			for(Tclass tc : tcs)
			{
				tclassStr += tc.getName() + "  ";
			}
			td.setTclass(tclassStr);
			
			
			
			String stateStr = "正常";
			if(t.getState() == 0)
				stateStr = "待审核";
			else if(t.getState() == 2)
				stateStr = "禁用";
			else if(t.getState() == 3)
				stateStr = "拒绝";
			
			td.setStateStr(stateStr);
			
			List<Paper> papers = paperService.findPaperByTeacherId(t.getId());
			ids = new ArrayList<Long>();
			for(Paper paper : papers)
			{
				ids.add(paper.getId());
			}
			int checkNum = paperService.countPaperAnswerByCheckState(ids, 2);
			int submitNum = paperService.countPaperAnswerBySubmitState(ids, 1);
			td.setPaperState(checkNum+"/"+submitNum);
			
			list2.add(td);
		}
		result.setRows(list2);
		return result;
	}
	
	@RequestMapping("/teacher/disable")
	@ResponseBody
	public EduResult disableTeacher(String ids)
	{
		String[] idss = ids.split(",");
		for(String sid : idss)
		{
			long id = Long.parseLong(sid);
			Teacher teacher = teacherService.findTeacherById(id);
			teacher.setState(2);
			teacher.setUpdated(new Date());
			teacherService.updateTeacher(teacher);
		}
		return EduResult.ok("", null);
	}
	
	@RequestMapping("/teacher/enable")
	@ResponseBody
	public EduResult enableTeacher(String ids)
	{
		String[] idss = ids.split(",");
		for(String sid : idss)
		{
			long id = Long.parseLong(sid);
			Teacher teacher = teacherService.findTeacherById(id);
			teacher.setState(1);
			teacher.setUpdated(new Date());
			teacherService.updateTeacher(teacher);
		}
		return EduResult.ok("", null);
	}
	
	@RequestMapping("/teacher/check")
	@ResponseBody
	public EduResult checkTeacher(String ids)
	{
		String[] idss = ids.split(",");
		for(String sid : idss)
		{
			long id = Long.parseLong(sid);
			Teacher teacher = teacherService.findTeacherById(id);
			teacher.setState(1);
			teacher.setUpdated(new Date());
			teacherService.updateTeacher(teacher);
		}
		return EduResult.ok("", null);
	}
	
	@RequestMapping("/teacher/refuse")
	@ResponseBody
	public EduResult refuseTeacher(String ids)
	{
		String[] idss = ids.split(",");
		for(String sid : idss)
		{
			long id = Long.parseLong(sid);
			Teacher teacher = teacherService.findTeacherById(id);
			teacher.setState(3);
			teacher.setUpdated(new Date());
			teacherService.updateTeacher(teacher);
		}
		return EduResult.ok("", null);
	}
}
