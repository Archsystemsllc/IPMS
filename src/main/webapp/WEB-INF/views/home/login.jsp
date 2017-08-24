<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>


	<div class="wrap">
		<h2>IPMS</h2>
		<h4>Welcome to the login page</h4>
		<c:if test="${not empty error}">
		<div class="errorblock">
			Login failed, try again.<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>
	
		<form name='f' action="<c:url value='j_spring_security_check' />"
		method='POST'>
		<div class="login">
			<div class="email">
				<label for="user">Username</label><div class="email-input"><div class="input-prepend"><span class="add-on"><i class="icon-envelope"></i></span><input type="text" id="user" name="j_username"></div></div>
			</div>
			<div class="pw">
				<label for="pw">Password</label><div class="pw-input"><div class="input-prepend"><span class="add-on"><i class="icon-lock"></i></span><input type="password" id="pw" name="j_password"></div></div>
			</div>
			<div class="remember">
				<label class="checkbox">
					<input type="checkbox" value="1" name="remember"> Remember me on this computer
				</label>
			</div>
		</div>
		<div class="submit">
			<a href="#">Lost password?</a>
			<button class="btn btn-red5">Login</button>
		</div>
		</form>
	</div>
