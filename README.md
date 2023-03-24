## 먹어보시개 백엔드 (Eat it dog Backend)

![image](https://user-images.githubusercontent.com/80818534/223601480-41d10b62-ae0d-475e-a86d-2abd9b7b59bb.png)

### 먹어보시개란?

`먹어보시개` 서비스는 반려견이 먹어도 되는 음식인지, 먹으면 위험한 음식인지 궁금할 때 간결한 해답을 주는 서비스입니다. 반려견 집사로 생활하면서 느끼던 불편함에 착안해 개발했습니다.

음식 별 `섭취 가능 여부`, `급여 방법`, `증상`, `주성분 및 기능` 등의 정보를 간결하고 정확하게 제공하여
* 강아지가 위험한 음식을 먹었을 때 신속하게 대처 가능하게 하기
* 기존 블로그 및 인터넷 검색으로 찾는 시간 줄이기
* 인터넷의 단순 광고성 포스팅, 틀린 정보 배제하기

등과 같은 문제 해결을 `앱/웹 플랫폼`으로서 시도합니다.

### 서버 상세 기술 스택
* Backend : SpringBoot (Java), JPA, lombok, JWT
* Deploy : Github Actions, Docker, AWS Route 53, ALB, EC2
* DB : MySQL, AWS RDS

### Commit 규칙

#### Commit Template
`git commit template`을 사용하여 커밋 규칙을 강제합니다. (./.gitmessage)

아래 명령어로 템플릿 파일을 설정해놓으면 `git commit` 명령어를 통해 Vim에서 템플릿 메시지 조회/커밋을 실행할 수 있습니다.

```
git config --global commit.template .gitmessage.txt
```

#### Commit 기본 형식

```
(gitmoji) :: 구체적인 커밋 메시지
```

#### gitmoji 타입

| icon |     code    |           description            |
|:---:|:--------:|:--------------------------------:|
| ✨   | `:sparkles:` |        새 기능 (파일 추가)            |
| 📝   |  `:memo:`    |     코드 수정 (요구사항 수정)    |
| 🎨   |   `:art:`    |     코드 구조 개선        |
| ⚡️    |    `:zap:`    |   코드 성능 개선        |
| 🔥   |    `:fire:`  |   코드 삭제 (파일 삭제)   |
| 📄 |    `:page_facing_up:`  |   문서 작성 및 변경    |
| 🔧 |  `:wrench:`      | Configuration 파일/의존성 추가 및 삭제 |
| 👷 |   `:construction_worker:`    |   CI/CD 시스템 추가/수정     |
| 🐛 |     `:bug:`    |         버그 수정               |
| ✅ |  `:white_check_mark:`   |      테스트 케이스 작성 및 수정    |
| ⏪ | `:rewind:` |            작업 되돌리기              |
| 🚑 |   `:ambulance:`    |          긴급 수정             |
| 🙈 | `:see_no_evil:`  | .gitignore 추가/수정 |

### Version 규칙

> 모든 버전은 01.00.00에서 시작합니다.
```
"01.01.09" 생략시 "1.1.9"
"01.01.10" 생략시 "1.1.10"
```

- 기존 버전과 호환되지 않도록 API가 변경되면 **Major Version**를 올립니다.
- 기존 버전과 호환되면서 새로운 기능을 추가할 때는 **Minor Version**를 올립니다.
- 자잘한 버그나 내부적 코드 보완 등의 변화가 발생했을때 **Patches**를 올립니다.
