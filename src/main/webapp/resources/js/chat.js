$(document).ready(function() {
            const sender = $('#sender').val(); // 보내는사람
			const chat_number = $('#chat_number').val();
			let status = $('#set_status').val();
			let buyer_number = $('#buyer_number').val();
			let celler_number = $('#celler_number').val();
			let board_number = $('board_number').val();
			
            // 채팅보내기
            function sendMessage(message) {
                $.ajax({
                    url: '/chat/send',
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify({
                        sender: sender,
                        chat_number: chat_number,
                        content: message,
                        time_stamp: new Date() // Optional, if needed
                    }),
                    success: function() {
                        $('#content').val(''); // Clear input field
                        loadMessages(); // Reload messages
						$('.chat-body').scrollTop($('.chat-body')[0].scrollHeight);
                    },
                    error: function(xhr, status, error) {
                        console.error('Error sending message:', error);
                    }
                });
            }

            // 채팅내용 불러오기
    function loadMessages() {
    $.ajax({
      url: '/chat/messages',
      type: 'POST',
      contentType: 'application/json',
      data: JSON.stringify({
        chat_number:chat_number
      }),
      success: function(response) {
	  let chat_list = response; //list<ChatVO> 넣기
        $('.message-left').empty();
        $('.message-right').empty();
        chat_list.forEach(function(chat) {
		const formattedTime = new Date(chat.time_stamp).toLocaleString('ko-KR', {
        month: '2-digit',
        day: '2-digit',
        hour: '2-digit',
        minute: '2-digit',
        hour12: false
    }).replace(',', ''); // 쉼표 제거
          const messageClass = chat.sender === sender?'.message-right' :'.message-left';
          const messageHTML = `
            <div class="message">
              <span class="content">${chat.content}</span><br>
              <span class="time-stamp">${formattedTime}</span>
            </div>
          `;
           $(messageClass).append(messageHTML);
        });
        $('.chat-messages').scrollTop($('.chat-messages')[0].scrollHeight); // Scroll to bottom
      },
      error: function(xhr, status, error) {
        console.error('Error loading messages:', error);
      }
    });
  }


            // 채팅 지우기
            function deleteMessage(chatNumber) {
                $.ajax({
                    url: '/chat/delete',
                    type: 'insert',
                    data: {
                        sender: sender,
                        chat_number: chat_number,
                        time_stamp : time_stamp
                    },
                    success: function() {
                        loadMessages(); // Reload messages after deletion
                    },
                    error: function(xhr, status, error) {
                        console.error('Error deleting message:', error);
                    }
                });
            }

            // Load messages initially
            loadMessages();

            // 전송버튼 눌러서 전송하기
            $('#send-button').click(function() {
                const message = $('#content').val().trim();
                if (message) {
                    sendMessage(message);
                }
            });

            // 엔터키를 눌러도 전송이 되게하기
            $('#message-input').keypress(function(e) {
                if (e.which === 13) { // Enter key
                    $('#send-button').click();
                }
            });
              // 삭제버튼 눌러서 삭제하기
  $(document).on('click', '.delete-button', function() {
    const time_stamp = $(this).parent().find('.time-stamp').text();
    deleteMessage(chat_number, time_stamp);
  });
  
// status 값으로 채팅창 상태변경
function chat_status(chat_number, status) {
	console.log("status=" + status +" ,chat_number="+chat_number);
    $.ajax({
      url: '/chat/status',
      type: 'POST',
      contentType: 'application/json',
      data: JSON.stringify({
            chat_number: chat_number,
            status: status,
            board_number:board_number,
            celler_number:celler_number,
            buyer_number:buyer_number
        }),
        success: function(response) {
            console.log('예약신청 성공:', response);
            window.location.href = window.location.href;
            // 추가적인 성공 처리 로직
        },
        error: function(xhr, status, error) {
            console.error('예약신청 오류:', error);
        }
    });
}

// 결제
$(document).on('click', '#payWithPotatoPay', function() {
    const paymentData = {
        buyer_number: $('#buyer_number').val(),
        seller_number: $('#celler_number').val(),
        price: parseInt($('#productPrice').text().replace(/\D/g, '')) // 가격 가져오기
    };
    console.log('paymentData:', paymentData);

    $.ajax({
        url: '/pay/processPayment',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(paymentData),
        success: function(response) {
            console.log('결제 응답:', response); // 응답 로그 출력
            if (response.status === "success") {
                alert("결제가 완료되었습니다.");
                
                // 채팅방 상태를 3으로 변경
                const chat_number = $('#chat_number').val(); // 채팅 번호 가져오기
                console.log('chat_number:', chat_number);
                chat_status(chat_number, 3); // 상태 3으로 변경
                
                window.location.reload(); // 페이지 새로고침
            } else {
                alert("결제에 실패했습니다. 잔액을 확인해주세요.");
            }
        },
        error: function(xhr, status, error) {
            console.error('결제 처리 오류:', error);
            console.error('서버 응답:', xhr.responseText); // 서버 응답 로그 출력
            alert("결제 처리 중 오류가 발생했습니다.");
        }
    });

    closePaymentModal(); // 결제 후 모달 닫기
});



// 칭찬설문 후 점수에 따른 해당 회원 온도 변경 및 매너칭찬 점수 저장
function submitSurvey() {
    const selectedRating = document.querySelector('input[name="rating"]:checked');
    const user_number = $('#celler_number').val();
    const buyer_number = $('#buyer_number').val();
    const chat_number = $('#chat_number').val();
    const statusValue = document.getElementById('finishBtn').getAttribute('data-value');

    console.log("user_number :", user_number);
    console.log("statusValue :", statusValue); // statusValue 확인

    if (selectedRating) {
        const ratingValue = parseInt(selectedRating.value);
        let tempChange = (ratingValue >= 3) ? 1 : -1;

        console.log("tempChange :", tempChange);
        
        // 매너 설문 결과를 서버에 전송
        $.ajax({
            type: 'POST',
            url: '/manner/submit', // 매너 설문을 위한 URL
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify({
                member_number: user_number, // 해당 회원 번호
                rating: ratingValue // 선택한 점수
            }),
            success: function(response) {
                console.log('서버 응답:', response);
                alert('칭찬이 등록되었습니다.');

                // 온도 업데이트 요청
                $.ajax({
                    type: 'POST',
                    url: '/user/updateTemperature',
                    contentType: 'application/json; charset=utf-8',
                    data: JSON.stringify({
                        user_number: user_number,
                        temperatureChange: tempChange
                    }),
                    success: function(response) {
                        console.log('온도 업데이트 응답:', response);
                        alert('온도가 업데이트되었습니다.');
                        
                        // 포인트 적립 요청
                        $.ajax({
                            type: 'POST',
                            url: '/pay/addPoints',
                            contentType: 'application/json; charset=utf-8',
                            data: JSON.stringify({
                                member_number: buyer_number,
                                point: 1000 // 적립할 포인트 수
                            }),
                            success: function(response) {
                                alert('포인트가 적립되었습니다.');
                                chat_status(chat_number, parseInt(statusValue)); // 채팅방 status값 5로 변경
                                window.location.reload(); // 현재 페이지 새로고침
                                closeMannerModal();
                            },
                            error: function(xhr, status, error) {
                                alert('포인트 적립에 실패했습니다.');
                                console.error('Error:', error);
                            }
                        });
                    },
                    error: function(xhr, status, error) {
                        alert('온도 업데이트 실패.');
                        console.error('Error:', error);
                    }
                });
            },
            error: function(xhr, status, error) {
                alert('칭찬 등록에 실패했습니다.');
                console.error('Error:', error);
            }
        });
    } else {
        alert("점수를 선택해 주세요.");
    }
}

// 칭찬하기 버튼 클릭 이벤트
$(document).ready(function() {
	$("#finishBtn").on('click',submitSurvey);
});

 	$("#buyBtn").on('click', function() {
        //const chat_number = $(this).data('chat-number');
        //const status = $(this).data('status');
        console.error('chat_number:', chat_number);
        console.error('status:', status);
        chat_status(chat_number, status);
    });

    //$("#buyBtn2").on('click', function() {
        //chat_status(chat_number, status);
    //});

    $("#cellBtn").on('click', function() {
        chat_status(chat_number, status);
    });

    $("#cellBtn2").on('click', function() {
        chat_status(chat_number, status);
    });

//$("#buyBtn").on('click',chat_status);
//$("#buyBtn2").on('click',chat_status);
//$("#cellBtn").on('click',chat_status);
//$("#cellBtn2").on('click',chat_status); 
 
loadMessages();
function focus_roll(){ $('.chat-body').scrollTop($('.chat-body')[0].scrollHeight);	}
focus_roll;		
  setInterval(function() {
  loadMessages();
}, 5000); // 3초마다 채팅내용을 불러옵니다.
  
});

// 칭찬합시다 버튼 클릭시 모달창 열림
function openMannerModal() {
    document.getElementById('mannerModal').style.display = 'block';
}

// 칭찬모달 닫기 버튼
function closeMannerModal() {
    document.getElementById('mannerModal').style.display = 'none';
}

// 결제모달 띄우면서 해당 상품 가격 가져옴
function showPaymentOptions() {
    const board_number = $('#board_number').val();
    
    $.ajax({
        url: '/pay/getPrice?board_number=' + encodeURIComponent(board_number),
        type: 'GET',
        success: function(response) {
            const price = response.price;
            $('#productPrice').text('상품 가격: ' + price + '원');
            $('#paymentModal').show();
        },
        error: function(xhr, status, error) {
            console.error('상품 가격을 가져오는 중 오류 발생:', error);
        }
    });
}

// 결제모달 닫기버튼
function closePaymentModal() {
    $('#paymentModal').hide();
}



$(document).on('click', '#payOnSite', function() {
    // 현장 결제 로직 구현
    alert('현장 결제를 진행합니다.');
    closePaymentModal();
});

