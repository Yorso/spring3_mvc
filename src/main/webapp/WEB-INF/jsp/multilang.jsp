<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
	<body>
		<h1>
			<spring:message code="home.titleLang" />
		</h1>
		<p>
			<spring:message code="home.intro" />
		</p>
		<p>
			<a href="?lang=en">English</a> | <a href="?lang=es">Español</a>
		</p>
	</body>
</html>