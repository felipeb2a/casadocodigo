<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de produtos</title>
</head>
<body>
	<spring:hasBindErrors name="product">
		<ul>
			<c:forEach var="error" items="${errors.allErrors}">
				<li>${error.code}-${error.field}</li>
			</c:forEach>
		</ul>
	</spring:hasBindErrors>
	<!--
	#mvcUrl
	A vantagem � que, caso voc� queira mudar a URL do seu controller,
	voc� n�o precisar� mudar os links da sua p�gina 
	
	#commandName
	ponto que precisa de mais aten��o na tag form � o atributo
	commandName. Ele tem o mesmo prop�sito do atributo name na tag
	hasBindErrors, ou seja, recebe o tipo do par�metro que est� sendo validado
	pelo nosso controller, com a primeira letra em min�sculo
	
	#enctype
	O atributo enctype serve justamente para indicarmos como queremos
	mandar os dados do navegador para o servidor e o multipart/form-data
	� o jeito utilizado quando necessitamos transmitir arquivos.
	-->
	<form:form action="${spring:mvcUrl('saveProduct').build()}"
		method="post" commandName="product" enctype="multipart/form-data">
		<div>
			<label for="title">Titulo</label>
			<form:input path="title" />
			<form:errors path="title" />
		</div>
		<div>
			<label for="description">Descri��o</label>
			<form:textarea path="description" rows="10" cols="20" />
			<form:errors path="description" />
		</div>
		<div>
			<label for="pages">N�mero de paginas</label>
			<form:input path="pages" />
			<form:errors path="pages" />
		</div>
		<div>
			<label for="releaseDate">Data de lan�amento</label>
			<!--Perceba que s� indicamos o nome da propriedade que deve ser usada e, pelo tipo da mesma, o Spring MVC vai decidir qual formatador utilizar -->
			<form:input path="releaseDate" type="date" />
			<form:errors path="releaseDate" />
		</div>
		<div>
			<label for="summary">Sumario do livro</label>
			<input type="file" name="summary" />
			<form:errors path="summaryPath" />
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
			<input type="submit" value="Enviar">
		</div>
	</form:form>
</body>
</html>