<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/admin_header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>공지 수정 - 관리자 게시판</title>
    <link rel="stylesheet" href="/resources/css/notiview.css">
</head>
<body>
    <div class="container">
        <h1>공지 수정</h1>
        <form action="/admin/updateNotice" method="post" onsubmit="return confirmUpdate();">
            <input type="hidden" name="notice_number" value="${notice.notice_number}">
            <input type="hidden" name="writer" value="${sessionScope.member_number}">

            <div class="form-row">
                <label for="title">제목</label>
                <input type="text" id="title" name="title" class="form-control" value="${notice.title}" required>
            </div>

            <div class="form-row">
                <label for="content">내용</label>
                <textarea id="content" name="content" class="form-control" rows="10" required>${notice.content}</textarea>
            </div>

            <div class="form-actions">
                <button type="submit" class="btn" id="updateBtn">수정 완료</button>
                <button type="button" class="btn" onclick="window.history.back();">돌아가기</button>
            </div>
        </form>
    </div>

    <script>
    function confirmUpdate() {
        return confirm("수정 내용을 저장하시겠습니까?");
    }
    </script>
</body>
</html>
