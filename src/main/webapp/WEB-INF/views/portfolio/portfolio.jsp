<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div id="content">
	<div class="grid_container">
		<div class="grid_12">
			<div class="widget_wrap">
				<div class="widget_top">
					<span class="h_icon documents"></span>
					<h6>Application Portfolio Management</h6>
					<div class="c_actions"></div>
				</div>
				<div class="widget_content">
					<c:if test="${not empty success}">
						<div class="successblock">
							<spring:message code="${success}"></spring:message>
						</div>
					</c:if>
					<div class="content">
						<div class="grid_container">
							<div class="grid_12">
								<div class="widget_wrap">
									<div class="widget_content">
										<div class="widget_top">
											<span class="h_icon list_image"></span>
											<h6>Description</h6>
										</div>

										<div id="tabs" class="ui-tabs">
											<ul>
												<li><a href="#tab1">Application Information</a></li>
												<li><a href="#tab2">Budget Cost</a></li>
												<li><a href="#tab3">Strategic Impact</a></li>
												<li><a href="#tab4">Architectural Fit </a></li>

											</ul>
										</div>
									</div>
									<div id="tab1" class="ui-tabs-hide"></div>
									<div class="content">
										<div class="grid_container">
											<div class="grid_12 full_block">
												<div class="widget_wrap">
													<div class="widget_top">
														<span class="h_icon list_image"></span>
														<h6>Description</h6>
													</div>
													<form id="signupform" class="form_container left_label"
														action="#" method="get" autocomplete="off">
														<ul>
															<li>
																<div class="form_grid_12">
																	<label id="lfirstname" class="field_title"
																		for="firstname"> Name <span class="req">*</span>
																	</label>
																	<div class="form_input">
																		<input id="firstname" class="large" type="text"
																			maxlength="100" value="" name="firstname">
																	</div>
																</div>
															</li>
														</ul>
														<ul>
															<li>
																<div class="form_grid_12">
																	<label id="lfirstname" class="field_title"
																		for="firstname"> Application Id<span
																		class="req">*</span>
																	</label>
																	<div class="form_input">
																		<input id="firstname" class="large" type="text"
																			maxlength="100" value="" name="firstname">
																	</div>
																</div>
															</li>
														</ul>
														
														<ul>
															<li><label id="lfirstname" class="field_title"
																for="firstname" style="margin-left: 15px;"> Application Status <span
																	class="req">*</span>
															</label> <select id="selSingleDropdown" class="medium"
																name="state" id="state">
																	<option selected="selected">-- select --</option>
																	<option value="" selected="selected">Production</option>
																	<option value="AL">Development</option>
																	<option value="AK">Production</option>

															</select></li>
														</ul>
														
														<ul>
															<li><label id="lfirstname" class="field_title"
																for="firstname" style="margin-left: 15px;"> Application Type <span
																	class="req">*</span>
															</label> <select id="selSingleDropdown" class="medium"
																name="state" id="state">
																	<option selected="selected">-- select --</option>
																	<option value="" selected="selected">COTS</option>
																	<option value="AL">Development</option>
																	<option value="AK">Production</option>

															</select></li>
														</ul>
														

														<ul>
															<li>
																<div class="form_grid_12">
																	<label id="lfirstname" class="field_title"
																		for="firstname">Last Upgrade Year <span
																		class="req">*</span>
																	</label>
																	<div class="form_input">
																		<input id="firstname" class="large" type="text"
																			maxlength="100" value="" name="firstname">
																	</div>
																</div>
															</li>
														</ul>
														<ul>
															<li>
																<div class="form_grid_12">
																	<label class="field_title">Analysis Start Date</label>
																	<div class="form_input">
																		<div class=" form_grid_2 alpha">
																			<input id="dp1357752243176"
																				class="datepicker hasDatepicker" type="text"
																				name="filed30"> <span class=" label_intro">From</span>
																		</div>
																		<span class="clear"></span>
																	</div>
																</div>
															</li>
														</ul>
														<ul>
															<li>
																<div class="form_grid_12">
																	<label id="lfirstname" class="field_title"
																		for="firstname">Est. Lifespan <span
																		class="req">*</span>
																	</label>
																	<div class="form_input">
																		<input id="firstname" class="large" type="text"
																			maxlength="100" value="" name="firstname">
																	</div>
																</div>
															</li>
														</ul>
														<ul>
															<li>
																<div class="form_grid_12">
																	<label class="field_title">Retirement Date</label>
																	<div class="form_input">
																		<div class=" form_grid_2 alpha">
																			<input id="dp1357752243176"
																				class="datepicker hasDatepicker" type="text"
																				name="filed30"> <span class=" label_intro">From</span>
																		</div>
																		<span class="clear"></span>
																	</div>
																</div>
															</li>
														</ul>
													</form>
												</div>
											</div>
										</div>

									</div>

									<span class="clear"></span>
								</div>
							</div>

						</div>
					</div>
					<div class="content">
						<div class="grid_container">
							<div class="grid_12 full_block">
								<div class="widget_wrap">
									<div class="widget_top">
										<span class="h_icon list_image"></span>
										<h6>Transformation Decision</h6>
									</div>
									<ul>
										<li style="margin-left: 30px;"><label id="lfirstname" class="field_title"
											for="firstname"> 2006 <span class="req">*</span>
										</label> <select id="selSingleDropdown" class="medium" name="state"
											id="state">
												<option selected="selected">-- select --</option>
												<option value="" selected="selected">Maintain</option>
												<option value="AL">Maintain</option>
												<option value="AK">Enhance</option>

										</select></li>
									</ul>
									<ul>
										<li style="margin-left: 30px;"><label id="lfirstname" class="field_title"
											for="firstname"> 2007 <span class="req">*</span>
										</label> <select id="selSingleDropdown" class="medium" name="state"
											id="state">
												<option selected="selected">-- select --</option>
												<option value="" selected="selected">Enhance</option>
												<option value="AL">Maintain</option>
												<option value="AK">Enhance</option>

										</select></li>
									</ul>

									<ul>
										<li style="margin-left: 30px;"><label id="lfirstname" class="field_title"
											for="firstname"> 2008 <span class="req">*</span>
										</label> <select id="selSingleDropdown" class="medium" name="state"
											id="state">
												<option selected="selected">-- select --</option>
												<option value="" selected="selected">Enhance</option>
												<option value="AL">Maintain</option>
												<option value="AK">Enhance</option>

										</select></li>
									</ul>
									<ul>
										<li style="margin-left: 30px;"><label id="lfirstname" class="field_title"
											for="firstname"> 2009 <span class="req">*</span>
										</label> <select id="selSingleDropdown" class="medium" name="state"
											id="state">
												<option selected="selected">-- select --</option>
												<option value="" selected="selected">Enhance</option>
												<option value="AL">Maintain</option>
												<option value="AK">Enhance</option>

										</select></li>
									</ul>

									<ul >
										<li style="margin-left: 400px; margin-top: -185px;""><label id="lfirstname" class="field_title"
											for="firstname"> 2010 <span class="req">*</span>
										</label> <select id="selSingleDropdown" class="medium" name="state"
											id="state">
												<option selected="selected">-- select --</option>
												<option value="" selected="selected">Enhance</option>
												<option value="AL">Maintain</option>
												<option value="AK">Enhance</option>

										</select></li>
									</ul>
									<ul>
										<li style="margin-left: 400px;"><label id="lfirstname" class="field_title"
											for="firstname"> 2011 <span class="req">*</span>
										</label> <select id="selSingleDropdown" class="medium" name="state"
											id="state">
												<option selected="selected">-- select --</option>
												<option value="" selected="selected">Maintain</option>
												<option value="AL">Maintain</option>
												<option value="AK">Enhance</option>

										</select></li>
									</ul>

									<ul>
										<li style="margin-left: 400px;"><label id="lfirstname" class="field_title"
											for="firstname"> 2012 <span class="req">*</span>
										</label> <select id="selSingleDropdown" class="medium" name="state"
											id="state">
												<option selected="selected">-- select --</option>
												<option value="" selected="selected">Maintain</option>
												<option value="AL">Maintain</option>
												<option value="AK">Enhance</option>
										</select></li>
									</ul>
									<ul>
										<li style="margin-left: 400px;"><label id="lfirstname" class="field_title"
											for="firstname"> 2013 <span class="req">*</span>
										</label> <select id="selSingleDropdown" class="medium" name="state"
											id="state">
												<option selected="selected">-- select --</option>
												<option value="" selected="selected">Maintain</option>
												<option value="AL">Maintain</option>
												<option value="AK">Enhance</option>

										</select></li>
									</ul>

								</div>
							</div>
						</div>
					</div>
					<div class="content">
						<div class="grid_container">
							<div class="grid_12 full_block">
								<div class="widget_wrap">
									<div class="widget_top">
										<span class="h_icon list_image"></span>
										<h6>Release Notes</h6>
									</div>
									<ul>
										<li>
											<div class="form_grid_12">
												<label class="field_title"> <span
													class="label_intro"></span>
												</label>
												<div class="form_input">
													<textarea class="input_grow" tabindex="5" rows="5"
														cols="50" name="filed06"
														style="width: 511px; height: 71px; overflow: hidden;"></textarea>
												</div>
											</div>
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<div class="content">
						<div class="grid_container">
							<div class="grid_12 full_block">
								<div class="widget_wrap">
									<div class="widget_top">
										<span class="h_icon list_image"></span>
										<h6>Ownership</h6>
									</div>
									<form id="signupform" class="form_container left_label"
										action="#" method="get" autocomplete="off">
										<ul>
											<li>
												<div class="form_grid_12">
													<label id="lfirstname" class="field_title" for="firstname">
														Application Manager <span class="req">*</span>
													</label>
													<div class="form_input">
														<input id="firstname" class="large" type="text"
															maxlength="100" value="" name="firstname">
													</div>
												</div>
											</li>
										</ul>
										<ul>
											<li>
												<div class="form_grid_12">
													<label id="lfirstname" class="field_title" for="firstname">
														Contact Name<span class="req">*</span>
													</label>
													<div class="form_input">
														<input id="firstname" class="large" type="text"
															maxlength="100" value="" name="firstname">
													</div>
												</div>
											</li>
										</ul>
										<ul>
											<li>
												<div class="form_grid_12">
													<label id="lfirstname" class="field_title" for="firstname">Primary
														Vendor<span class="req">*</span>
													</label>
													<div class="form_input">
														<input id="firstname" class="large" type="text"
															maxlength="100" value="" name="firstname">
													</div>
												</div>
											</li>
										</ul>
										<ul>
											<li><label id="lfirstname" class="field_title"
												for="firstname" style="margin-left: 15px;">License Type<span class="req">*</span>
											</label> <select id="selSingleDropdown" class="medium" name="state"
												id="state">
													<option selected="selected">-- select --</option>
													<option value="" selected="selected">Enterprise</option>
													<option value="AL">Maintain</option>
													<option value="AK">Enhance</option>

											</select></li>
										</ul>
										<ul>
											<li>
												<div class="form_grid_12">
													<label id="lfirstname" class="field_title" for="firstname">No
														of Users<span class="req">*</span>
													</label>
													<div class="form_input">
														<input id="firstname" class="large" type="text"
															maxlength="100" value="" name="firstname">
													</div>
												</div>
											</li>
										</ul>
										<ul>
											<li>
												<div class="form_grid_12">
													<label id="lfirstname" class="field_title" for="firstname">Support
														FTE<span class="req">*</span>
													</label>
													<div class="form_input">
														<input id="firstname" class="large" type="text"
															maxlength="100" value="" name="firstname">
													</div>
												</div>
											</li>
										</ul>
										<ul>
											<li>
												<div class="form_grid_12">
													<label id="lfirstname" class="field_title" for="firstname">Email/Phone<span
														class="req">*</span>
													</label>
													<div class="form_input">
														<input id="firstname" class="large" type="text"
															maxlength="100" value="" name="firstname">
													</div>
												</div>
											</li>
										</ul>
										<ul>
											<li>
												<div class="form_grid_12">
													<label id="lfirstname" class="field_title" for="firstname">Related
														Vendors<span class="req">*</span>
													</label>
													<div class="form_input">
														<input id="firstname" class="large" type="text"
															maxlength="100" value="" name="firstname">
													</div>
												</div>
											</li>
										</ul>
										<ul>
											<li>
												<div class="form_grid_12">
													<label id="lfirstname" class="field_title" for="firstname">No
														of Licenses<span class="req">*</span>
													</label>
													<div class="form_input">
														<input id="firstname" class="large" type="text"
															maxlength="100" value="" name="firstname">
													</div>
												</div>
											</li>
										</ul>
										<ul>
											<li><label id="lfirstname" class="field_title"
												for="firstname" style="margin-left: 15px;">External Users<span class="req">*</span>
											</label> <select id="selSingleDropdown" class="medium" name="state"
												id="state">
													<option selected="selected">-- select --</option>
													<option value="" selected="selected">None</option>
													<option value="AL">Maintain</option>
													<option value="AK">Enhance</option>

											</select></li>
										</ul>
									</form>
								</div>
							</div>
						</div>
					</div>
					<div class="content">
						<div class="grid_container">
							<div class="grid_12 full_block">
								<div class="widget_wrap">
									<div class="widget_top">
										<span class="h_icon list_image"></span>
										<h6>Ownership</h6>
									</div>
									<form id="signupform" class="form_container left_label"
										action="#" method="get" autocomplete="off">
										<ul>
											<li><label id="lfirstname" class="field_title"
												for="firstname" style="margin-left: 15px;">Professional Layer<span class="req">*</span>
											</label> <select id="selSingleDropdown" class="medium" name="state"
												id="state">
													<option selected="selected">-- select --</option>
													<option value="" selected="selected">Object (old)</option>
													<option value="AL">Maintain</option>
													<option value="AK">Enhance</option>

											</select></li>
										</ul>
										<ul>
											<li><label id="lfirstname" class="field_title"
												for="firstname" style="margin-left: 15px;">Business Layer<span class="req">*</span>
											</label> <select id="selSingleDropdown" class="medium" name="state"
												id="state">
													<option selected="selected">-- select --</option>
													<option value="" selected="selected">Other
														(Distributed)</option>

													<option value="AL">Maintain</option>
													<option value="AK">Enhance</option>

											</select></li>
										</ul>
										<ul>
											<li><label id="lfirstname" class="field_title"
												for="firstname" style="margin-left: 15px;">DBMS<span class="req">*</span>
											</label> <select id="selSingleDropdown" class="medium" name="state"
												id="state">
													<option selected="selected">-- select --</option>
													<option value="" selected="selected">Server RDBMS</option>
													<option value="AL">Maintain</option>
													<option value="AK">Enhance</option>

											</select></li>
										</ul>
										<ul>
											<li><label id="lfirstname" class="field_title"
												for="firstname" style="margin-left: 15px;">Cleint O/S<span class="req">*</span>
											</label> <select id="selSingleDropdown" class="medium" name="state"
												id="state">
													<option selected="selected">-- select --</option>
													<option value="" selected="selected">No Longer
														Serviced</option>
													<option value="AL">Maintain</option>
													<option value="AK">Enhance</option>

											</select></li>
										</ul>
										<ul>
											<li><label id="lfirstname" class="field_title"
												for="firstname" style="margin-left: 15px;">Primary O/S<span class="req">*</span>
											</label> <select id="selSingleDropdown" class="medium" name="state"
												id="state">
													<option selected="selected">-- select --</option>
													<option value="" selected="selected">Non leading
														Service</option>
													<option value="AL">Maintain</option>
													<option value="AK">Enhance</option>

											</select></li>
										</ul>
										<ul>
											<li><label id="lfirstname" class="field_title"
												for="firstname" style="margin-left: 15px;">Transaction Mode<span class="req">*</span>
											</label> <select id="selSingleDropdown" class="medium" name="state"
												id="state">
													<option selected="selected">-- select --</option>
													<option value="" selected="selected">Real time</option>
													<option value="AL">Maintain</option>
													<option value="AK">Enhance</option>

											</select></li>
										</ul>
										<ul>
											<li><label id="lfirstname" class="field_title"
												for="firstname" style="margin-left: 15px;">Design Pattern<span class="req">*</span>
											</label> <select id="selSingleDropdown" class="medium" name="state"
												id="state">
													<option selected="selected">-- select --</option>
													<option value="" selected="selected">Distributed</option>
													<option value="AL">Maintain</option>
													<option value="AK">Enhance</option>

											</select></li>
										</ul>

									</form>
								</div>
							</div>
						</div>
					</div>
					<div class="content">
						<div class="grid_container">
							<div class="grid_12 full_block">
								<div class="widget_wrap">
									<div class="widget_top">
										<span class="h_icon list_image"></span>
										<h6>Transformation Assesment Attributes</h6>
									</div>
									<form id="signupform" class="form_container left_label"
										action="#" method="get" autocomplete="off">
										<ul>
											<li>
												<div class="form_grid_12">
													<label id="lfirstname" class="field_title" for="firstname">
														Business Value <span class="req">*</span>
													</label>
													<div class="form_input">
														<input id="firstname" class="large" type="text"
															maxlength="100" value="" name="firstname">
													</div>
												</div>
											</li>
										</ul>
										<ul>
											<li>
												<div class="form_grid_12">
													<label id="lfirstname" class="field_title" for="firstname">
														Architecture Fit Score<span class="req">*</span>
													</label>
													<div class="form_input">
														<input id="firstname" class="large" type="text"
															maxlength="100" value="" name="firstname">
													</div>
												</div>
											</li>
										</ul>
										<ul>
											<li>
												<div class="form_grid_12">
													<label id="lfirstname" class="field_title" for="firstname">
														Ap[plication List Score<span class="req">*</span>
													</label>
													<div class="form_input">
														<input id="firstname" class="large" type="text"
															maxlength="100" value="" name="firstname">
													</div>
												</div>
											</li>
										</ul>
										<ul>
											<li>
												<div class="form_grid_12">
													<label id="lfirstname" class="field_title" for="firstname">
														Performance Score<span class="req">*</span>
													</label>
													<div class="form_input">
														<input id="firstname" class="large" type="text"
															maxlength="100" value="" name="firstname">
													</div>
												</div>
											</li>
										</ul>
									</form>
								</div>
							</div>
						</div>
					</div>
					<div class="content">
						<div class="grid_container">
							<div class="grid_12 full_block">
								<div class="widget_wrap">
									<div class="widget_top">
										<span class="h_icon list_image"></span>
										<h6>Transformation Assesment Attributes</h6>
									</div>
									<form id="signupform" class="form_container left_label"
										action="#" method="get" autocomplete="off">
										<ul>
											<li>
												<div class="form_grid_12">
													<label id="lfirstname" class="field_title" for="firstname">
														Annual Cost <span class="req">*</span>
													</label>
													<div class="form_input">
														<input id="firstname" class="large" type="text"
															maxlength="100" value="" name="firstname">
														
													</div>
												</div>
											</li>
										</ul>
										<ul>
											<li>
												<div class="form_grid_12">
													<label id="lfirstname" class="field_title" for="firstname">
														Discretionary Projects<span class="req">*</span>
													</label>
													<div class="form_input">
														<input id="firstname" class="large" type="text"
															maxlength="100" value="" name="firstname">
													</div>
												</div>
											</li>
										</ul>
									</form>
								</div>
							</div>
						</div>
					</div>
					<div class="content">
						<div class="grid_container">
							<div class="grid_12 full_block">
								<div class="widget_wrap">
									<div class="widget_top">
										<span class="h_icon list_image"></span>
										<h6>Others</h6>
									</div>
									<ul>
										<li>
											<div class="form_grid_12">
												<label class="field_title"> Comment<span
													class="label_intro"></span>
												</label>
												<div class="form_input">
													<textarea class="input_grow" tabindex="5" rows="5"
														cols="50" name="filed06"
														style="width: 511px; height: 71px; overflow: hidden;"></textarea>
												</div>
											</div>
										</li>
									</ul>
									
									
									<ul>
										<li>
											<div class="form_grid_12">
												<label class="field_title">Temporary Use Text<span
													class="label_intro"></span>
												</label>
												<div class="form_input">
													<textarea class="input_grow" tabindex="5" rows="5"
														cols="50" name="filed06"
														style="width: 511px; height: 71px; overflow: hidden;"></textarea>
												</div>
											</div>
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>