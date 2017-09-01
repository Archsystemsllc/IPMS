/**
 * 
 */
package com.archsystemsinc.ipms.sec.webapp.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.archsystemsinc.ipms.sec.persistence.service.IPrincipalService;
import com.archsystemsinc.ipms.sec.persistence.service.ISharePointService;
import com.archsystemsinc.ipms.sharepoint.SharePointFile;


/**
 * @author PrakashTotta
 *
 */
@Controller
public class SharePointController {
	
	private final Log log = LogFactory.getLog(SharePointController.class);

	@Autowired
	private ISharePointService sharePointService;
	
	@Autowired
	private IPrincipalService principalService;
	
	/**
	 * 
	 * @param model
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/sharepoint", method = RequestMethod.GET)
	public String newProject(final Model model, java.security.Principal principal) {
		final com.archsystemsinc.ipms.sec.model.Principal currentUser = principalService.findByName(principal.getName());
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("sharePointFile", new SharePointFile());
		populateSharePointFolders(model);
		return "sharepoint";
	}

	private void populateSharePointFolders(final Model model) {
		try {
			model.addAttribute("foldersList", sharePointService.listFolders());
		} catch (Exception e) {
			log.error("Error occured while Listing Folders!",e);
			Map<String,String> results = new LinkedHashMap<String,String>();
			results.put("IPMS", "IPMS");
			model.addAttribute("foldersList",results);
		}
	}
	
	/**
	 * 
	 * @param folderName
	 * @return
	 */
	@RequestMapping(value = "/listFiles", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<SharePointFile> listFiles(final Model model,final @RequestParam("folderName") String folderName) {
		log.debug("--> listFiles::"+folderName);
		model.addAttribute("sharePointFile", new SharePointFile());
		List<SharePointFile> list = null;
		try {
			list = sharePointService.listFiles(folderName);
			if(list.isEmpty()) {
				model.addAttribute("success","success.sharepoint.list");
			}
			
		log.debug("File List:"+list);
		} catch (Exception e) {
			log.error("Error occured while Listing Files!",e);
			model.addAttribute("success","error.sharepoint.list");
		}
		log.debug("<-- listFiles");
		return list;
	}
	
	/**
	 * 
	 * @param model
	 * @param sharePointFile
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/uploadFile",method = RequestMethod.POST)
	public String uploadFile(final Model model, SharePointFile sharePointFile,
			final BindingResult result, final HttpServletRequest request,java.security.Principal principal) {
		sharePointFile.setFileName(sharePointFile.getFileData().getOriginalFilename());
		log.debug("--> uploadFile::"+sharePointFile);
		try {
			sharePointService.uploadFile(sharePointFile);
			model.addAttribute("success","success.sharepoint.upload");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error occured while Listing Files!",e);
			model.addAttribute("success","error.sharepoint.upload");
		}
		model.addAttribute("sharePointFile", sharePointFile);
		model.addAttribute("selectedFolder", sharePointFile.getFolderName());
		final com.archsystemsinc.ipms.sec.model.Principal currentUser = principalService.findByName(principal.getName());
		model.addAttribute("currentUser", currentUser);
		populateSharePointFolders(model);
		log.debug("<-- uploadFile");
		return "sharepoint";
	}
}
