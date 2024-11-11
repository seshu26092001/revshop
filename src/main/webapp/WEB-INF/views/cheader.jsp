<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Revshop</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link href="/css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="/css/menu.css" rel="stylesheet" type="text/css" media="all" />
<link href="/css/all.css" rel="stylesheet" type="text/css" />
<link href="/css/flexslider.css" rel='stylesheet' type='text/css' />
<link href="/css/style.css" rel="stylesheet" type="text/css" />
<link href="/css/pharmacy.css" rel="stylesheet" type="text/css" />
<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.js"></script>
<script src="/js/script.js" type="text/javascript"></script>
<link rel="icon" href="/images/logo.png" />
</head>
<body>
	<div class="container-fluid">
		<div class="header">
			<div class="header_top position-relative">
				<c:if test="${userid ne null }">
					<h5 class="position-absolute w-100 text-center" style="top: 20px;">Welcome
						! ${uname }</h5>
				</c:if>
				<div class="position-absolute w-100" style="top: 50px">
					<jsp:include page="msg.jsp"></jsp:include>
				</div>

				<div
					style="width: 100%; padding: 10px 0; display: flex; align-items: center;">
					<a href="/"
						style="font-size: 36px; font-weight: bold; color: #333; font-family: 'Georgia', serif; text-decoration: none; margin-left: 20px; letter-spacing: 1px; text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);">
						Revshop </a>
				</div>

				<div class="clear"></div>
			</div>
		</div>

		<div class="h_menu sticky-top">
			<a id="touch-menu" class="mobile-menu" href="#">Menu</a>
			<nav>
				<ul class="menu list-unstyled">
					<li><a href="/">Home</a></li>
					<li class="activate"><a href="#">Categories</a>
						<ul class="sub-menu list-unstyled sub-menu2">
							<c:forEach items="${cats }" var="cat">
								<li style="width: 190px;"><a href="/cats/${cat.catid}">
										${cat.catname}</a></li>
							</c:forEach>
						</ul></li>
					<c:choose>
						<c:when test="${ sessionScope.userid ne null }">
							<li class="float-right"><a href="/logout">Logout</a></li>
							<li class="float-right"><a href="/cchangepwd">Change
									Password</a></li>
							<li class="float-right"><a href="/cart">Cart <c:if
										test="${cqty > 0 }">
										<i class="badge badge-warning">${cqty}</i>
									</c:if>
							</a></li>
							<li class="float-right"><a href="/history">History</a></li>
							<li class="float-right"><a href="/helpdesk">Help Desk</a></li>



						</c:when>
						<c:otherwise>
							<li class="float-right"><a href="/login">Register</a></li>
						</c:otherwise>

					</c:choose>
					<div class="clear"></div>
				</ul>
			</nav>

		</div>