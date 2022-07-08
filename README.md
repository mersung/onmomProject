
## 프로젝트명 
***
    온몸(Onmom)

    : "온라인(Online)"과 "몸(Body)"의 합성어.
      몸을 가꾸기 위해 이용하는 헬스장에 대한
      정보를 공유하는 온라인 커뮤니티입니다.
#
## 팀원소개
***
    이하린(PM), 나석주(PL), 조민혁, 박창신, 심대성
#
## 프로젝트 기간
***
    2022.06.14 ~ 2022.07.12
#
## 프로젝트 목적
***
    서울 지역별 헬스장에 대한 회원들의 후기글을 통하여 다양한 정보를 얻을 수 있는 사이트
#
## 프로젝트 설명
***
    헬스장을 선택할 때 회원권금액, PT금액과 같은 정보를 얻으려면

    대부분 직접방문 또는 전화문의를 해야해서 번거롭다.

    이러한 불편을 해소하기 위하여

    서울 지역별 헬스장에 대한 회원들의 솔직한 후기공유를 통해

    다양한 정보를 얻을 수 있는 사이트를 제작하려고 한다.

####

    1) 로그인한 회원만 게시판에 글쓰기 가능. 비회원은 읽기만 가능

    2) <추천게시판> 지역별 카테고리 선택 

    3) <추천게시판> 해당 지역에 대한 헬스장 리뷰글 확인

    4) <추천게시판> 리뷰글을 통해 헬스장 정보 확인 (좋아요, 싫어요, 댓글)

    5) <자유게시판> 리뷰글 이외의 정보 확인 

    6) <공지사항> 추천게시판과 자유게시판 이용수칙 확인

    7) <지도> 헬스장 위치 검색 (KAKAO Map API 사용)

    8) <회원들과 채팅> 온몸 회원들과 자유롭게 채팅 (동글채팅 API 사용)

    9) <상담원과 채팅> 문의사항을 상담원에게 채팅으로 문의 (tawk.to API 사용)
#
## 일정계획서
***
[02_chart_2022_06_20.xlsx](https://github.com/ShimDaeSung1/onmomProject/files/9042501/02_chart_2022_06_20.xlsx)
![Onmom_Plan_2022_06_20](https://user-images.githubusercontent.com/86524081/176813061-1e41b298-d0df-479c-a78c-0f4ad3dc0825.jpg)
#
## UI 설계서
***
[03_UI_2022_06_30.pptx](https://github.com/ShimDaeSung1/onmomProject/files/9042512/03_UI_2022_06_30.pptx)
![OnmomProject_UI_page-0001](https://user-images.githubusercontent.com/86524081/176625305-48865b34-c471-4d53-8203-7cb1b67bbdae.jpg)
![OnmomProject_UI_page-0002](https://user-images.githubusercontent.com/86524081/176625317-0ad8bf8d-25dc-430a-86b7-fe1b24cf4cde.jpg)
![OnmomProject_UI_page-0003](https://user-images.githubusercontent.com/86524081/176625319-986c2c67-f9b0-4d55-b92e-0401cff8688c.jpg)
![OnmomProject_UI_page-0004](https://user-images.githubusercontent.com/86524081/176625322-10142ab4-6afa-4307-a647-73df8a139c9c.jpg)
![OnmomProject_UI_page-0005](https://user-images.githubusercontent.com/86524081/176625325-1efcd610-1f7d-4b7f-acb5-72cabb297bc2.jpg)
![OnmomProject_UI_page-0006](https://user-images.githubusercontent.com/86524081/176625328-7d3c7031-8be3-4378-9a3e-d2614276cc7e.jpg)
![OnmomProject_UI_page-0007](https://user-images.githubusercontent.com/86524081/176625330-ddfa74c1-2d8f-43d9-ac09-ac724608723e.jpg)
#
## 네이밍룰
***
[05_naming_2022_06_30.xlsx](https://github.com/ShimDaeSung1/onmomProject/files/9042517/05_naming_2022_06_30.xlsx)
![OnmomProject_NamingRule_page-0001](https://user-images.githubusercontent.com/86524081/176625129-8e9c07fc-69ee-43a3-94d3-bd82d41f52ba.jpg)
![OnmomProject_NamingRule_page-0002](https://user-images.githubusercontent.com/86524081/176625133-b154089f-59e4-4488-bde1-f74863ac76ca.jpg)
#
## ERD
***
[dbdocs.zip](https://github.com/ShimDaeSung1/onmomProject/files/9042534/dbdocs.zip)
![ERD_2022_06_30](https://user-images.githubusercontent.com/86524081/176608037-daf77596-b035-49d8-8cc6-5d75cbcfd5be.png)
#
## 기술스택
***
**Frontend**

    HTML5, CSS3, JavaScript, JQuery, Ajax, bootstrap, thymeleaf

**Backend**

    Java, JSP, Spring boot, Gradle, Spring-Data-JPA, Spring-Security

**DB**

    Maria db

**WAS**
    
    Apache Tomcat

**배포**

    AWS EC2 (Amazon Linux)
#
## 주요기능
***
### 로그인
    스프링 시큐리티를 이용한 구글 로그인을 구현했습니다.

    로그인 후 <마이페이지, 로그아웃> 으로 변경됩니다.
    로그인 시 게시글 등록과 댓글 등록이 가능합니다.
https://user-images.githubusercontent.com/86524081/177472500-6371d6ed-49b9-49ee-8b0a-9976bcecdea0.mp4
#
### 마이페이지
    마이페이지에서는 <내 프로필> 및 <내가 쓴 글>을 확인합니다.

    <내가 쓴 글>에서 게시글 클릭 시,
    해당 게시글 상세보기 페이지로 이동합니다.
https://user-images.githubusercontent.com/86524081/177738925-124a42f4-03ae-4118-8fe4-da20e102c952.mp4
#
### 공지사항
    추천게시판 및 자유게시판 이용수칙을 확인합니다.
https://user-images.githubusercontent.com/86524081/177473820-dcfd3ee5-17d9-435a-a1d1-b64d4fa69704.mp4
#
### 추천게시판
    회원들이 헬스장 후기를 작성하는 게시판입니다.
    
    메인페이지에서는 상위 6개의 게시글을 확인할 수 있습니다.
    (좋아요 순)
    드롭다운으로 지역을 선택합니다.
    MORE 클릭시 추천게시판 페이지로 이동합니다.
https://user-images.githubusercontent.com/86524081/177734202-9fe99610-fe3d-4a37-85a2-47a360b92614.mp4

    로그인을 한 회원에 한해서만 글쓰기 버튼이 노출됩니다.
    게시글 등록, 수정, 삭제가 가능합니다.
https://user-images.githubusercontent.com/86524081/177734190-36156e7e-850a-42d7-bebf-7e0a711d2e4b.mp4

    비회원이 댓글 등록을 시도할 경우에 로그인 페이지로 이동합니다.
    로그인 후 댓글 등록, 수정, 삭제가 가능합니다.
https://user-images.githubusercontent.com/86524081/177734199-e17b971c-863f-4822-bb1e-200f75fb66b4.mp4
#
### 자유게시판
    헬스장 후기를 제외한 게시글을 회원들이 자유롭게 작성합니다.

    로그인을 한 회원에게만 글쓰기 버튼이 노출됩니다.
    로그인 후 게시글 등록, 수정, 삭제가 가능합니다.

    --------

    비회원이 댓글 등록을 시도할 경우, 로그인 페이지로 이동합니다.
#
### 헬스장 지도 (KAKAO Map API)
    헬스장 위치 정보를 제공합니다.

    두 가지 방법으로 구현했습니다.

    1. 메인페이지에서 서울시 헬스장 버튼을 클릭하면
       서울시 헬스장 영역으로 이동합니다.
https://user-images.githubusercontent.com/86524081/177473897-1f147800-3d27-4498-8597-66e28e9aacb4.mp4
    
    2. 네비게이션 바에서 지도 아이콘을 클릭하면
       헬스장 검색 페이지로 이동합니다.
https://user-images.githubusercontent.com/86524081/177473949-868aaaa6-06f3-47bd-b287-d617e6178cf0.mp4
#
### 회원들과 채팅 (동글채팅 API)
    사이트 접속자들과 자유롭게 채팅을 할 수 있습니다.

    메인페이지 하단의 <채팅방 참여하기> 버튼 클릭 시
    <채팅방 닫기> 로 변경되고 채팅창이 열립니다.
https://user-images.githubusercontent.com/86524081/177474001-cbec45a7-52f7-447b-8fe7-10b4944f6819.mp4
#
### 상담원과 채팅 (tawk.to API)
    사이트 이용시 문의사항은 상담원과 채팅을 통해 문의합니다.

    오른쪽 하단 아이콘을 클릭 후, 문의사항을 입력합니다.
    상단에서 예상되는 대기시간을 확인하고 답변을 기다립니다.
https://user-images.githubusercontent.com/86524081/177474044-c9007a9c-8878-4bad-9569-ff91106e59be.mp4


#
## 최종 산출물
***
    최종발표 UCC (유튜브에 영상 업로드한 링크 첨부할 예정)
####
    최종발표 PPT (이미지로 변환하여 첨부하기)
####
    온몸 웹사이트 (AWS 배포 후 링크 첨부할 예정)



