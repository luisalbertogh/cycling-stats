node('deploy') {
    stage('Checkout') {
        // git changelog: false, credentialsId: 'b62020a2-bb9d-4303-897b-7ce7730d7cef', poll: false, url: 'https://github.com/luisalbertogh/oracle-maven.git'
        git 'https://github.com/luisalbertogh/cycling-stats.git'
    }

    /*
    stage 'Build'
    def mvnHome = tool 'Maven 3.2'
    bat "${mvnHome}/bin/mvn clean install"
    
    stage 'Archive'
    archiveArtifacts artifacts: 'target/*.jar', onlyIfSuccessful: true */
}