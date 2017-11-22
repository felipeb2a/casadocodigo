<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de produtos</title>
</head>
<body>
	<form method="post" action="/casadocodigo/produtos">
		<div>
			<label for="title">Titulo</label> <input type="text" name="title"
				id="title" />
		</div>
		<div>
			<label for="description">Descrição</label>
			<textarea rows="10" cols="20" name="description" id="description">
			</textarea>
		</div>
		<div>
			<label for="pages">Número de paginas</label> <input type="text"
				name="pages" id="pages" />
		</div>
		<c:forEach items="${types}" var="bookType" varStatus="status">
			<div>
				<label for="price_${bookType}">${bookType}</label> <input
					type="text" name="prices[${status.index}].value"
					id="price_${bookType}" /> <input type="hidden"
					name="prices[${status.index}].bookType" value="${bookType}" />
			</div>
		</c:forEach>

		<div>
			<div>
				<label for="price_EBOOK">EBOOK</label> <input type="text"
					name="prices[0].value" id="price_EBOOK" /> <input type="hidden"
					name="prices[0].bookType" value="EBOOK" />
			</div>
			<div>
				<label for="price_PRINTED">IMPRESSO</label> <input type="text"
					name="prices[1].value" id="price_PRINTED" /> <input type="hidden"
					name="prices[1].bookType" value="IMPRESSO" />
			</div>
			<div>
				<label for="price_COMBO">COMBO</label> <input type="text"
					name="prices[2].value" id="price_COMBO" /> <input type="hidden"
					name="prices[2].bookType" value="COMBO" />
			</div>
		</div>

		<div>
			<input type="submit" value="Enviar">
		</div>
	</form>
</body>
</html>