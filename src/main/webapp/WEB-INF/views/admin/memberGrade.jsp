<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/admin_header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>관리자게시판 - 상세보기</title>
    <link rel="stylesheet" href="/resources/css/notiview.css">
</head>
<body>
      
        <div class="container">
            <h1>회원관리</h1>
             <a href="../admin/memberGrade?member_number=${sessionScope.member_number}">전체회원</a>
             <a href="../admin/memberNormal?grade=0">일반회원</a>
             <a href="../admin/memberNormal?grade=0">신고회원</a>
             <a href="../admin/memberNormal?grade=2">경고회원</a>
             <a href="../admin/memberNormal?grade=4">블랙리스트회원</a>
        <table class="member-table">
    <thead>
        <tr>
            <th>ID</th>
            <th>회원가입일</th>
            <th>최근로그인</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="memberVO" items="${member}">
            <tr>
                <td><c:out value="${memberVO.id}"/></td>
                <td><fmt:formatDate value="${memberVO.regidate}" pattern="yyyy년 MM월 dd일 (E) HH:mm:ss" /></td>
                <td><fmt:formatDate value="${memberVO.update_date}" pattern="yyyy년 MM월 dd일 (E) HH:mm:ss" /></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
   
   </div>
</body>
</html>