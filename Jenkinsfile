pipeline {
	agent any

	stages {
		stage('Build') {
			steps {
				cd 'labCon'
				sh 'mvn install'
				archiveArtifacts artifacts: 'target/restapi.war', fingerprint: true
			}
		}
		stage('Test') {
			steps {
				cd 'labCon'
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
