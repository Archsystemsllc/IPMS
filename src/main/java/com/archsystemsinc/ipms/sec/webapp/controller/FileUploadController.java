package com.archsystemsinc.ipms.sec.webapp.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.mpxj.MPXJException;
import net.sf.mpxj.ProjectFile;
import net.sf.mpxj.mpp.MPPReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.archsystemsinc.ipms.sec.model.Program;
import com.archsystemsinc.ipms.sec.model.Project;
import com.archsystemsinc.ipms.sec.model.Task;
import com.archsystemsinc.ipms.sec.persistence.service.IProgramService;
import com.archsystemsinc.ipms.sec.persistence.service.IProjectService;
import com.archsystemsinc.ipms.sec.persistence.service.ITaskService;

@Controller
@RequestMapping(value = "/upload")
public class FileUploadController {

	@Autowired
	private IProjectService projectService;

	@Autowired
	private IProgramService programService;
	
	@Autowired
	private ITaskService taskService;

	@RequestMapping(method = RequestMethod.GET)
	public String actionItem(final Model model) {
		model.addAttribute(new FileUpload());
		return "upload";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String create(final Model model, FileUpload uploadItem,
			final BindingResult result, final HttpServletRequest request) {
		//if (request.getParameter("btnAction") != null) {
			if (null!=request.getParameter("btnAction") && request.getParameter("btnAction").equalsIgnoreCase("Import")) {
				final HttpSession session = request.getSession(true);
				uploadItem = (FileUpload) session.getValue("uploadItem");
				Program program = null;
				if(null != request.getParameter("programId")){
					uploadItem.setProgramId(Long.valueOf(request.getParameter("programId")));
				}
				Project project = projectService.findByName(uploadItem.getName());
				if (project == null) {
					project = new Project();
					program = programService.findOne(uploadItem.getProgramId());
					project.setProgram(program);
				}
				project.setName(uploadItem.getName());
				project.setStartDate(uploadItem.getStartDate());
				project.setEndDate(uploadItem.getEndDate());
				
//				project.setProgram(null);
				String description = uploadItem.getDesc();
				if(description == null) description = uploadItem.getName() + " - MS Project File"; 
				project.setDescription(description);
				projectService.update(project);
				for (int i = 0; i < uploadItem.getTasks().size(); i++) {
					Task task = uploadItem.getTasks().get(i);
					List<Task> tasks = taskService.findByNameAndProject(task.getName(), project);
					if (null!=tasks && tasks.size()>0)
						task = tasks.get(0);
					else 
						task = new Task();
					task.setProject(project);
					task.setProgram(project.getProgram());
					task.setDateCreated(uploadItem.getTasks().get(i).getDateCreated());
					task.setDueDate(uploadItem.getTasks().get(i).getDueDate());
					task.setName(uploadItem.getTasks().get(i).getName());
					task.setDescription(uploadItem.getTasks().get(i).getDescription());
					task.setStatus(uploadItem.getTasks().get(i).getStatus());
					task.setMsProjectTaskId(uploadItem.getTasks().get(i).getMsProjectTaskId());
					task.setMsProjectParentTaskId(uploadItem.getTasks().get(i).getMsProjectParentTaskId());
					task.setMsProjectTaskMode(uploadItem.getTasks().get(i).getMsProjectTaskMode());
					taskService.update(task);
				}
				model.addAttribute("message", "MS Project file extracted successfully");
			} else {
				if (result.hasErrors()) {
					return "upload";
				}

				if (null != uploadItem.getFileData()) {
					uploadItem.setName(uploadItem.getFileData().getOriginalFilename());	
				}
				
				final MPPReader reader = new MPPReader();
				try {
					final ProjectFile projectFile = reader.read(uploadItem.getFileData().getInputStream());
					uploadItem.setStartDate(projectFile.getStartDate());
					uploadItem.setEndDate(projectFile.getFinishDate());
					final List<Task> tasks = new ArrayList<Task>();
					for (int i = 0; i < projectFile.getAllTasks().size(); i++) {
						final Task task = new Task();
						task.setName(projectFile.getAllTasks().get(i).getName());
						task.setDescription(projectFile.getAllTasks().get(i).getName());
						task.setDateCreated(projectFile.getAllTasks().get(i).getCreateDate());
						task.setDueDate(projectFile.getAllTasks().get(i).getDeadline());
						task.setStatus((projectFile.getAllTasks().get(i).getActive()?"Active":"Inactive"));
						task.setMsProjectTaskId(projectFile.getAllTasks().get(i).getID());
						if(null != projectFile.getAllTasks().get(i).getParentTask())
							task.setMsProjectParentTaskId(projectFile.getAllTasks().get(i).getParentTask().getID());
						task.setMsProjectTaskMode(projectFile.getAllTasks().get(i).getTaskMode().name());
						tasks.add(task);
					}
					uploadItem.setTasks(tasks);

					final HttpSession session = request.getSession(true);
					final List<Program> programlist = programService.findActivePrograms();
					final Map<Integer, String> prList = new LinkedHashMap<Integer, String>();
					for (int i = 0; i < programlist.size(); i++) {
						prList.put(programlist.get(i).getId().intValue(), programlist
								.get(i).getName());
					}
					uploadItem.setPrograms(prList);
					session.putValue("uploadItem", uploadItem);
					model.addAttribute("uploadItemVal", uploadItem);
					

				} catch (final MPXJException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (final IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		//}

		return "upload";
	}
}
