
## 프로젝트명 
***
    온몸(Onmom)
####
    "온라인(Online)"과 "몸(Body)"의 합성어.
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
[dbdocs.zip](https://github.com/ShimDaeSung1/onmomProject/files/9081310/dbdocs.zip)
![ERD_2022_07_11](https://user-images.githubusercontent.com/86524081/178200316-19acbeab-7f37-4d85-99ba-9c93fda58883.png)
#
## Class Diagram
![cld](https://user-images.githubusercontent.com/86524081/178201458-1b3abec4-a47b-4f8a-a426-547feb3a223d.jpg)
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
#
## 주요기능
***
### 로그인
    스프링 시큐리티를 이용한 구글 로그인을 구현했습니다.

    로그인 후 <마이페이지, 로그아웃> 으로 변경됩니다.
    로그인 시 게시글 등록과 댓글 등록이 가능합니다.
https://user-images.githubusercontent.com/86524081/178181811-e41e4eef-0958-4b91-b42a-62840ca97487.mp4
#
### 마이페이지
    마이페이지에서는 <내 프로필> 및 <내가 쓴 글>을 확인합니다.

    <내가 쓴 글>에서 게시글 클릭 시,
    해당 게시글 상세보기 페이지로 이동합니다.
https://user-images.githubusercontent.com/86524081/178181838-cda25d38-aa54-446f-bde7-9197e6c20b6b.mp4
#
### 공지사항
    추천게시판 및 자유게시판 이용수칙을 확인합니다.
https://user-images.githubusercontent.com/86524081/178181858-d436fee5-0f41-4fbb-8b14-bd2495177860.mp4
#
### 추천게시판
    회원들이 헬스장 후기를 작성하는 게시판입니다.
    
    메인페이지에서는 상위 6개의 게시글을 확인할 수 있습니다.
    (좋아요 순)
    드롭다운으로 지역을 선택합니다.
    MORE 클릭시 추천게시판 페이지로 이동합니다.
https://user-images.githubusercontent.com/86524081/178181883-6635e1ec-6f09-434e-88bd-eaa40a17940e.mp4
####
    로그인을 한 회원에 한해서만 글쓰기 버튼이 노출됩니다.
    게시글 등록, 수정, 삭제가 가능합니다.
https://user-images.githubusercontent.com/86524081/178181908-4545c411-6cc0-421f-a98b-f8a88ccbdcd7.mp4
####
    비회원이 댓글 등록을 시도할 경우에 로그인 페이지로 이동합니다.
    로그인 후 댓글 등록, 수정, 삭제가 가능합니다.
https://user-images.githubusercontent.com/86524081/178181924-258228e4-b596-45a8-8e76-a6116579be94.mp4
####
    게시글 등록 시에 내용 또는 이미지가 없거나, 
    글자수를 초과하는 경우 에러페이지로 이동합니다.
https://user-images.githubusercontent.com/86524081/178188837-d2b45edd-4bc0-421c-9244-2744259a1c74.mp4
#
### 자유게시판
    헬스장 후기를 제외한 게시글을 회원들이 자유롭게 작성합니다.

    로그인을 한 회원에게만 글쓰기 버튼이 노출됩니다.
    로그인 후 게시글 등록, 수정, 삭제가 가능합니다.
https://user-images.githubusercontent.com/86524081/178182039-5cb5e8a5-973c-4837-bbec-fe713160ff74.mp4
####
    비회원이 댓글 등록을 시도할 경우, 로그인 페이지로 이동합니다.
    로그인 후 댓글 등록, 수정, 삭제가 가능합니다.
https://user-images.githubusercontent.com/86524081/178182061-23c27705-d5c3-4b76-85e4-5363b89b3722.mp4
#
### 헬스장 지도 (KAKAO Map API)
    헬스장 위치 정보를 제공합니다.

    두 가지 방법으로 구현했습니다.

    1. 메인페이지에서 서울시 헬스장 버튼을 클릭하면
       서울시 헬스장 영역으로 이동합니다.
https://user-images.githubusercontent.com/86524081/178182092-f9b1fef0-9192-492a-aaa7-df918cb95d02.mp4
####
    2. 네비게이션 바에서 지도 아이콘을 클릭하면
       헬스장 검색 페이지로 이동합니다.
https://user-images.githubusercontent.com/86524081/178182123-29a6cd33-9345-4d3f-be19-bb22697b3455.mp4
#
### 회원들과 채팅 (동글채팅 API)
    사이트 접속자들과 자유롭게 채팅을 할 수 있습니다.

    메인페이지 하단의 <채팅방 참여하기> 버튼 클릭 시
    <채팅방 닫기> 로 변경되고 채팅창이 열립니다.
https://user-images.githubusercontent.com/86524081/178182144-649f335c-afc4-41a9-9dc0-9ebbb636fc9e.mp4
#
### 상담원과 채팅 (tawk.to API)
    사이트 이용시 문의사항은 상담원과 채팅을 통해 문의합니다.

    오른쪽 하단 아이콘을 클릭 후, 문의사항을 입력합니다.
    상단에서 예상되는 대기시간을 확인하고 답변을 기다립니다.
https://user-images.githubusercontent.com/86524081/178182173-0556847f-243a-4b75-832c-eb829d571880.mp4
#
## 최종 산출물
***
### 최종발표 PPT
[07_Onmom_2022_07_11.pptx](https://github.com/ShimDaeSung1/onmomProject/files/9081324/07_Onmom_2022_07_11.pptx)
![슬라이드1](https://user-images.githubusercontent.com/86524081/178201168-5697e8d8-251f-4931-bae8-22d0958cb9a2.PNG)
![슬라이드2](https://user-images.githubusercontent.com/86524081/178201171-5423216d-12d8-4cc9-8584-af56104449f7.PNG)
![슬라이드3](https://user-images.githubusercontent.com/86524081/178201176-d03928f3-3243-4493-8806-a8acb2391130.PNG)
![슬라이드4](https://user-images.githubusercontent.com/86524081/178201178-a6898df2-290d-4d80-a24c-bd0faf5036eb.PNG)
![슬라이드5](https://user-images.githubusercontent.com/86524081/178201181-924ae73e-fff6-4b58-806e-9cf53416d03d.PNG)
![슬라이드6](https://user-images.githubusercontent.com/86524081/178201184-5061cdbe-a3bf-4b54-b11b-6cacb3163d6a.PNG)
![슬라이드7](https://user-images.githubusercontent.com/86524081/178201185-883a4b22-782f-446f-abc3-7eaf5fc5a8de.PNG)
![슬라이드8](https://user-images.githubusercontent.com/86524081/178201186-1e6d9148-53a4-4eb1-a22d-9d0ce5db0303.PNG)
![슬라이드9](https://user-images.githubusercontent.com/86524081/178201188-f34e604a-df65-41de-aeff-714c0829ced3.PNG)
![슬라이드10](https://user-images.githubusercontent.com/86524081/178201191-fddf6af4-7379-4d4d-b91e-15c65f8b9950.PNG)
![슬라이드11](https://user-images.githubusercontent.com/86524081/178201196-fb21459e-9b22-4baa-a097-7691b9d35b6c.PNG)
![슬라이드12](https://user-images.githubusercontent.com/86524081/178201198-db07a11f-0ae2-4beb-a9c2-6daa0638cec3.PNG)
![슬라이드13](https://user-images.githubusercontent.com/86524081/178201201-5d81c049-ff99-4442-9faa-887f3919172d.PNG)
![슬라이드14](https://user-images.githubusercontent.com/86524081/178201204-25c4db5f-2bcf-4124-944f-baa3e680d450.PNG)
![슬라이드15](https://user-images.githubusercontent.com/86524081/178201207-e45d05e2-48f4-473d-8b24-ecf7520c5bc9.PNG)
![슬라이드16](https://user-images.githubusercontent.com/86524081/178201211-8264f97c-7f32-443c-8c32-71573afeba82.PNG)
![슬라이드17](https://user-images.githubusercontent.com/86524081/178201213-812a6e4c-54d9-430f-baa5-da76dd7e404a.PNG)
![슬라이드18](https://user-images.githubusercontent.com/86524081/178201147-87b15403-77f5-41a6-b182-a9b430330a4f.PNG)
![슬라이드19](https://user-images.githubusercontent.com/86524081/178201152-8d83c48c-ee85-43aa-850d-d7fe78f91ef8.PNG)
![슬라이드20](https://user-images.githubusercontent.com/86524081/178201154-80bbd439-85ce-434e-9c2d-34927779b68f.PNG)
![슬라이드21](https://user-images.githubusercontent.com/86524081/178201156-3bedb9ba-c044-434a-bc0b-052b5079e50e.PNG)
![슬라이드22](https://user-images.githubusercontent.com/86524081/178201159-90ba4067-b5b0-4752-966b-3b07432c112a.PNG)
![슬라이드23](https://user-images.githubusercontent.com/86524081/178201163-363ee2fb-bb81-4146-b7ca-1e1551ea9c96.PNG)
![슬라이드24](https://user-images.githubusercontent.com/86524081/178201165-fa99dd2f-4cd6-4cf5-9cea-8e5d0793a1b3.PNG)
### 최종발표 UCC
https://www.youtube.com/watch?v=pEG5BOj7EKk




