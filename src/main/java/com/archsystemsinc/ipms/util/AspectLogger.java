package com.archsystemsinc.ipms.util;

import java.sql.Timestamp;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.archsystemsinc.ipms.sec.model.Principal;
import com.archsystemsinc.ipms.sec.model.Requirement;
import com.archsystemsinc.ipms.sec.model.RevisionHistory;
import com.archsystemsinc.ipms.sec.persistence.service.IPrincipalService;
import com.archsystemsinc.ipms.sec.persistence.service.IRequirementService;
import com.archsystemsinc.ipms.sec.persistence.service.IRevisionHistoryService;

@Aspect
public class AspectLogger {

	@Autowired
	private IRevisionHistoryService revisionHistoryService;

	@Autowired
	private IPrincipalService principalService;

	@Autowired
	private IRequirementService requirementService;

	private final Log log = LogFactory.getLog(this.getClass());

	public static HttpSession getSession() {
		final ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		return attr.getRequest().getSession(true); // true == allow create
	}

	@Before("execution(* com.archsystemsinc.ipms.sec.persistence.service.impl..*.*(..))")
	public void logRequirementBeforeChanges(final JoinPoint joinPoint)
			throws Throwable {
		log.info(">>>>>>>>>>>> ASPECT LOGGING <<<<<<<<<<<<<<<<<<<<<");
		final String className = joinPoint.getTarget().getClass().getName();
		final String method = joinPoint.getSignature().getName();
		if (className.contains("RequirementServiceImpl")) {
			if (method.contains("save")) {
				final String currentUser = SecurityContextHolder.getContext()
						.getAuthentication().getName();
				final Principal principal = principalService
						.findByName(currentUser);
				final java.util.Date date = new java.util.Date();
				final Timestamp timeStamp = new Timestamp(date.getTime());
				final Requirement requirement = (Requirement) joinPoint
						.getArgs()[0];

				RevisionHistory revisionHistory = new RevisionHistory();
				revisionHistory.setPrincipal(principal);
				revisionHistory.setRequirement(requirement);
				revisionHistory.setUpdatedAt(timeStamp);
				revisionHistory = revisionHistoryService
						.create(revisionHistory);
				final HttpSession session = getSession();
				session.setAttribute("revisionHistory", revisionHistory);
			}
		}
	}

	@After("execution(* com.archsystemsinc.ipms.sec.persistence.service.impl..*.*(..))")
	public void logRequirementAfterChanges(final JoinPoint joinPoint)
			throws Throwable {
		log.info(">>>>>>>>>>>> ASPECT LOGGING <<<<<<<<<<<<<<<<<<<<<");
		final String className = joinPoint.getTarget().getClass().getName();
		final String method = joinPoint.getSignature().getName();
		if (className.contains("RequirementServiceImpl")) {
			if (method.contains("save")) {
				final String currentUser = SecurityContextHolder.getContext()
						.getAuthentication().getName();
				final Principal principal = principalService
						.findByName(currentUser);
				final java.util.Date date = new java.util.Date();
				final Timestamp timeStamp = new Timestamp(date.getTime());
				Requirement newRequirement = (Requirement) joinPoint
						.getArgs()[0];

				final HttpSession session = getSession();
				final RevisionHistory revisionHistory = (RevisionHistory) session
						.getAttribute("revisionHistory");
				final Requirement oldRequirement = (Requirement) session
						.getAttribute("requirement");
				newRequirement = requirementService.findOne(oldRequirement
						.getId());
				String text = "";
				
				if (!oldRequirement.getDescription().equalsIgnoreCase(
						newRequirement.getDescription()))
					text += " Req Statement was changed from ["
							+ oldRequirement.getDescription() + "] to ["
							+ newRequirement.getDescription() + "]<br/>";

				if (oldRequirement.getPriority() != newRequirement
						.getPriority())
					text += " priority was changed from ["
							+ oldRequirement.getPriorityString() + "] to ["
							+ newRequirement.getPriorityString() + "]<br/>";

				if (oldRequirement.getType() != newRequirement.getType())
					text += " requirement type was changed from ["
							+ oldRequirement.getRequirementTypeString()
							+ "] to ["
							+ oldRequirement.getRequirementTypeString()
							+ "]<br/>";

				if (oldRequirement.getRiskLevel() != newRequirement
						.getRiskLevel())
					text += " risk level was changed from ["
							+ oldRequirement.getRiskLevelString() + "] to ["
							+ newRequirement.getRiskLevelString() + "]<br/>";

				if (!oldRequirement.getRiskDescription().equalsIgnoreCase(
						newRequirement.getRiskDescription()))
					text += " risk description was changed from ["
							+ oldRequirement.getRiskDescription() + "] to ["
							+ oldRequirement.getRiskDescription() + "]<br/>";

				revisionHistory.setText(text);
				revisionHistoryService.create(revisionHistory);
			}
		}
	}

}