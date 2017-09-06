<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<script type='text/javascript' src='https://www.google.com/jsapi'></script>
<script type='text/javascript'>
	<script src= "http://code.jquery.com/jquery-1.8.3.js">
</script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<script>

$(function(){
	$('select').on('change', function(){
		 var folderName = $("#folderNameSel").val();
		  $("#folderName").val(folderName);
		  $("table tbody").empty();
		
		  $("#fileList").css("display","none");
		  $("#fileUploadMsg").css("display","none");
		  $.ajax({
	            type: "GET",
	            url: "listFiles",
	            data:"folderName="+folderName,
	            success: function (data) {
	            	 $("table tbody").empty();
	             	 var tr = "";
	            	 for(var index=0; index<data.length; index++) {
	            		 tr = tr+"<tr><td>"+data[index].fileName+"</td><td><a href='"+data[index].fileUrl+"'>"+ data[index].fileUrl+"</a></td></tr>;"	 
	            	 }
	            	 if(data.length == 0){
	            		 $("#fileList").html("Selected SharePoint folder has no files to list"); 
	           		 	 $("#fileList").css("display","block");
	            	 }
	            	 $("table tbody").append(tr);
	               
	            }
	        });
	
	});
		
	$(function() {
	    var folderName = $("#folderName").val();
	    $("#folderNameSel").val(folderName);
	    $.ajax({
            type: "GET",
            url: "listFiles",
            data:"folderName="+folderName,
            success: function (data) {
            	 $("table tbody").empty();
             	 var tr = "";
            	 for(var index=0; index<data.length; index++) {
            		 tr = tr+"<tr><td>"+data[index].fileName+"</td><td><a href='"+data[index].fileUrl+"'>"+ data[index].fileUrl+"</a></td></tr>;"	 
            	 }
            	 if(data.length == 0){
            		 $("#fileList").html("Selected SharePoint has no files to list"); 
           		 	 $("#fileList").css("display","block");
            	 }
            	 $("table tbody").append(tr);
               
            }
        });
	});
	
	$('table.table tr').click(function () {
        window.location.href = $(this).data('url');
    });
	
	
	$('#uploadForm').submit(function() {
		var fileName = $("#fileData").val().split('\\').pop();
		if(fileName == ""){
			alert("Please select a file to upload!");
			return false;
		}else{
			var c = confirm("Do you want to upload file "+fileName+" ?");
		    return c; //you can just return c because it will be true or false
		}
	    
	});
	});
</script>
<div id="breadcrumb">
	<ul>
		<li><a href="${pageContext.request.contextPath}/app/groupdashboard">Home</a>
			<span> >> </span></li>
		<li><a href="${pageContext.request.contextPath}/app/sharePoint">SharePoint</a>
			<span> >> </span></li>
	
	</ul>
</div>
<div id="content">
	<div class="grid_container">
		<div class="grid_10">
			<div class="widget_wrap">
				<div class="widget_top">
				<span class="h_icon blocks_images"></span>
					<h6>SharePoint Document Management</h6>
					<div class="c_actions">
						
					</div>
				</div>
				<form:form modelAttribute="sharePointFile" class="form_container left_label"
                           action="#" method="get">
                    <div class="widget_content">
                  <c:if test="${not empty success}">
                        <div class="successblock" id="fileUploadMsg"><spring:message code="${success}"></spring:message>
                        </div>
                  </c:if>
                       <ul>                          
							  <li>
                                <div class="form_grid_12">
                                    <label for="fileData" class="field_title">Select Folder (*):</label>
                                    
                                    <div class="form_input">
                                        <form:select id="folderNameSel" path="folderName" items="${foldersList}" multiple="false" />
                                       <!--  <select name="folderNameSel" id="folderNameSel">
                                        <option value="-"> Select </option> 
										<option value="Root"> Root </option>
										<option value="Test"> Test </option>
										</select>-->
                                    </div>
                                </div>
                            </li>
                        </ul>
                        </div>     
					</form:form>
			</div>
		</div>
		<span class="clear"></span>
		<div class="grid_10">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon blocks_images"></span>
					<h6>Files</h6>
				</div>
				<div class="widget_content">
					<table class="display data_tbl" id="table12341">
						<thead>
							<tr>
								<th>File Name</th>
								<th>SharePoint Download Link (click to download)</th>
							</tr>
						</thead>
						<tbody>
							<div class="successblock" id="fileList" style="display:none"></div>
							<tr>
							</tr>
						</tbody>						
						<tfoot>							
							<tr style="height: 1px">
								<td colspan="10"><a class="activeobjectlink"></a></td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</div>
		<span class="clear"></span>
		<div class="grid_10">
            <div class="widget_wrap">
                <div class="widget_top">
                    <span class="h_icon blocks_images"></span>
                    <h6>Upload File</h6>
                </div>
                <form:form modelAttribute="sharePointFile" enctype="multipart/form-data" class="form_container left_label"
                           action="${pageContext.request.contextPath}/app/uploadFile" method="post" id="uploadForm">
                    <div class="widget_content">
                     
                    <form:input type="hidden" id="folderName" name="folderName" path="folderName" value="${selectedFolder}"/>
                       <ul>                          
							  <li>
                                <div class="form_grid_12">
                                    <label for="fileData" class="field_title">Select File</label>
                                    <div class="form_input">
                                        <form:input type="file" id="fileData" name="fileData" path="fileData" />
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="form_grid_12">
                                    <div class="form_input">
                                        <button type="submit" class="btn_small btn_blue"><span id="sub">Upload</span></button>
                                       
                                    </div>
                                </div>
                            </li>
                        </ul>
                        </div>     
					</form:form>						
                    </div>
                    </div>
                    
	</div>
</div>