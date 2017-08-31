<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div class="switch_bar">
	<ul>
			<li><a href="${pageContext.request.contextPath}/app/organization-groups"><span
				class="stats_icon sitemap_sl"></span><span class="label">Group</span></a></li>
		<li><a href="${pageContext.request.contextPath}/app/programs"><span
				class="stats_icon graphic_design_sl"></span><span class="label">Vertical</span></a></li>
		<li><a href="${pageContext.request.contextPath}/app/models"><span
				class="stats_icon sign_in_sl"></span><span class="label">Models</span></a></li>
		<li><a href="${pageContext.request.contextPath}/app/projects"><span
				class="stats_icon statistics_sl"></span><span class="label">Finances</span></a></li>
		<li><a href="${pageContext.request.contextPath}/app/tasks"><span
				class="stats_icon finished_work_sl"></span><span class="label">Task
					List</span></a></li>
		<li><a href="${pageContext.request.contextPath}/app/meetings"><span
				class="stats_icon folder_sl c_stats_icon"></span><span class="label">Meetings</span></a></li>
		<li><a href="${pageContext.request.contextPath}/app/agenda"><span
				class="stats_icon agenda_sl"></span><span class="label">Agenda</span></a></li>
		<!--  <li><a href="${pageContext.request.contextPath}/app/meetingminutes"><span class="stats_icon full_time_sl"></span><span class="label">Minutes</span></a></li>-->
		<li><a href="${pageContext.request.contextPath}/app/actionitems"><span
				class="stats_icon actionitems_sl c_stats_icon"></span><span
				class="label">Action Items</span></a></li>
		<li><a
			href="${pageContext.request.contextPath}/app/issues"><span
				class="stats_icon issues_sl c_stats_icon"></span><span class="label">Issues</span></a></li>
		<li><a href="${pageContext.request.contextPath}/app/groupreport"><span
				class="stats_icon settings_sl"></span><span class="label">Reports</span></a></li>
		<li>
		<a href="${pageContext.request.contextPath}/app/upload"
			title="Import from MS Project"><span
				class="stats_icon agenda_sl"></span><span class="label">MS Project</span></a>				
				</li>
		<!-- 
        <li>
        <a href="${pageContext.request.contextPath}/app/new-programmeeting">
        <span class="stats_icon folder_sl c_stats_icon"></span>
         <span class="label">ScheduleMtg</span>
        </a>
       
        </li> -->

		<!-- <li><a href="${pageContext.request.contextPath}/app/lessonslearneds"><span class="stats_icon archives_sl"></span><span class="label">Lessons Lrnd</span></a></li>
        <li>
            <a href="${pageContext.request.contextPath}/app/projectreport"><span class="stats_icon current_work_sl"></span><span class="label">Analytics</span></a>
        </li> -->
		<!--
        <li class="dropdown"><a href="#" data-toggle="dropdown" class="dropdown-toggle"><span class="stats_icon user_sl"></span><span class="label"> Users</span></a>
            <div class="notification_list dropdown-menu blue_d">
                <div class="white_lin nlist_block">
                    <ul>
                        <li>
                            <div class="nlist_thumb">
                                <img src="${pageContext.request.contextPath}/resources/images/photo_60x60.jpg" width="40" height="40" alt="img">
                            </div>
                            <div class="list_inf">
                                <a href="#"></a>
                            </div>
                        </li>
                        <li>
                            <div class="nlist_thumb">
                                <img src="${pageContext.request.contextPath}/resources/images/photo_60x60.jpg" width="40" height="40" alt="img">
                            </div>
                            <div class="list_inf">
                                <a href="#"></a>
                            </div>
                        </li>
                        <li>
                            <div class="nlist_thumb">
                                <img src="${pageContext.request.contextPath}/resources/images/photo_60x60.jpg" width="40" height="40" alt="img">
                            </div>
                            <div class="list_inf">
                                <a href="#"></a>
                            </div>
                        </li>
                    </ul>
                    <span class="btn_24_blue"><a href="#">View All</a></span>
                </div>
            </div>
        </li>
        <li><a href="#"><span class="stats_icon lightbulb_sl"></span><span class="label">Support</span></a></li>-->
		<%-- <li><a href="${pageContext.request.contextPath}/app/contactus"><span class="stats_icon address_sl"></span><span class="label">Contact</span></a></li>
        <li><a href="#"><span class="stats_icon config_sl"></span><span class="label">Settings</span></a></li>--%>
		<%--<li><a href="#"><span class="stats_icon calendar_sl"><span class="alert_notify orange">30</span></span><span class="label">Activity Stream</span></a></li>--%>
		<%--<li><a href="#"><span class="stats_icon lightbulb_sl"></span><span class="label">Support</span></a></li>--%>
	</ul>
</div>