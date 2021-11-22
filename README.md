# Spring을 활용한 간단한 CRUD 프로젝트

네이버 부스트코스 웹 백엔드 강의 내 프로젝트를 Spring / Spring Boot를 활용해 진행해봤습니다

간단한 CRUD 등을 구현한 프로젝트이지만 기존의 프로젝트에서 추가 기능을 넣어 어려운 기능까지 구현해볼 수 있도록 했습니다.

---

## Project A : 명함 관리 프로그램

### Description

이름, 휴대폰 번호, 회사 이름으로 구성된 회원 엔티티

이를 검색 및 조회하면 된다

### Diagram

![다이어그램](https://user-images.githubusercontent.com/67625677/140455384-33d78ae2-ef1c-4b8e-9070-54b5c424b315.png)

---

## Project B : 게시판 만들기

### Description

이름, 내용으로 구성된 form을 통해 게시판에 글 남기기

글 작성 시간 자동으로 파싱되어 나타남

글 수정 및 삭제 기능 존재

댓글은 댓글과 대댓글으로 나뉘어 남겨짐

댓글은 게시글 아래에 5개씩 paging된 상태로 조회

댓글에는 좋아요 싫어요 기능 존재

싫어요가 10개 이상이 되는 댓글은 삭제

글 또는 댓글 도배 방지 기능 (10초간 같은 이름 계속 작성시 에러 메세지)

로그인 기능 구현

보안

권한

### 실행 환경

---