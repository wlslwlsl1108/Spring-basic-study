# Spring-basic-study (베이직반 3회차)

## ※ 복습
- 시간 복잡도에서 O(1)은 프로그램 상 제일 빠름
- 클래스는 설계도기 때문에 혼자서는 아무것도 못함
   => " new " 로 객체 생성해 메모리에 올려줘야 사용가능!
- static은 일단 쓰지 말기

## ※ IP  (Internet Protocol)
- 인터넷에 연결된 장치들을 식별할 수 있는 고유 주소
- 패킷(Packet) : 통신 단위
- 노드(Node) : 장치(기기)

## ※ TCP
- Handshake 과정을 통해 안정적 연결

   -> 신뢰성 확보 / 연결 지향적

   -> 3 way Handshake = "연결 시작할 때"

   -> 4 way Handshake = "연결 끊을 때"
- 장) 데이터 정확성/신뢰성 보장
- 장) 순차적/안정적 데이터 전송
- 단) UDP보다 느릴 수 있음 (실시간 통신 부적합)
- 데이터 올바른지, 순서대로 왔는지 등등 확인

   => UDP 에 비해 느림

## ※ UDP
- 비연결 지향적
  -> 연결 없이 데이터 전송
- 신뢰성 X
- 장) 빠른 데이터 전송 속도

  -> 데이터 관련 확인 안하기 때문
- 단) 신뢰성 낮고 데이터 손실 발생할 수 있음
- 중간에 유실돼도 지장이 없는 경우 사용

   ex) 스트리밍 서비스

## ※ URI / URL
- 같다고 생각하고 진행

## ※ DNS (Domain Name System)
- IP주소 몰라도 도메인 주소(www.naver.com)만 치고 사이트로 들어갈 수 있는 이유 
  => 사람이 읽는 도메인 주소를 컴퓨터가 이해할 수 있는 IP주소로 변환해주는 서버

## ※ HTTP Methods
   -> 강제는 아니지만, 의도를 드러낼 수 있음

- GET = " 조회 "
- POST = " 등록 "

   -> 판단하기 애매할 땐 "POST"
- PUT = " 전체 수정 "
- PATCH = " 일부 수정 "
- DELETE = " 삭제 "
- HEAD = "body가 없는 GET"
- OPTIONS = "pre, 선"

## ※ member 관련 API 작성 (예시)
    member!! -> 의도 명확 => rest API

[전체 조회]

/members


[단건 조회]

GET/members/{memberId}
-> 전체 members 중에 {memberId} 조회해달라는 뜻


[단건 수정]

PUT/members/{memberId}
PATCH/members/{memberId}


[단건 삭제]

DELETE/members/{memberId}


[단건 등록]

POST/members

-> DB에 데이터를 저장하는데, 아직 안들어갔기 때문에 memberId 있을 수 없다


## ※ REST API
    -> 표준 X , 소통에 드는 리소스 줄이기 위함

1. 명사 / 복수

     ex) /members, /items
2. 마지막에 ' / ' 금지
3. ' - ' 사용(_ 금지) / 대문자 사용X
4. 확장자 미포함
5. 계층화

   -> 상위 순으로! (members가 있어야 items가 있을 수 있음)

   -> /members/{memberId}/items/{itemId}

## ※ REST API
   - Query String

       -> 필수값을 수도 아닐 수도 있음

       => ? 뒤에 나옴

      ex) /members?age=20&status=active

   - Path Variable

       -> 반드시 필수! (없을 시 api가 다른 api가 되어버림)

       => / 뒤에 나옴

      ex) /members/{memberId}


## ※ 라이브러리
   - 정말 작은 요소

## ※ 프레임워크
- 여러 라이브러리들의 집합체

## ※ 3 Layered Architecture

![설명자료](images/11.png)

 1. Presentation layer  ( "Controller" 클래스 )
     - 사용자 요청(프론트엔드 -> 백엔드)을 받음
     - 클라이언트와 소통하는 역할
     - [ JSON <-> 자바 ]  (객체 변환)


 2. Business Logic layer  ( "Service" 클래스 )
     - 비즈니스 로직 실행되는 부분
     - 실제 로직 수행
     - Controller / Repository 사이에서 데이터 가공


 3. Data Access layer  ( "Repository" 클래스 )
     - DB와 직접 소통 ->  [ 자바 <-> SQL ]
     - CRUD 담당
       (Create, Read, Update, Delete)