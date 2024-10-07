let refundGeneratedCode = ''; // 랜덤 인증번호

// 버튼으로 input창에 금액 입력
function addRefundAmount(amount) {
    const inputField = document.getElementById('amount');
    const currentAmount = parseInt(inputField.value) || 0; 
    inputField.value = currentAmount + amount; // 현재 입력된 값에 더하기
    toggleClearRefundButton(); // X 버튼 상태 업데이트
}

// input창 금액 비우기
function clearRefundAmount() {
    document.getElementById('amount').value = ''; // 입력 필드 비우기
    toggleClearRefundButton(); // X 버튼 상태 업데이트
}

// 금액 없을때 x버튼 숨김
function toggleClearRefundButton() {
    const inputField = document.getElementById('amount');
    const clearButton = document.querySelector('.clear-button');
    clearButton.style.display = inputField.value ? 'block' : 'none'; // 금액이 있으면 X 버튼 표시
}

// 환불 인증 팝업 열기
function openRefundVerificationWindow() {
    refundGeneratedCode = String(Math.floor(100000 + Math.random() * 900000));
    console.log("생성된 환불 인증번호: " + refundGeneratedCode);
    
    localStorage.setItem('refundGeneratedCode', refundGeneratedCode);
    
    const amount = document.getElementById('amount').value;
    localStorage.setItem('refundAmount', amount);
    
    const memberNumber = document.getElementById('refund-container').dataset.memberNumber;
    window.open(`/pay/refund_verification?member_number=${memberNumber}`, '환불 인증번호 입력', 'width=600,height=400');
}

// 환불 인증하기
function submitRefundVerification() {
    const memberNumber = getMemberNumberFromURL();
    if (!memberNumber) {
        alert("회원 번호를 가져올 수 없습니다.");
        return;
    }
    
    const verificationCodeInput = document.getElementById('verificationCode');
    const code = verificationCodeInput.value;
    const receivedCode = localStorage.getItem('refundGeneratedCode');

    if (code === receivedCode) {
        const refundAmount = localStorage.getItem('refundAmount');
        
        // 잔액 조회
        $.ajax({
            url: `/pay/getBalance?member_number=${memberNumber}`,
            type: 'GET',
            success: function(balance) {
                if (balance >= parseFloat(refundAmount)) {
                    const refundVO = {
                        from_member_number: memberNumber,
                        to_member_number: memberNumber, // 자기 자신
                        pay_amount: parseFloat(refundAmount),
                        point_amount: 0
                    };

                    // 환불 요청
                    $.ajax({
                        url: '/pay/refund',
                        type: 'POST',
                        contentType: 'application/json',
                        data: JSON.stringify(refundVO),
                        success: function(data) {
                            alert(data.message);
                           	window.opener.location.href = '/pay/pay_info'; // 부모 창 리다이렉트
                    		window.close(); // 현재 팝업창 닫기
                        },
                        error: function(jqXHR, textStatus, errorThrown) {
                            alert("환불 실패: " + errorThrown);
                        }
                    });
                } else {
                    alert("잔액이 부족합니다.");
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert("잔액 조회 실패: " + errorThrown);
            }
        });
    } else {
        alert("인증번호가 일치하지 않습니다.");
    }
}

// URL 파라미터에서 member_number 가져오기
function getMemberNumberFromURL() {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get('member_number');
}