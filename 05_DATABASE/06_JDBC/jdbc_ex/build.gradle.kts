// java 플러그인은 Gradle에 적용
plugins {
    id("java")
}

// 프로젝트 그룹이랑, 버전 관리
group = "org.scoula"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

// 의존성을 추가한 내용들

/*
* implemenation - 내가 쓸 라이브러리
* compileOnly - 컴파일 때만 필요함, JAR에 포함 안됨 (Lombok)
* runtimeOnly - 런타임에만 필요
* annotationProcessor - 컴파일 중 코드를 생성하는 것 (Lombok)
* test ... - 테스트 코드 전용
* */
dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")


    // assertJ
    testImplementation("org.assertj:assertj-core:3.27.7")

    // MySQ jdbc 드라이버
    implementation("com.mysql:mysql-connector-j:8.3.0")

    // lombok
    compileOnly("org.projectlombok:lombok:1.18.42")
    annotationProcessor("org.projectlombok:lombok:1.18.42")

    // lombok test
    testAnnotationProcessor("org.projectlombok:lombok:1.18.42")
    testCompileOnly("org.projectlombok:lombok:1.18.42"  )
}


tasks.test {
    useJUnitPlatform()
}