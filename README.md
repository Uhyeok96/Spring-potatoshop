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
- 중고 거래 게시판 리스트
  > 인기 게시물(좋아요 수)과 최근 게시물(등록일) 중 선택하여 리스트 출력 가능
  > 판매중, 예약중, 판매완료 순으로 출력.
- 게시물 등록 - 이미지 미리보기 및 파일 업로드 기능
- 게시물 상세보기, 수정, 삭제(좋아요 및 장바구니 기능 포함)
  > 로그인 한 회원 판별하여 작성자일 경우 수정, 삭제 버튼 / 다른 회원일 경우 대화연결, 신고하기 버튼 활성화
- 게시물 검색 기능 및 인기 검색어 순위(1~10위) 표시
  > 카테고리와 제목 각각 따로 검색 가능


**관리자**
- 관리자(운영자)의 회원 검색
- 회원 관리(회원가입일 및 최근 로그인 시간 정보)
- 회원 등급 수정(일반 회원, 블랙리스트 회원)
  > 블랙리스트 회원은 로그인 불가
- 신고내역 확인 / 신고회원 등급 수정
- 관리자 게시판 (공지 등록, 수정, 삭제 등)

## 한 눈에
