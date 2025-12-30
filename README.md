# SPRING PLUS
# 1.프로젝트 설명
이 프로젝트는 제공된 Spring Boot 애플리케이션의 오류를 해결하고, 코드 품질을 개선하는 능력을 기르기 위한 과제입니다.

# 2.개발 환경
* 개발 언어: Java
* JDK 버전: 17
* 프레임워크: Spring Boot 3.3.3
* 빌드 도구: Gradle
* 데이터베이스: MySQL
* ORM: Spring Data JPA (Hibernate)
* 보안 / 인증: JWT, BCrypt
* IDE: IntelliJ IDEA
* API 테스트 도구: Postman

# 3.단계별 구현 기능
| 레벨| 기능명| 간단 설명 |
| ----- | ------------------- | -------------------------------------------------- |
| Lv 1  | 트랜잭션 오류 수정          | `@Transactional(readOnly=true)`로 인해 발생한 저장 오류를 수정  |
| Lv 2  | JWT 닉네임 추가             | User에 `nickname` 컬럼 추가 및 JWT에 닉네임 포함                |
| Lv 3  | 할 일 검색 기능 개선 (JPQL) | weather 조건 및 수정일 기간 조건을 선택적으로 검색               |
| Lv 4  | 컨트롤러 테스트 수정        | Todo 단건 조회 실패 테스트가 통과하도록 테스트 코드 수정         |
| Lv 5  | AOP 동작 수정               | 관리자 권한 변경 메소드 실행 전에 AOP가 동작하도록 수정         |
| Lv 6  | JPA Cascade 적용            | 할 일 생성 시 생성 유저가 담당자로 자동 등록                    |
| Lv 7  | N+1 문제 해결 (댓글)        | 댓글 조회 시 연관 엔티티 N+1 문제 해결                         |
| Lv 8  | QueryDSL 전환               | Todo 조회 JPQL을 QueryDSL로 변경하고 N+1 방지                 |
| Lv 9  | Spring Security 적용        | 기존 Filter / ArgumentResolver → Spring Security로 전환       |
| Lv 10  | QueryDSL 검색 API          | 제목·담당자·기간 검색 + 페이징 + Projection 적용               |
| Lv 11 | 트랜잭션 심화               | 매니저 등록 실패와 무관하게 로그는 항상 저장                   |
| Lv 12 | AWS 배포                    | EC2 + RDS + S3 구성 및 health check API 제공                   |

12-1 EC2

<img width="1909" height="836" alt="2" src="https://github.com/user-attachments/assets/c1e8ce8d-882d-46cd-b6f7-35514ea4186e" />

12-2 RDS

<img width="1894" height="832" alt="1" src="https://github.com/user-attachments/assets/7a0bd5f0-3d9b-4fce-919a-1dc35795988e" />

