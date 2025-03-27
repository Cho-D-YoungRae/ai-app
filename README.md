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

