/**
 * 
 */
package com.archsystemsinc.ipms.sec.persistence.service.impl;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.archsystemsinc.ipms.sec.persistence.service.ISharePointService;
import com.archsystemsinc.ipms.sharepoint.SharePointFile;
import com.google.common.base.Joiner;

/**
 * @author PrakashTotta
 *
 */
@Service
@Transactional
public class SharePointServiceImpl implements ISharePointService{

	private final Log log = LogFactory.getLog(SharePointServiceImpl.class);

	static String ROOT = "https://archintranet.sharepoint.com";

	static String MS_LOGIN_URL = "https://login.microsoftonline.com/extSTS.srf";
	static String PQRS_FORMS_URL = ROOT + "/_forms/default.aspx?wa=wsignin1.0";
	static String PQRS_CTX_URL = ROOT + "/PQRS/_api/contextinfo";

	static String LIST = ROOT
			+ "/PQRS/_api/web/GetFolderByServerRelativeUrl('/PQRS/Shared%20Documents/IPMS<SELECTED_FOLDER>')/Files";
	
	static String UPLOAD = ROOT+"/PQRS/_api/web/GetFolderByServerRelativeUrl('/PQRS/Shared%20Documents/IPMS<SELECTED_FOLDER>')/Files/add(url='<FILE_NAME>',overwrite=true)";
	
	//static String userName = "ptotta@archsystemsinc.com";
	//static String password = "ndfs*123";
	
	@Value("${sharepoint.user.name}")
    String userName;
	@Value("${sharepoint.user.pwd}")
    String password;
	@Value("${sharepoint.ipms.folders}")
	String fodlers;
	
	/**
	 * 	
	 */
	public String uploadFile(SharePointFile file) throws Exception {
		String jsonData = "{'__metadata': { 'type': 'SP.List' }, 'AllowContentTypes': true,'BaseTemplate': 100, 'ContentTypesEnabled': true, 'Description': 'description', 'Title': '"+file.getFileName()+" ' }";
		String securityToken = receiveSecurityToken();
		List<String> cookies = getSignInCookies(securityToken);
		String formDigestValue = getFormDigestValue(cookies);
		LinkedMultiValueMap<String, Object> headers = new LinkedMultiValueMap<String, Object>();
		final String filename = file.getFileName();
		ByteArrayResource contentsAsResource = new ByteArrayResource(file.getFileData().getFileItem().get()){
            @Override
            public String getFilename(){
                return filename;
            }
        };
        headers.add("file", contentsAsResource);
		headers.add("body", jsonData);
		
		HttpHeaders httph = new HttpHeaders();
		httph.setContentType(MediaType.MULTIPART_FORM_DATA);
		httph.add("Content-type",file.getFileData().getContentType());
		httph.add("Content-type", "application/json;odata=verbose");
		httph.add("Cookie", Joiner.on(';').join(cookies));
		httph.add("X-RequestDigest", formDigestValue);
	
		String folderName = file.getFolderName();
		if("Root".equals(folderName)) {
			folderName = "";
		}else {
			folderName = "/"+folderName;
		}
		
		String path = UPLOAD.replace("<SELECTED_FOLDER>", folderName);
		path = path.replace("<FILE_NAME>", filename);
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<LinkedMultiValueMap<String, Object>>(
				headers, httph);
		ResponseEntity<String> responseEntity = restTemplate.exchange(new URI(path), HttpMethod.POST, requestEntity,
				String.class);

		return responseEntity.getBody();
	}

	public String getFodlers() {
		return fodlers;
	}

	public void setFodlers(String fodlers) {
		this.fodlers = fodlers;
	}

	/**
	 * 
	 */
	public List<SharePointFile> listFiles(String folderName) throws Exception {
		String securityToken = receiveSecurityToken();
		List<String> cookies = getSignInCookies(securityToken);
		String formDigestValue = getFormDigestValue(cookies);
		LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		HttpHeaders headers = new HttpHeaders();
		if("Root".equals(folderName)) {
			folderName = "";
		}else {
			folderName = "/"+folderName;
		}
		String path = LIST.replace("<SELECTED_FOLDER>", folderName);
		log.debug("Path:" + path);
		headers.add("Cookie", Joiner.on(';').join(cookies));
		headers.add("Content-type", "application/json;odata=verbose");
		headers.add("X-RequestDigest", formDigestValue);
		headers.add("Accept", "application/json;odata=verbose");
		HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<LinkedMultiValueMap<String, Object>>(
				map, headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.exchange(new URI(path), HttpMethod.GET, requestEntity,
				String.class);
		JSONObject json = new JSONObject(responseEntity.getBody());
		return parseFileListReponse(responseEntity.getBody());
	}

	private List<SharePointFile> parseFileListReponse(String jsonReponse) {
		List<SharePointFile> list = new ArrayList<SharePointFile>();
		try {
			JSONObject json = new JSONObject(jsonReponse);
			JSONArray files = json.getJSONObject("d").getJSONArray("results");
			JSONObject obj = null;
			String serverRelativeUrl = null;
			String fileName = null;
			SharePointFile spf = null;
			for (int index = 0; index < files.length(); index++) {
				obj = files.getJSONObject(index);
				serverRelativeUrl = (String) obj.get("ServerRelativeUrl");
				fileName = serverRelativeUrl.substring(serverRelativeUrl.lastIndexOf("/") + 1,
						serverRelativeUrl.length());

				spf = new SharePointFile();
				spf.setFileName(fileName);
				spf.setFileUrl(ROOT + serverRelativeUrl);

				list.add(spf);
			}
		} catch (Exception ex) {

		}

		return list;
	}
	
	private String receiveSecurityToken() throws URISyntaxException, TransformerFactoryConfigurationError,
			TransformerException, ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> requestEntity = new HttpEntity<String>(buildSecurityTokenRequestEnvelope(userName, password),
				headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.exchange(new URI(MS_LOGIN_URL), HttpMethod.POST,
				requestEntity, String.class);
		String resp = responseEntity.getBody();
		return getSecToken(resp);

	}

	private List<String> getSignInCookies(String securityToken) throws TransformerException, URISyntaxException {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> requestEntity = new HttpEntity<String>(securityToken, headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.exchange(new URI(PQRS_FORMS_URL), HttpMethod.POST,
				requestEntity, String.class);
		headers = responseEntity.getHeaders();
		List<String> cookies = headers.get("Set-Cookie");
		return cookies;
	}

	private String getFormDigestValue(List<String> cookies)
			throws IOException, URISyntaxException, TransformerException, JSONException {
		RestTemplate restTemplate = new RestTemplate();
		LinkedMultiValueMap<String, Object> headers = new LinkedMultiValueMap<String, Object>();
		HttpHeaders httph = new HttpHeaders();
		httph.add("Cookie", Joiner.on(';').join(cookies));
		httph.add("Accept", "application/json;odata=verbose");
		httph.add("X-ClientService-ClientTag", "SDK-JAVA");
		HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<LinkedMultiValueMap<String, Object>>(
				headers, httph);
		ResponseEntity<String> responseEntity = restTemplate.exchange(new URI(PQRS_CTX_URL), HttpMethod.POST,
				requestEntity, String.class);
		JSONObject json = new JSONObject(responseEntity.getBody());
		return json.getJSONObject("d").getJSONObject("GetContextWebInformation").getString("FormDigestValue");
	}

	/*public String uploadFile(SharePointFile file) throws Exception {
		String securityToken = receiveSecurityToken();
		List<String> cookies = getSignInCookies(securityToken);
		String formDigestValue = getFormDigestValue(cookies);
		LinkedMultiValueMap<String, Object> headers = new LinkedMultiValueMap<String, Object>();
		final String filenameFolder = "/temp/"+file.getFileData().getOriginalFilename();
		
		final String filename = file.getFileData().getOriginalFilename();
		
		log.debug("filenfilenameFolderme::"+filenameFolder);
		FileOutputStream fos = new FileOutputStream(filenameFolder);
		fos.write(file.getFileData().getBytes());
		fos.close();
        headers.add("file", new FileSystemResource(filenameFolder));
		HttpHeaders httph = new HttpHeaders();
		httph.setContentType(MediaType.MULTIPART_FORM_DATA);
		httph.add("Cookie", Joiner.on(';').join(cookies));
		httph.add("X-RequestDigest", formDigestValue);
		String folderName = file.getFolderName();
		if("Root".equals(folderName)) {
			folderName = "";
		}else {
			folderName = "/"+folderName;
		}
		
		String path = UPLOAD.replace("<SELECTED_FOLDER>", folderName);
		path = path.replace("<FILE_NAME>", filename);
		log.debug("path::"+path);
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<LinkedMultiValueMap<String, Object>>(
				headers, httph);
		ResponseEntity<String> responseEntity = restTemplate.exchange(new URI(path), HttpMethod.POST, requestEntity,
				String.class);
		//File f = new File(filenameFolder);
		//f.delete();
		return responseEntity.getBody();
	}*/

	

	private String getSecToken(String resp) {
		String secToken = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		try {
			Document doc = dbf.newDocumentBuilder().parse(new InputSource(new StringReader(resp)));
			XPath p = XPathFactory.newInstance().newXPath();
			p.setNamespaceContext(new NamespaceContext() {
				public Iterator getPrefixes(String namespaceURI) {
					return null;
				}

				public String getPrefix(String namespaceURI) {
					return null;
				}

				public String getNamespaceURI(String prefix) {
					if (prefix.equals("wst"))
						return "http://schemas.xmlsoap.org/ws/2005/02/trust";
					if (prefix.equals("wsse"))
						return "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";

					return null;
				}
			});

			Node secNode = (Node) p.evaluate(
					"//wst:RequestSecurityTokenResponse/wst:RequestedSecurityToken/wsse:BinarySecurityToken", doc,
					XPathConstants.NODE);
			secToken = secNode.getFirstChild().getNodeValue();

		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return secToken;
	}

	private static String buildSecurityTokenRequestEnvelope(String userName, String password) {
		String str = "<s:Envelope xmlns:s=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:a=\"http://www.w3.org/2005/08/addressing\">\r\n"
				+ "  <s:Header>\r\n"
				+ "    <a:Action s:mustUnderstand=\"1\">http://schemas.xmlsoap.org/ws/2005/02/trust/RST/Issue</a:Action> \r\n"
				+ "    <a:ReplyTo>\r\n"
				+ "      <a:Address>http://www.w3.org/2005/08/addressing/anonymous</a:Address>\r\n"
				+ "    </a:ReplyTo> \r\n"
				+ "    <a:To s:mustUnderstand=\"1\">https://login.microsoftonline.com/extSTS.srf</a:To>\r\n"
				+ "    <o:Security s:mustUnderstand=\"1\" xmlns:o=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\">\r\n"
				+ "      <o:UsernameToken>\r\n" + "        <o:Username>" + userName + "</o:Username>\r\n"
				+ "        <o:Password>" + password + "</o:Password>\r\n" + "      </o:UsernameToken>\r\n"
				+ "    </o:Security>\r\n" + "  </s:Header>\r\n" + "  <s:Body>\r\n"
				+ "    <t:RequestSecurityToken xmlns:t=\"http://schemas.xmlsoap.org/ws/2005/02/trust\">\r\n"
				+ "      <wsp:AppliesTo xmlns:wsp=\"http://schemas.xmlsoap.org/ws/2004/09/policy\">\r\n"
				+ "        <a:EndpointReference>\r\n"
				+ "          <a:Address><![CDATA[https://archintranet.sharepoint.com/PQRS]]></a:Address>\r\n"
				+ "        </a:EndpointReference>\r\n" + "      </wsp:AppliesTo> \r\n"
				+ "      <t:KeyType>http://schemas.xmlsoap.org/ws/2005/05/identity/NoProofKey</t:KeyType>\r\n"
				+ "      <t:RequestType>http://schemas.xmlsoap.org/ws/2005/02/trust/Issue</t:RequestType>\r\n"
				+ "      <t:TokenType>urn:oasis:names:tc:SAML:1.0:assertion</t:TokenType>\r\n"
				+ "    </t:RequestSecurityToken>\r\n" + "  </s:Body>\r\n" + "</s:Envelope>";
		return str;
	}

}
