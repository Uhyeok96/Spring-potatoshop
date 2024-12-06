<img src="https://capsule-render.vercel.app/api?type=waving&color=auto&height=280&section=header&text=Spring-project%20potatoshop&fontSize=60" />
<a href="https://git.io/typing-svg"><img src="https://readme-typing-svg.demolab.com?font=Fira+Code&weight=600&size=26&pause=1000&color=F7A033&width=435&lines=%EA%B0%90%EC%9E%90%EB%A7%88%EC%BC%93+-+%EC%A4%91%EA%B3%A0+%EA%B1%B0%EB%9E%98+%EC%82%AC%EC%9D%B4%ED%8A%B8" alt="Typing SVG" /></a>

[Spring 프로젝트 2조]
<br><br><br>
## 프로젝트 소개
'당근마켓' 을 참고하여 만든 중고 거래 사이트 입니다.
> 모바일 중심의 UI에서 PC 중심의 UI로 개선.
<br>

## 개발 기간
2024.09.02 ~ 2024.10.04 (연휴 제외 약 3주)
<br><br>

## 멤버 구성
승환 - 관리자 회원의 커뮤니티 페이지, 회원 관리(블랙리스트 관리)
<br><br>
우혁 - 중고거래 판매자에 대한 후기, 판매물품 리스트, 매너칭찬 설문, 감자페이 및 포인트
<br><br>
태희 - 중고거래 게시판 기능, 파일 업로드 및 검색, 더보기 페이징 처리
<br><br>
희준 - 로그인/회원가입 및 회원정보 관리, 판매자와 구매자의 1:1 채팅 기능, 예외 페이지 관리, 그 외 부가기능
<br><br>
## 개발 환경
- `java 11`
- **IDE** : Eclipse
- **Framework** : Spring Framework(5.0.7)
- **Database** : Oracle DB(11xe)
- **ORM** : Mybatis
- **Infra** : Aws EC2
- **VCS** : github
<br><br>

## 주요 기능
**회원**
- 로그인(Session에 저장된 data 이용하여 header 기능 구분)

- 회원가입(아이디 중복확인, 필수 사항 기입 체크)

- 마이페이지 및 활동내역
  > 회원정보 변경 기능 및 작성글, 후기, 신고 정보 확인

- 중고 거래 게시판 리스트
  > 인기 게시물(좋아요 수)과 최근 게시물(등록일) 중 선택하여 리스트 출력 가능
  > 판매중, 예약중, 판매완료 순으로 출력

- 게시물 등록 - 이미지 미리보기 및 파일 업로드 기능
  
- 게시물 상세보기, 수정, 삭제
  > 로그인 한 회원 판별하여 작성자일 경우 수정, 삭제 버튼 / 다른 회원일 경우 대화연결, 신고하기 버튼 활성화

- 좋아요, 관심(장바구니) 리스트

- 게시물 검색 기능 및 인기 검색어 순위(1~10위) 표시
  > 카테고리와 제목 각각 따로 검색 가능

- 판매자와 구매자간의 1:1 대화기능
  > 거래도 채팅방에서 버튼으로 이루어지며 마지막 거래완료 버튼 클릭 시
  > 설문 버튼 활성화 (설문 작성 시 작성한 회원의 포인트 적립)

- 판매자에 대한 후기(댓글) 기능과 답글(대댓글) CRUD 기능

- 판매자의 판매물품 리스트와 매너 칭찬(설문) 기능
  > 거래완료 시 설문을 통하여 기록을 남기고 판매자의 매너온도 상승 및 하락 기능

- 감자페이와 포인트 (충전, 환불, 포인트 전환)
  > 충전과 환불은 랜덤인증번호 생성하여 인증받아야 함, 포인트는 페이로 전환하여 사용가능

- QNA 게시판 - 하단 footer에 QNA게시판을 만들어 자주 묻는 질문 리스트 확인 가능

- 고객의 편지(개선사항) - 하단 footer에 개선사항 전달하는 Message 기능
  > 관리자가 고객의 편지 리스트에서 확인

- 오류 통합 관리(예외 발생 시 통합 오류 처리 기능)

- 그 외 성능 향상을 위한 부가기능
  > ULID, INDEX, 테이블 분리, Alias


**관리자**
- 관리자(운영자)의 회원 검색

- 회원 관리(회원가입일 및 최근 로그인 시간 정보)
  
- 회원 등급 수정(일반 회원, 블랙리스트 회원)
  > 블랙리스트 회원은 로그인 불가

- 신고내역 확인 / 신고회원 등급 수정

- 관리자 게시판 (공지 등록, 수정, 삭제 등)

<br><br>

## 발표 영상
<iframe width="946" height="532" src="https://www.youtube.com/embed/_e_7X1SDAw0" title="감자마켓 프로젝트 발표(김우혁)" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>
<iframe width="640" height="360" src="https://www.youtube.com/embed/6Az2cNU7gUw" frameborder="0" gesture="media" allowfullscreen=""></iframe>

[![Video Label](http://img.youtube.com/vi/_e_7X1SDAw0/0.jpg)](https://youtu.be/_e_7X1SDAw0)
<br><br>

## 한 눈에

[감자마켓 홈페이지 이동](http://mbc-webcloud.iptime.org:6302/home) <http://mbc-webcloud.iptime.org:6302/home>

**TEST 계정 로그인**

ID : test1

PW : q1

![image](https://github.com/user-attachments/assets/8b770c18-0490-4b6a-8d51-ecf4ff77fdb2)
![image](https://github.com/user-attachments/assets/bc668191-c866-4434-a5e5-dd30d3e3fce3)
![image](https://github.com/user-attachments/assets/56740c3f-0798-48e7-a8f2-80e31bfb37ae)





