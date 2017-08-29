node {
    // Maven home (configured in Jenkins global configuration)
    def mvnHome = tool 'Maven 3.2'

    // Checkout 
    stage('Checkout') {
        checkout scm
    }

    // Build
    stage('Build') {
        bat "${mvnHome}/bin/mvn clean package -Dmaven.test.skip=true"
    }

    // Artifacts
    stage('Archive') {
        archiveArtifacts artifacts: 'target/*.jar', onlyIfSuccessful: true 
    }

    // Deploy
    stage('Deploy') {
        bat "${mvnHome}/bin/mvn deploy -Dmaven.test.skip=true"
    }
}