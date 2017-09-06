/**
 * 
 */
package com.archsystemsinc.ipms.sharepoint;

import java.io.Serializable;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * @author PrakashTotta
 *
 */
public class SharePointFile implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5284336937896618510L;

	private String fileName;
	
	private String fileUrl;

	private String folderName;
	
	private CommonsMultipartFile fileData;
	
	public CommonsMultipartFile getFileData() {
		return fileData;
	}


	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getFileUrl() {
		return fileUrl;
	}


	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}


	public String getFolderName() {
		return folderName;
	}


	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SharePointFile [fileName=");
		builder.append(fileName);
		builder.append(", fileUrl=");
		builder.append(fileUrl);
		builder.append(", folderName=");
		builder.append(folderName);
		builder.append(", fileData=");
		builder.append(fileData);
		builder.append("]");
		return builder.toString();
	}	
	
}
