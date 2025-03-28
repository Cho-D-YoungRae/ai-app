# AI 애플리케이션

- `core:core-api`: 실행 가능한 API 서버 애플리케이션
- `core:core-enum`: `core-api` 에서 사용되며, 외부 모듈에서도 사용되어야 하는 Enum
- `client`: 외부 시스템
- `storage:db-core`: 데이터베이스

로컬 개발 환경 세팅

```sh
docker compose up -d
```

## 실행

`http/core-api.http` 의 API 스펙에 따라 순차적으로 실행

admin 로그인 시 로그인 API 를 아래와 같이 수정 후 발급되는 토큰 사용

```json
{
  "email": "admin@test.com",
  "password": "admin"
}
```

API 의 파리미터를 변경하여 사용 가능

## 구조(core-api)

> 계층 구조를 따름.

- Presentation Layer
- Business Layer
- Implement Layer
  - 비즈니스 로직을 이루기 위해 도구로서 상세 구현 로직을 갖음
- Data Access Layer

## 기능 구현

> 2번 까지 구현 완료

1. 사용자 관리 및 인증 기능
   - 개인 정보 암호화 제외
   - JWT 토큰 제외. 임시 토큰으로 대체.
   - 리프레시 토큰과 같은 로그인 유지 로직 제외
2. 대화(chat) 관리 기능
   - stream 기능 제외
   - redis 등을 통한 세션 개선 제외
   - Rate Limiting 제외
3. 사용자 피드백 관리 기능
4. 분석 및 보고 기능
5. 대화 관리 기능 세션 개선
6. 대화 관리 기능 stream
7. 사용자 관리 및 인증 기능 토큰 개선
8. 사용자 관리 및 인증 기능 암호화 개선

## 추후 개선 작업

1. JWT 토큰 사용
2. 채팅 로직 수정
3. 피드백 생성
4. 피드백 조회
5. 피드백 상태 변경