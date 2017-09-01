/**
 * 
 */
package com.archsystemsinc.ipms.sec.persistence.service;

import java.util.List;

import com.archsystemsinc.ipms.sharepoint.SharePointFile;

/**
 * @author PrakashTotta
 *
 */
public interface ISharePointService {
	public String uploadFile(SharePointFile file) throws Exception;
	public List<SharePointFile> listFiles(String folderName) throws Exception;
	public String getFodlers();
}
