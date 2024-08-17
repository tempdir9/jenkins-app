pipeline {
    agent any
    parameters {
        string(defaultValue: 'main', name: 'BRANCH_NAME',trim: true)
    }
    stages {
        stage('Clone from git') {
            steps {
                checkout scmGit(branches: [[name: params.BRANCH_NAME]],
                    userRemoteConfigs: [[url: 'https://github.com/tempdir9/jenkins-app.git', credentialsId: 'github-credentials']])
            }
        }
        stage('Build') {
            steps {
                sh "./gradlew clean build"
			}
        }
        stage('Test') {
            steps {
                sh "./gradlew test"
            }
            post {
                always {
                    junit 'build/test-results/**/*.xml'
                }
            }
        }
        stage('CodeQL scan') {
            environment {
                GITHUB_TOKEN = credentials('github-token')
            }
            tools {
                codeql 'codeql-2.18.2'
            }
            steps {
                sh 'rm -rf codeql && mkdir codeql'
                sh "codeql database create codeql/project-database --language=java --command='./gradlew --no-daemon --no-build-cache clean build'"
                sh 'codeql database analyze codeql/project-database --format=sarif-latest --sarif-category=java --output=codeql/scan-result.sarif'
                sh "codeql github upload-results --repository=tempdir9/jenkins-app --ref=refs/heads/${params.BRANCH_NAME} --commit=${env.GIT_COMMIT} --sarif=codeql/scan-result.sarif"
            }
        }
        stage('Create Jar') {
            steps {
                sh "./gradlew bootJar"
            }
            post {
                success {
                    archiveArtifacts 'build/libs/*.jar'
                }
            }
        }
    }
}