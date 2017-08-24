<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>

<div class="content">
	<div class="row-fluid">
		<div class="span8">
			<div class="box">
				<div class="box-head">
					<h3>Model Calendar</h3>
				</div>
				<div class="box-content">
				    <legend>Model Name: <c:out value='${Model.name}' /></legend>
					<div id='calendar'></div>
				</div>
			</div>
		</div>
		
		<div class="span4">
			<div class="box">
			   <div class="box-head">
					<h3>Model Info</h3>
				</div>
				<br><br>
			</div>
		</div>
	</div>
</div>