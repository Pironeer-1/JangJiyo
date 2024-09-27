# Convention Template

## 1. 디렉토리 및 파일
### 📌 `도메인형 구조`
- 각 도메인들의 흐름을 파악하기 쉽게 하기 위하여 **도메인형 구조** 선택

  (개인적으로 도메인형 구조가 직관적이어서 보기에 더 편리한 듯..)

### 📌 `사용 방식`
    ▶️ global: 특정 도메인에 종속되지 않고, 프로젝트 전방위적으로 이용할 수 있는 것들
    ▶️ board: controller, service, repository 등 topic과 관련된 구현
    ▶️ member: controller, service, repository 등 member와 관련된 구현

    ▶️ controller: 사용자의 요청 처리, 적절한 서비스 메서드를 호출하여 결과를 클라이언트에게 전달
    ▶️ service: 애플리케이션의 비즈니스 로직
    ▶️ repository: 데이터 저장소와의 상호작용(CRUD)
    ▶️ dto: 계층 간 데이터 교환에 사용
    ▶️ entity: 데이터베이스의 테이블과 직접적으로 매핑되어 데이터를 영구적으로 저장
  

## 2. package, class, method, 변수와 상수 명명 규칙
### 📌 `Package`
- 클래스명과의 쉬운 구분을 위해 **소문자** 사용
### 📌 `Class`
- **파스칼** 표기법을 사용하며, **명사**로 시작
### 📌 `Method`
- **카멜** 표기법을 사용하며, **동사**로 시작
### 📌 `변수&상수`
- 변수: **카멜** 표기법
- 상수: **스네이크** 케이스를 사용하여 **대문자**로 표기

### 📌 `사용 방식`
    ▶️ package: global, board, member / controller, service, repository, ...
    ▶️ class: BoardController, BoardService, JwtTokenSet, ...
    ▶️ method: save, findById, existByMemberId, ...
    ▶️ 변수: boardMap, memberMap, ...
    ▶️ 상수: USER_NOT_EXIST, USER_WRONG_PASSWORD, ...


## 3. Repository 추상화
### 📌 `유지보수성 및 확장성`
- 데이터베이스 구조나 코드가 변경되더라도 인터페이스를 통해 데이터베이스를 다루기에 비즈니스 로직에 영향을 최소화할 수 있음
- repository 계층을 확장하여 추가 기능을 제공하거나 다른 데이터베이스 시스템으로 전환하더라도 쉽게 대응 가능


## 4. 에러 처리
### 📌 `CustomException 이용`
- 전역적으로 예외를 처리하기 위해 CustomException을 사용해 에러를 처리함
- 에러 종류의 경우에는 ErrorCode라는 enum 클래스에 정의함

