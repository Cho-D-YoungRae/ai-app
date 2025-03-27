# AI 애플리케이션

- `core:core-api`: 실행 가능한 API 서버 애플리케이션
- `core:core-enum`: `core-api` 에서 사용되며, 외부 모듈에서도 사용되어야 하는 Enum
- `client`: 외부 시스템
- `storage:db-core`: 데이터베이스

로컬 개발 환경 세팅

```sh
docker compose up -d
```

## 구조(core-api)

> 계층 구조를 따름.

- Presentation Layer
- Business Layer
- Implement Layer
  - 비즈니스 로직을 이루기 위해 도구로서 상세 구현 로직을 갖음
- Data Access Layer

## 기능 구현

1. 사용자 관리 및 인증 기능
   - 개인 정보 암호화 제외
   - 리프레시 토큰과 같은 로그인 유지 로직 제외
2. 대화(chat) 관리 기능
   - stream 기능 제외
   - redis 등을 통한 세션 개선 제외
3. 사용자 피드백 관리 기능
4. 분석 및 보고 기능
5. 대화 관리 기능 세션 개선
6. 대화 관리 기능 stream
7. 사용자 관리 및 인증 기능 암호화
