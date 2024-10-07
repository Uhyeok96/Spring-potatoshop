<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp"%>
<link rel="stylesheet" href="/resources/css/pay_refund.css">

<div id="refund-container" data-member-number="<%= session.getAttribute("member_number") %>">
    <h1>환불하기</h1>
    <form id="refundForm"> <!-- action 속성 제거 -->
        <div class="form-group">
            <label for="amount">환불 금액</label>
            <div class="input-container">
                <input type="number" id="amount" name="refund_amount" min="1" required placeholder="환불할 금액을 입력하세요." oninput="toggleClearRefundButton()">
                <button type="button" class="clear-button" onclick="clearRefundAmount()" style="display:none;">✖</button>
            </div>
        </div>
        <div class="form-group">
            <ul class="AmountShortcut_shortcut-list__pEIG9">
                <li class="AmountShortcut_item__LIolC"><button type="button" class="AmountShortcut_button-amount__tuXDw" onclick="addRefundAmount(10000)">+1만</button></li>
                <li class="AmountShortcut_item__LIolC"><button type="button" class="AmountShortcut_button-amount__tuXDw" onclick="addRefundAmount(50000)">+5만</button></li>
                <li class="AmountShortcut_item__LIolC"><button type="button" class="AmountShortcut_button-amount__tuXDw" onclick="addRefundAmount(100000)">+10만</button></li>
                <li class="AmountShortcut_item__LIolC"><button type="button" class="AmountShortcut_button-amount__tuXDw" onclick="addRefundAmount(1000000)">+100만</button></li>
            </ul>
        </div>
        <div class="form-group">
            <button type="button" class="btn" onclick="openRefundVerificationWindow()">환불하기</button> <!-- 환불하기 버튼 -->
        </div>
    </form>
</div>

<div class="footer">
    <p>감자페이를 이용해 주셔서 감사합니다!</p>
</div>

<script src="/resources/js/pay_refund.js"></script> <!-- 환불 관련 JavaScript 파일 추가 -->
<%@ include file="../common/footer.jsp"%>