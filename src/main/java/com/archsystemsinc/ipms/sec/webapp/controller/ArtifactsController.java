package com.archsystemsinc.ipms.sec.webapp.controller;

import com.archsystemsinc.ipms.sec.model.*;
import com.archsystemsinc.ipms.sec.persistence.service.*;
import com.archsystemsinc.ipms.sec.persistence.service.impl.TaskServiceImpl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;


@Controller
public class ArtifactsController {
    public ArtifactsController(){

    }
    private final Log log = LogFactory.getLog(ArtifactsController.class);

    @Autowired
    private IPrincipalService principalService;

    @Autowired
    private IIssueService issueService;

    @Autowired
    private IActionItemService actionItemService;

    @Autowired
    private ILessonsLearnedService learnedService;

    @Autowired
    private IRiskService riskService;
    
    @Autowired
    
    private ITaskService taskService;

    @RequestMapping(value = "/artifacts")
    public String artifacts(final Model model,
                                final java.security.Principal principal) {

        final Principal currentUser = principalService.findByName(principal
                .getName());

        final List<Issue> issues = issueService.findCurrentUserIssues(currentUser);
        model.addAttribute("issues", issues);

        final List<ActionItem> actionItems = actionItemService.findCurrentUserActionItems(currentUser);
        model.addAttribute("actionItems", actionItems);

        final List<LessonsLearned> lessonsLearned = learnedService.findAll();
        model.addAttribute("lessonsLearned", lessonsLearned);

        final List<Risk> risks = riskService.findAll();
        model.addAttribute("risks", risks);
        
        final List<Task> tasks = taskService.findAll();
        model.addAttribute("tasks", tasks);

        return "artifacts";
    }

    protected Map referenceData() {
        final Map referenceData = new HashMap();
        return referenceData;
    }

}
