pipeline {
	agent any

	stages {
		stage('Build') {
			customWorkspace './labCon'
			steps {
				sh 'mvn install'
				archiveArtifacts artifacts: 'target/restapi.war', fingerprint: true
			}
		}
		stage('Test') {
			customWorkspace './labCon'
			steps {
				sh 'mvn test ||  true'
			}
		}
		stage('Deploy') {
			when {
				expression {
					currentBuild.result == null || currentBuild.result == 'SUCCESS'
				}
			}
			steps {
				sh './launch-servers.sh'
				sh './hot-deploy'
			}
		}
	}
}
