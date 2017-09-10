<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div id="breadcrumb">
	<ul>
		<li><a href="${pageContext.request.contextPath}/app/groupdashboard">Home</a>
			<span> >> </span></li>
		<li><a href="${pageContext.request.contextPath}/app/privileges">Privileges</a>
			<span> >> </span></li>
		<li><a href="#" style="text-decoration: none;">Privilege</a></li>
	</ul>
</div>
<div class="content">
	<div class="grid_container">
		<div class="grid_12 full_block">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon list"></span>
					<h6>Privilege</h6>
				</div>
				<div class="widget_content">
				<form action="../edit-privilege/${privilege.id}" class="form_container left_label">
					<ul>
						<li>
							<div class="form_grid_12">
								<label class="field_title">ID</label>
								<div class="form_input">
									<span class="uneditable-input mid">${privilege.id }</span>
									<form:input type="hidden" id="id" name="id" path="id"></form:input>
								</div>
							</div>
						</li>
						<li>
							<div class="form_grid_12">
								<label for="type" class="field_title">Name</label>
								<div class="form_input">
									<span class="uneditable-input mid">${privilege.description}</span>
								</div>
							</div>
						</li>
						<li>
							<div class="form_grid_12">
								<label for="type" class="field_title">Resource</label>
								<div class="form_input">
									<span class="uneditable-input mid">${privilege.formattedName}</span>
								</div>
							</div>
						</li>
					</ul>
					<ul>
                            <li>
                                <div class="form_grid_12">
                                    <a href="../edit-privilege/${privilege.id}"><button class="btn_small btn_blue">Edit Privilege</button></a>
                                </div>
                            </li>
                        </ul>

					</form>
				</div>
			</div>
		</div>
	</div>
</div>