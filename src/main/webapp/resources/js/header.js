$(document).ready(function() {
	const message = sessionStorage.getItem('loginMessage');
	const messageDiv = $('.notification_bar');
	const notice_message = sessionStorage.getItem('notice_message');

	if (message) {
		showNotification(message);
		sessionStorage.removeItem('loginMessage');
	} else {
		messageDiv.hide(); // 메시지가 없을 때 notification_bar 숨김
	}

	// 알림 표시 함수
	function showNotification(message) {
		const messageDiv = $('.notification_bar');
		messageDiv.text(message).slideDown();
		
		// 메시지 표시 후 3초 후에 사라지도록 설정
		setTimeout(function() {
			messageDiv.slideUp(function() {
				$(this).text('');
			});
		}, 3000);
	}

	// 스크롤 이벤트 처리
	$(window).scroll(function() {
		if (messageDiv.is(':visible')) {
			messageDiv.css('top', Math.max(0, 0 - $(window).scrollTop()));
		}
	});
	
	// 로그인 링크 클릭 시 모달 표시
	$('a[href="#login"]').click(function(e) {
		e.preventDefault();
		$('#loginModal').show();
	});

	// 모달 닫기 버튼 클릭 시 모달 숨기기
	$('.close').click(function() {
		$('#loginModal').hide();
	});

	// 모달 외부 클릭 시 모달 숨기기
	$(window).click(function(e) {
		if ($(e.target).is('#loginModal')) {
			$('#loginModal').hide();
		}
	});

	$('#alarm_button').click(function(e) {
		e.preventDefault(); // 기본 링크 동작 방지
		let alarm_list = document.getElementById('alarm_list');
		alarm_list.style.display = alarm_list.style.display === 'none' ? 'block' : 'none';
	});

	$('#hide').click(function(event){
		event.preventDefault(); // 기본 링크 동작 방지
		document.getElementById('alarm_list').style.display = 'none'; // 알림 목록 숨기기
	});   

	// 로그인 폼 제출
	$('#loginForm').submit(function(e) {
		e.preventDefault();
		$.ajax({
			url: '/rest/login',
			type: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({
				id: $('#loginId').val(),
				pass: $('#loginPass').val()
			}),
			success: function(response) {
				if (response && response.id) {
					$('#loginModal').modal('hide');
					
					// 로그인 성공 메시지를 세션 스토리지에 저장
					sessionStorage.setItem('loginMessage', `${response.nickName}님, 로그인 성공!`);
					
					// 관리자인 경우 관리자 페이지로, 아니면 현재 페이지 새로고침
					if (response.member_number === 'admin') {
						window.location.href = '/admin/home';
					} else {
						location.reload();
					}
				} else {
					showNotification('아이디 또는 비밀번호가 일치하지 않습니다.');
				}
			},
			error: function(xhr, status, error) {
				showNotification('블랙리스트 회원은 로그인이 불가능합니다. 관리자에게 문의하세요');
			}
		});
	});

	// 로그아웃 처리
	$('#logoutLink').click(logout);
	
	//로그아웃 함수
	function logout(e) {
		e.preventDefault();
		$.ajax({
			url: '/rest/logout',
			type: 'GET',
			dataType: 'json',
			success: function(response) {
				sessionStorage.setItem('loginMessage', '로그아웃 되었습니다');
				window.location.href = response.redirect;
			},
			error: function() {
				sessionStorage.setItem('loginMessage', '로그아웃 처리중 오류가 발생하였습니다');
			}
		});
	}
	
	//회원가입 버튼 처리
	$('#register').click(function(e){
		e.href = 'potato/register';
	});

	window.addEventListener('unload', logout);

	$(document).on('click', '#del_all', function(e) {
		e.preventDefault(); // 기본 링크 동작 방지
		let member_number = $(this).data('member-number');
		$.ajax({
			url: '/alarm/delete_all',
			type: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({member_number: member_number}), // 알림 내용 전송
			success: function(response) {
				$('.alarm_list li').remove();
				refresh_alarm();
			},
			error: function(xhr, status, error) {
				alert('알림 삭제 실패: ' + (xhr.responseJSON && xhr.responseJSON.message ? xhr.responseJSON.message : '서버 오류가 발생했습니다.'));
			}
		});
	});

	$(document).on('click', '#alarm_del', function(e) {
		e.preventDefault(); // 기본 링크 동작 방지
		let $li = $(this).closest('li');
		let data = {
			alarm_number: $(this).data('alarm-number'),
			member_number: $(this).data('member-number'),
			target_type: $(this).data('target-type'),
			target_key: $(this).data('target-key'),
			status: $(this).data('status')
		};
		$.ajax({
			url: '/alarm/delete',
			type: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(data), // 알림 내용 전송
			success: function(response) {
				$li.remove();
				refresh_alarm();
			},
			error: function(xhr, status, error) {
				alert('알림 삭제 실패: ' + (xhr.responseJSON && xhr.responseJSON.message ? xhr.responseJSON.message : '서버 오류가 발생했습니다.'));
			}
		});
	});

	$(document).on('click', '#alarm_func', function(e) {
		e.preventDefault(); // 기본 링크 동작 방지
		let $li = $(this).closest('li');
		let data = {
			alarm_number: $(this).data('alarm-number'),
			member_number: $(this).data('member-number'),
			target_type: $(this).data('target-type'),
			target_key: $(this).data('target-key'),
			status: $(this).data('status')
		};
		$.ajax({
			url: '/alarm/func',
			type: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(data), // 알림 내용 전송
			success: function(url) {
				let redirect_url = url;
				console.log('url주소:'+redirect_url);
				window.location.href = redirect_url;
			},
			error: function(xhr, status, error) {
				alert('알림 처리 실패: ' + (xhr.responseJSON && xhr.responseJSON.message ? xhr.responseJSON.message : '서버 오류가 발생했습니다.'));
			}
		});
	});

	$(document).on('pagechange', function() {
		refresh_alarm();
	});

	function refresh_alarm(){
		let session_number = document.getElementById('session_number').value;
		$.ajax({
			url: '/rest/alarm',
			type: 'POST',
			contentType: 'application/json',
			data: JSON.stringify({
				member_number: session_number
			}),
			success: function(response) {
				// 알림 목록 업데이트 로직
			},
			error: function(xhr, status, error) {
				console.error('알림 새로고침 실패:', error);
			}
		});
	}
});