pipeline {
	agent any
	stages {
		
		stage('Clean image'){
			steps {
				sh "docker stop application_ws"
				sh "docker rm application_ws"
			}
		}
		
		stage('Run docker'){
			steps {
				sh "docker-compose up -d --build"
				echo "Deploy Finished"
			}
		}
	}
}
