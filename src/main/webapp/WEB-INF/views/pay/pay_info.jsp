<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp"%>
<link rel="stylesheet" href="/resources/css/pay.css"> <!-- CSS 링크를 헤더 아래로 이동 -->
<script src="/resources/js/pay_charge.js"></script>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Potato Pay</title>
</head>
<body>
<div id="profile-container">
    <div class="profile_photo">
        <img id="img_thumb" src="${pageContext.request.contextPath}/resources/upload/${sessionScope.profile_image}" alt="" width="100px" height="100px">
        <span class="mask"></span>
    </div>
    <div id="article-profile-left">
        <span>${sessionScope.nickName}님의 감자페이입니다.</span>
        <span style="color: #aaa;">${sessionScope.id}</span> <!-- 흐릿한 글씨로 표시 -->
    </div>
</div>

<div id="info" data-member-number="${sessionScope.member_number}">
    <div class="info-section" >
        <h2 style="margin-bottom: 100px;">감자 머니 (Pay)</h2>
        <p style="font-size: 0.9em; margin-bottom: 60px;" >감자머니로 쉽고 간단한 쿨거래 지금 당장 시작하세요~!!</p>
        <p>보유 금액 : ${pay} 원</p>
        <a class="button" href="/pay/pay_charge">충전하기</a>
        <a class="button" href="/pay/pay_refund">환불하기</a>
    </div>
    <div class="info-section">
        <h2>포인트 (Point)</h2>
        <p style="font-size: 0.9em;">포인트는 거래 완료 시 판매자의 매너칭찬 설문에 참여할 경우</p>
        <p style="font-size: 0.9em;">1000p 적립이 됩니다!!</p>
        <p style="font-size: 0.9em;">포인트를 페이로 전환하면 현금처럼 사용이 가능하게 됩니다!!</p>
        <p>전환 가능한 포인트 : ${point} 점</p>
        <a class="button" href="#" onclick="convertPoints(${point})">페이로 전환하기</a>
    </div>
</div>

<div class="footer">
    <p>감자페이를 이용해 주셔서 감사합니다!</p>
</div>

<script>
    // JSP에서 세션 값을 가져와 JavaScript 변수에 할당
    var member_number = "${sessionScope.member_number}"; // 세션의 member_number 값 가져오기
    console.log("member_number:", member_number); // 콘솔에 출력하여 확인
</script>
<%@ include file="../common/footer.jsp"%>
</body>
</html>