pipeline {
	agent any
	stages {
		
		//stage('Clean image'){
		//	steps {
		//		sh "docker stop application_ws"
		//		sh "docker rm application_ws"
		//		echo "Deploy: STARTING"
		//	}
		//}
		
		stage('Run docker'){
			steps {
				sh "docker-compose up -d --build"
			}
		}
		
		stage('Deploy finish'){
			steps {
				echo "Deploy: OK"
			}
		}
	}
}
