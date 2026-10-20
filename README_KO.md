# Fisch-style Fishing Minigame (프로토타입)

Minecraft Java 1.21.11 / Fabric / Java 21

## 현재 구현
- 낚싯대를 사용하면 미니게임 화면이 열립니다.
- 막대의 흰색 표시가 초록색 구간 안에 있을 때 스페이스바를 누르면 진행도가 올라갑니다.
- 구간 밖에서 누르면 진행도가 감소합니다.
- 진행도 100%면 성공 메시지가 표시됩니다.
- 아직 실제 물고기 아이템 지급, 실제 입질 타이밍, 모바일 터치 버튼, 서버 동기화는 구현되지 않았습니다.

## Android 휴대폰만 있는 경우
가장 쉬운 방법은 프로젝트를 GitHub에 올린 뒤 GitHub Actions로 빌드하는 것입니다.

1. GitHub에서 새 저장소(repository)를 만듭니다.
2. 이 ZIP의 압축을 풀고 파일/폴더를 저장소에 업로드합니다.
3. 아래 경로의 workflow 파일을 추가합니다: `.github/workflows/build.yml`
4. 저장소의 Actions 탭에서 빌드가 끝나면 `fisch-minigame-jar` artifact를 다운로드합니다.
5. 다운로드한 artifact ZIP 안의 `fisch-minigame-0.1.0.jar`를 Minecraft Java 1.21.11 + Fabric의 `mods` 폴더에 넣습니다.

## PC 또는 클라우드 개발 환경에서 빌드
Java 21과 Gradle이 설치된 환경에서 프로젝트 루트 폴더에서 실행:
`gradle build`

빌드 결과:
`build/libs/fisch-minigame-0.1.0.jar`

## 주의
- 이 프로젝트는 첫 번째 프로토타입이며, 여기서 직접 Minecraft 실행 테스트를 수행한 것은 아닙니다.
- Fabric/Minecraft 버전이나 API 차이로 빌드 오류가 나면 오류 로그를 보내 주세요. 그 로그에 맞춰 수정할 수 있습니다.
- 미니게임은 현재 키보드 스페이스바 조작 기준입니다. Android 터치 플레이까지 지원하려면 화면 버튼을 추가해야 합니다.
